// Copyright (c) Tribufu. All Rights Reserved.

package dev.mintaka.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Http Client Options
 *
 * Options for creating a HttpClient instance.
 */
public class HttpClientOptions {
    public String baseUrl;
    public Map<String, String> headers;
    public boolean logEnabled;
    public String logTarget;

    public HttpClientOptions() {
        this(null);
    }

    public HttpClientOptions(String baseUrl) {
        this(baseUrl, new HashMap<>(), false, "HttpClient");
    }

    public HttpClientOptions(String baseUrl, Map<String, String> headers, boolean logEnabled, String logTarget) {
        this.baseUrl = baseUrl;
        this.headers = headers;
        this.logEnabled = logEnabled;
        this.logTarget = logTarget;
    }
}
