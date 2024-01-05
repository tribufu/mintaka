// Copyright (c) Tribufu. All Rights Reserved.

package dev.mintaka.oauth2;

public class OAuth2CodeResponse {
    public final String code;
    public final String state;

    public OAuth2CodeResponse(String code, String state) {
        this.code = code;
        this.state = state;
    }
}
