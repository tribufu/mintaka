// Copyright (c) Tribufu. All Rights Reserved.

package dev.mintaka.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Http Client
 *
 * Helper class to make HTTP requests.
 */
public class HttpClient {
    private static final Gson gson = new GsonBuilder().create();

    private final java.net.http.HttpClient inner;
    protected final HttpClientOptions options;

    /**
     * Create a default HttpClient instance.
     */
    public HttpClient() {
        this(new HttpClientOptions());
    }

    /**
     * Create a HttpClient instance with the specified options.
     *
     * @param baseUrl
     * @param headers
     * @param logEnabled
     * @param logTarget
     */
    public HttpClient(String baseUrl, Map<String, String> headers, boolean logEnabled, String logTarget) {
        this(new HttpClientOptions(baseUrl, headers, logEnabled, logTarget));
    }

    /**
     * Create a HttpClient instance with the specified options.
     *
     * @param options
     */
    public HttpClient(HttpClientOptions options) {
        this.options = options;
        this.inner = java.net.http.HttpClient.newBuilder().build();
    }

    /**
     * Helper method to send an asynchronous HTTP request.
     */
    private CompletableFuture<String> sendRequest(HttpRequest request) {
        return this.inner.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
    }

    /**
     * Helper method to send an asynchronous HTTP request.
     */
    private <T> CompletableFuture<T> sendRequest(HttpRequest request, Class<T> returnType) {
        return this.inner.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> gson.fromJson(response.body(), returnType));
    }

    /**
     * Helper method to build form URL-encoded data from a Map
     */
    private HttpRequest.BodyPublisher buildFormDataFromMap(Map<String, String> data) {
        return HttpRequest.BodyPublishers.ofString(
                data.entrySet().stream()
                        .map(entry -> entry.getKey() + "=" + entry.getValue())
                        .reduce((param1, param2) -> param1 + "&" + param2)
                        .orElse(""));
    }

    /**
     * Make a GET request and deserialize the response JSON.
     *
     * @param path       The path of the resource.
     * @param headers    Optional headers.
     * @param returnType The type to deserialize the response into.
     * @return A CompletableFuture containing the deserialized response object.
     */
    public <T> CompletableFuture<T> get(String path, Map<String, String> headers, Class<T> returnType) {
        var requestBuilder = HttpRequest.newBuilder();

        for (var entry : headers.entrySet()) {
            requestBuilder.header(entry.getKey(), entry.getValue());
        }

        var request = requestBuilder
                .uri(URI.create(this.options.baseUrl + path))
                .GET()
                .build();

        return sendRequest(request, returnType);
    }

    /**
     * Make a GET request and deserialize the response JSON.
     *
     * @param path       The path of the resource.
     * @param headers    Optional headers.
     * @param returnType The type to deserialize the response into.
     * @return A CompletableFuture containing the deserialized response object.
     */
    public <T> List<T> getArray(String path, Map<String, String> headers, Class<T[]> returnType) {
        var requestBuilder = HttpRequest.newBuilder();

        for (var entry : headers.entrySet()) {
            requestBuilder.header(entry.getKey(), entry.getValue());
        }

        var request = requestBuilder
                .uri(URI.create(this.options.baseUrl + path))
                .GET()
                .build();

        var response = sendRequest(request);
        T[] array = new Gson().fromJson(response.join(), returnType);

        return Arrays.asList(array);
    }

    /**
     * Make a POST request and deserialize the response JSON.
     *
     * @param path       The path of the resource.
     * @param body       The request body.
     * @param headers    Optional headers.
     * @param returnType The type to deserialize the response into.
     * @return A CompletableFuture containing the deserialized response object.
     */
    public <S, T> CompletableFuture<T> post(String path, S body, Map<String, String> headers, Class<T> returnType) {
        var requestBuilder = HttpRequest.newBuilder();

        for (var entry : headers.entrySet()) {
            requestBuilder.header(entry.getKey(), entry.getValue());
        }

        var request = requestBuilder
                .uri(URI.create(this.options.baseUrl + path))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(body)))
                .build();

        return sendRequest(request, returnType);
    }

    /**
     * Make a POST request with form URL-encoded data
     * and deserialize the response JSON.
     *
     * @param path       The path of the resource.
     * @param formData   The form data as a Map.
     * @param headers    Optional headers.
     * @param returnType The type to deserialize the response into.
     * @return A CompletableFuture containing the deserialized response object.
     */
    public <T> CompletableFuture<T> postFormUrlEncoded(String path, Map<String, String> formData,
            Map<String, String> headers, Class<T> returnType) {
        var requestBuilder = HttpRequest.newBuilder();

        for (var entry : headers.entrySet()) {
            requestBuilder.header(entry.getKey(), entry.getValue());
        }

        var request = requestBuilder
                .uri(URI.create(this.options.baseUrl + path))
                .POST(buildFormDataFromMap(formData))
                .build();

        return sendRequest(request, returnType);
    }

    /**
     * Make a PUT request and deserialize the response JSON.
     *
     * @param path       The path of the resource.
     * @param body       The request body.
     * @param headers    Optional headers.
     * @param returnType The type to deserialize the response into.
     * @return A CompletableFuture containing the deserialized response object.
     */
    public <S, T> CompletableFuture<T> put(String path, S body, Map<String, String> headers, Class<T> returnType) {
        var requestBuilder = HttpRequest.newBuilder();

        for (var entry : headers.entrySet()) {
            requestBuilder.header(entry.getKey(), entry.getValue());
        }

        var request = requestBuilder
                .uri(URI.create(this.options.baseUrl + path))
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(body)))
                .build();

        return sendRequest(request, returnType);
    }

    /**
     * Make a DELETE request.
     *
     * @param path    The path of the resource.
     * @param headers Optional headers.
     * @return A CompletableFuture containing the response body as a String.
     */
    public CompletableFuture<String> delete(String path, Map<String, String> headers) {
        var requestBuilder = HttpRequest.newBuilder();

        for (var entry : headers.entrySet()) {
            requestBuilder.header(entry.getKey(), entry.getValue());
        }

        var request = requestBuilder
                .uri(URI.create(this.options.baseUrl + path))
                .DELETE()
                .build();

        return sendRequest(request);
    }
}
