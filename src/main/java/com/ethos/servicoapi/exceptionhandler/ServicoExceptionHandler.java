package com.ethos.servicoapi.exceptionhandler;

import com.ethos.servicoapi.exception.PrestadoraNaoAprovadaException;
import com.ethos.servicoapi.exception.PrestadoraNotFoundException;
import com.ethos.servicoapi.exception.ServicoException;
import com.ethos.servicoapi.exception.ServicoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ServicoExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroValidacao>> errosValidacaoException(MethodArgumentNotValidException exception){
        List<FieldError> camposInvalidos = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(camposInvalidos.stream().map(ErroValidacao::new).toList());
    }

    private record ErroValidacao(String campo, String mensagemErro){
        public ErroValidacao(FieldError campoInvalido){
            this(campoInvalido.getField(), campoInvalido.getDefaultMessage());
        }
    }

    @ExceptionHandler(ServicoException.class)
    public ResponseEntity<Void> servicoException(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ServicoNotFoundException.class)
    public ResponseEntity<Void> servicoNotFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(PrestadoraNotFoundException.class)
    public ProblemDetail prestadoraNotFound(PrestadoraNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setDetail(e.getMessage());
        problemDetail.setTitle("Prestadora not found");
        return problemDetail;
    }

    @ExceptionHandler(PrestadoraNaoAprovadaException.class)
    public ProblemDetail prestadoraNaoAprovada(PrestadoraNaoAprovadaException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail(e.getMessage());
        problemDetail.setTitle("Prestadora não aprovada");
        return problemDetail;
    }
}
