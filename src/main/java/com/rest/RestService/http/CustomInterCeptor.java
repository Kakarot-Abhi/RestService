package com.rest.RestService.http;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

public class CustomInterCeptor implements ClientHttpRequestInterceptor {
    /**
     * @param request   the request, containing method, URI, and headers
     * @param body      the body of the request
     * @param execution the request execution
     * @return
     * @throws IOException
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            // Measure HTTP data time
            Instant dataStart = Instant.now();
            ClientHttpResponse response = execution.execute(request, body);
            Instant dataEnd = Instant.now();
            Duration dataDuration = Duration.between(dataStart, dataEnd);

        // Calculate body sizes
        long requestBodySize = body.length;
        ByteArrayOutputStream responseBodyStream = new ByteArrayOutputStream();
        response.getBody().transferTo(responseBodyStream);
        long responseBodySize = responseBodyStream.size();

            response.getHeaders().put("response.total.time" , Collections.singletonList(String.valueOf(dataDuration.toMillis() )));
            response.getHeaders().put("Data received = " , Collections.singletonList(String.valueOf(responseBodySize )));
            response.getBody().available();

            return response;
    }
}
