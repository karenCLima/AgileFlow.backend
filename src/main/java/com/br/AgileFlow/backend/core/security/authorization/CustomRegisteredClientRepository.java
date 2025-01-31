package com.br.AgileFlow.backend.core.security.authorization;

import com.br.AgileFlow.backend.core.util.Base64Converter;
import com.br.AgileFlow.backend.domain.model.Client;
import com.br.AgileFlow.backend.domain.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomRegisteredClientRepository implements RegisteredClientRepository {

    private final TokenSettings tokenSettings;
    private final ClientSettings clientSettings;
    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(RegisteredClient registeredClient) {
    }

    @Override
    public RegisteredClient findById(String id) {
        return null;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client = clientService.findByClientId(clientId);
        String passwordDecoded = Base64Converter.decode(client.getClientSecret());
        String password = passwordEncoder.encode(passwordDecoded);

        return RegisteredClient
                .withId(client.getId())
                .clientId(client.getClientId())
                .clientSecret(password)
                .redirectUri(client.getRedirectUri())
                .scope(client.getScope())
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .tokenSettings(tokenSettings)
                .clientSettings(clientSettings)
                .build();
    }
}
