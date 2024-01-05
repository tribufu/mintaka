// Copyright (c) Tribufu. All Rights Reserved.

package dev.mintaka.oauth2;

public class OAuth2TokenResponse {
    public final OAuth2TokenType tokenType;
    public final String accessToken;
    public final String refreshToken;
    public final String scope;
    public final int expiresIn;

    public OAuth2TokenResponse(OAuth2TokenType tokenType, String accessToken, String refreshToken, String scope,
            int expiresIn) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.expiresIn = expiresIn;
    }
}
