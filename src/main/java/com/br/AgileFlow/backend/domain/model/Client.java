package com.br.AgileFlow.backend.domain.model;

import com.br.AgileFlow.backend.core.util.Base64Converter;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "redirect_uri")
    private String redirectUri;

    private String scope;

    @PrePersist
    private void prePersist() {
        if(id == null) {
            clientSecret = Base64Converter.encode(clientSecret);
        }
    }


}
