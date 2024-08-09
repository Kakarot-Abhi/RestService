package com.rest.RestService.client;

import com.rest.RestService.mapper.RestDataCapture;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class ErrorHandler implements ResponseErrorHandler {
    /**
     * @param response the response to inspect
     * @return
     * @throws IOException
     */
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return true;
    }

    /**
     * @param response the response with the error
     * @throws IOException
     */
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        RestDataCapture restDataCapture = RestDataCapture.builder()
                .resBody(new String(response.getBody().readAllBytes()))
                .code(response.getStatusCode().value())
                .codeDesc(response.getStatusText())
                .responseHeader(response.getHeaders())
                .build();
        System.err.println(restDataCapture);


    }
}
