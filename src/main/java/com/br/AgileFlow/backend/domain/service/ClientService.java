package com.br.AgileFlow.backend.domain.service;

import com.br.AgileFlow.backend.core.exception.NotFoundException;
import com.br.AgileFlow.backend.domain.model.Client;
import com.br.AgileFlow.backend.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    public Client findByClientId(String clientId) {
        return repository.findByClientId(clientId).orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }

}
