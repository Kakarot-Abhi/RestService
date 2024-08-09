package com.rest.RestService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rest.RestService.mapper.ResponseMapper;
import com.rest.RestService.mapper.RestDataCapture;
import com.rest.RestService.mapper.RestRunnerResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.function.Consumer;

@Component
public class Test {

    public void testGet() throws JsonProcessingException {
        RestClient restClient = RestClient.create();
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("a", "b");
        headers.add("b", "b");
        headers.add("c", "b");
        headers.add("d", "b");
        headers.add("e", "b");
        headers.add( "Content-Type", "application/json");

        Consumer<HttpHeaders> head = (t) -> t.addAll(headers);

//        ResponseEntity<Object> entity = restClient.get()
//                .uri("https://echo.free.beeceptor.com?adgjhs=4345&hdkhfs=sjdgjs")
//                .headers(head)
//                .retrieve()
//                .toEntity(Object.class);

        String url = "https://echo.free.beeceptor.com?adgjhs=4345&hdkhfs=sjdgjs";
        HttpMethod httpMethod = HttpMethod.GET;
        String body1 = "{\"sdfdsf\" : \"dssaf\"}";
        ResponseEntity<Object> entityq = restClient.method(httpMethod)
                .uri(url)
                .headers(head)
                .body(body1)
                .retrieve()
                .toEntity(Object.class);

        RestDataCapture restDataCapture = RestDataCapture.builder()
                .url(url)
                .method(httpMethod.name())
                .code(entityq.getStatusCode().value())
                .requestHeader(headers)
                .responseHeader(entityq.getHeaders())
                .build();



        RestRunnerResponse response = new ResponseMapper().toResponse(entityq);

        Object body = entityq.getBody();


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String writeValueAsString = objectMapper
                .writer().withDefaultPrettyPrinter()
                .writeValueAsString(restDataCapture);
//                .writerWithDefaultPrettyPrinter().writeValueAsString(restDataCapture);


        System.err.println(writeValueAsString);

        System.err.println(body);
        System.err.println(response);
    }



}
