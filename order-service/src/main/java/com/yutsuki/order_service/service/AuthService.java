package com.yutsuki.order_service.service;

import com.yutsuki.order_service.entity.User;
import com.yutsuki.order_service.exception.AuthException;
import com.yutsuki.order_service.exception.BaseException;
import com.yutsuki.order_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthService {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    public User getUser() throws BaseException {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            log.warn("GetUser-[block]:(authentication is null)");
            throw AuthException.unauthorized();
        }

        if (!authentication.isAuthenticated()) {
            log.warn("GetUser-[block]:(not authenticated)");
            throw AuthException.unauthorized();
        }

        if (!(authentication.getCredentials() instanceof Jwt jwt)) {
            log.warn("GetUser-[block]:(credentials is not Jwt)");
            throw AuthException.unauthorized();
        }

        if (!tokenService.isAccessToken(jwt)) {
            log.warn("GetUser-[block]:(not access token)");
            throw AuthException.unauthorized();
        }

        final String userId = authentication.getName();

        if (ObjectUtils.isEmpty(userId)) {
            log.warn("GetUser-[block]:(accountId is empty)");
            throw AuthException.unauthorized();
        }

        Optional<User> userOptional = userRepository.findById(Long.parseLong(userId));
        if (userOptional.isEmpty()) {
            log.warn("GetUser-[block]:(not found user). userId:{}", userId);
            throw AuthException.unauthorized();
        }

        return userOptional.get();

    }
}
