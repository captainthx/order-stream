package com.yutsuki.order_service.service;

import com.yutsuki.order_service.enums.TokenType;
import com.yutsuki.order_service.exception.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    @Value("${api.jwt.expiration.access-token}")
    private Duration accessExpiration;

    @Value("${api.jwt.expiration.refresh-token}")
    private Duration refreshExpiration;

    @Value("${api.jwt.issuer}")
    private String issuer;


    public Jwt generateAccessToken(Long userId) {
        var now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .id(UUID.randomUUID().toString())
                .issuer(issuer)
                .issuedAt(now)
                .expiresAt(now.plus(accessExpiration))
                .subject(String.valueOf(userId))
                .claim("type", TokenType.ACCESS)
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }


    public Jwt generateRefreshToken(Long userId) {
        var now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .id(UUID.randomUUID().toString())
                .issuer(issuer)
                .issuedAt(now)
                .expiresAt(now.plus(refreshExpiration))
                .subject(String.valueOf(userId))
                .claim("type", TokenType.REFRESH)
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }


    public Jwt decoder(String token) throws AuthException {
        try {
            return jwtDecoder.decode(token);
        } catch (Exception e) {
            throw AuthException.unauthorized();
        }
    }


    public boolean isAccessToken(final Jwt jwt) {
        return Objects.equals(jwt.getClaim("type"), TokenType.ACCESS.name());
    }

    public boolean isRefreshToken(final Jwt jwt) {
        return Objects.equals(jwt.getClaim("type"), TokenType.REFRESH.name());
    }

}
