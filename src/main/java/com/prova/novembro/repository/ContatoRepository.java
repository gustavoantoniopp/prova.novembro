package com.prova.novembro.repository;

import com.prova.novembro.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    Optional<Contato> findByEmail(String email);
    Optional<Contato> findByPhone(String phone);

}
