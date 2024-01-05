// Copyright (c) Tribufu. All Rights Reserved.

package dev.mintaka.oauth2;

public class OAuth2TokenRequest {
    public final OAuth2GrantType grantType;
    public final String code;
    public final String refreshToken;
    public final String username;
    public final String password;
    public final String clientId;
    public final String clientSecret;
    public final String redirectUri;

    public OAuth2TokenRequest(OAuth2GrantType grantType, String code, String refreshToken, String username,
            String password, String clientId, String clientSecret, String redirectUri) {
        this.grantType = grantType;
        this.code = code;
        this.refreshToken = refreshToken;
        this.username = username;
        this.password = password;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }
}
