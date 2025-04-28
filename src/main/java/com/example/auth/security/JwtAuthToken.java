package com.example.auth.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthToken extends AbstractAuthenticationToken {
    private final String principal;

    public JwtAuthToken(String principal) {
        super(null);
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
