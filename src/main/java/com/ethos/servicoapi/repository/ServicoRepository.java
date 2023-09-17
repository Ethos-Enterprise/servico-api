package com.ethos.servicoapi.repository;

import com.ethos.servicoapi.repository.entity.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicoRepository extends JpaRepository<ServicoEntity, UUID> {
}
