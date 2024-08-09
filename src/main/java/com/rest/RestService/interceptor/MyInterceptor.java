package com.rest.RestService.interceptor;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class MyInterceptor implements ClientHttpRequestInterceptor {

//    @Override
    public ClientHttpResponse intercept1(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // Your interceptor logic here (e.g., adding headers, logging)
        return execution.execute(request, body);
    }

    /**
     * @param request   the request, containing method, URI, and headers
     * @param body      the body of the request
     * @param execution the request execution
     * @return
     * @throws IOException
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        return null;
    }
}