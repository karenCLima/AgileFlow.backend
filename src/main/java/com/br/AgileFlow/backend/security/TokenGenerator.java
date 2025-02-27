package com.br.AgileFlow.backend.security;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import com.br.AgileFlow.backend.dto.response.TokenResponse;


@Component
public class TokenGenerator {
	
	@Autowired
	JwtEncoder accessTokenEncoder;
	
	@Autowired
	@Qualifier("jwtRefreshTokenEncoder")
	JwtEncoder refreshTokEncoder;
	
	private String createAcessToken(Authentication authentication) {
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		
		Instant now = Instant.now();
		
		JwtClaimsSet claimsSet = JwtClaimsSet.builder()
				.issuer("AgileFlow")
				.issuedAt(now)
				.expiresAt(now.plus(25, ChronoUnit.MINUTES))
				.subject(userDetailsImpl.getUsername())
				.build();
		return accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet))
				.getTokenValue();
	}
	
	private String createRefreshToken(Authentication authentication) {
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
				
		Instant now = Instant.now();
				
		JwtClaimsSet claimsSet = JwtClaimsSet.builder()
						.issuer("AgileFlow")
						.issuedAt(now)
						.expiresAt(now.plus(15, ChronoUnit.DAYS))
						.subject(userDetailsImpl.getUsername())
						.build();
		return refreshTokEncoder.encode(JwtEncoderParameters.from(claimsSet))
				.getTokenValue();
				
	}
	
	public TokenResponse createToken(Authentication authentication) {
		if(!(authentication.getPrincipal()instanceof UserDetailsImpl detailsImpl)) {
			throw new BadCredentialsException(
					MessageFormat.format("Principal {0} is not of User type", authentication.getPrincipal().getClass())
					);
		}
		
		TokenResponse tokenResponse = new TokenResponse();
		tokenResponse.setUsername(detailsImpl.getUsername());
		tokenResponse.setAccessToken(createAcessToken(authentication));
		
		String refreshToken;
		if(authentication.getCredentials() instanceof Jwt jwt) {
			Instant now = Instant.now();
			Instant expiresAt = jwt.getExpiresAt();
			Duration duration = Duration.between(now, expiresAt);
			long daysUntilExpired = duration.toDays();
			if(daysUntilExpired < 7) {
				refreshToken = createRefreshToken(authentication);
			}else {
				refreshToken = jwt.getTokenValue();
			}
			tokenResponse.setRefreshToken(refreshToken);
		}
		
		return tokenResponse;
		
		
	}

}
