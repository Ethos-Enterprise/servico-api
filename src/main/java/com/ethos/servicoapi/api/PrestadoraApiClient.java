package com.ethos.servicoapi.api;

import com.ethos.servicoapi.api.prestadoradto.Prestadora;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "prestadora", url = "http://prestadora-api:8085/v1.0/prestadoras")
public interface PrestadoraApiClient {
    @GetMapping("/{id}")
    Prestadora getPrestadoraById(@PathVariable UUID id);
}
