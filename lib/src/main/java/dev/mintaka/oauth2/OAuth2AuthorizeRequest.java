// Copyright (c) Tribufu. All Rights Reserved.

package dev.mintaka.oauth2;

public class OAuth2AuthorizeRequest {
    public final OAuth2ResponseType responseType;
    public final String clientId;
    public final String scope;
    public final String redirectUri;
    public final String state;

    public OAuth2AuthorizeRequest(OAuth2ResponseType responseType, String clientId, String scope, String redirectUri,
            String state) {
        this.responseType = responseType;
        this.clientId = clientId;
        this.scope = scope;
        this.redirectUri = redirectUri;
        this.state = state;
    }
}
