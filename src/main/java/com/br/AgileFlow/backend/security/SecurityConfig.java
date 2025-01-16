package com.br.AgileFlow.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import com.br.AgileFlow.backend.service.UserDetailsServiceImpl;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	
	@Autowired
	JWTtoUserConvertor jwTtoUserConvertor;
	@Autowired
	KeyUtils keyUtils;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/api/user/register", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .anyRequest().authenticated()
                )
            .cors(cors->cors.disable())
            .httpBasic(basic -> basic.disable())
            .oauth2ResourceServer(oauth2 -> oauth2.jwt((jwt) -> jwt.jwtAuthenticationConverter(jwTtoUserConvertor)))
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling((exceptions)->exceptions.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
            		.accessDeniedHandler(new BearerTokenAccessDeniedHandler()))
            .build();
    }
	
	@Bean
	@Primary
	JwtDecoder jwtAccessTokenDecoder() {
		return NimbusJwtDecoder.withPublicKey(keyUtils.getAccessTokenPublicKey()).build();
	}
	
	@Bean
	@Primary
	JwtEncoder jwtAccessTokenEncoder() {
		JWK jwk = new RSAKey.Builder(keyUtils.getAccessTokenPublicKey())
				.privateKey(keyUtils.getAccessTokenPrivateKey())
				.build();
		
		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}
	
	@Bean
	@Qualifier("jwtRefreshTokenDecoder")
	JwtDecoder jwtRefreshTokenDecoder() {
		return NimbusJwtDecoder.withPublicKey(keyUtils.getRefreshTokenPublicKey()).build();
	}
	
	@Bean
	@Qualifier("jwtRefreshTokenEncoder")
	JwtEncoder jwtRefreshTokenEncoder() {
		JWK jwk = new RSAKey.Builder(keyUtils.getRefreshTokenPublicKey())
				.privateKey(keyUtils.getRefreshTokenPrivateKey())
				.build();
		
		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}
	
	@Bean
	@Qualifier("jwtRefreshTokenAuthProvider")
	JwtAuthenticationProvider jwtRefreshTokenAuthenticationProvider() {
		JwtAuthenticationProvider provider = new JwtAuthenticationProvider(jwtRefreshTokenDecoder());
		provider.setJwtAuthenticationConverter(jwTtoUserConvertor);
		return provider;
	}
	
	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userDetailsServiceImpl);
		return provider;
	}

}
