package com.br.AgileFlow.backend.domain.repository;

import com.br.AgileFlow.backend.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    public Optional<Client> findByClientId(String clientId);

}
