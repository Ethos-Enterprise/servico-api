package com.ethos.servicoapi.service;

import com.ethos.servicoapi.controller.response.ServicoResponse;

public class ServicoFilaObj <T>{

    private int tamanho;
    private T[] fila;

    public ServicoFilaObj() {
        this.tamanho = 0;
        this.fila = (T[]) new ServicoResponse[10];
    }

    public boolean isEmpty() {
        if (tamanho == 0){
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if (tamanho == fila.length){
            return true;
        }
        return false;
    }


    public ServicoResponse insert(T info) {
        if (isFull()){
            throw new IllegalStateException("Lista cheia.");
        } else {
            fila[tamanho] = info;
            tamanho++;
        }
        return null;
    }

    public T peek() {
        return fila[0];
    }

    public T poll() {
        if (isEmpty()){
            throw new IllegalStateException("Fila vazia.");
        }

        T primeiroDaFila = fila[0];

        for (int i = 0; i < tamanho - 1; i++){
            fila[i] = fila[i + 1];
        }
        fila[tamanho - 1] = null;
        tamanho--;

        return primeiroDaFila;
    }

    public void exibe() {
        for (int i = 0; i < fila.length; i++){
            System.out.println(fila[i]);
        }
    }

    public int getTamanho(){
        return tamanho;
    }

    public T[] getFila() {
        return fila;
    }
}
