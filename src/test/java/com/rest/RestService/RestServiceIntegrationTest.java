package com.rest.RestService;

import com.rest.RestService.mapper.RestDataCapture;
import com.rest.RestService.model.Request;
import com.rest.RestService.service.IRestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.net.URI;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RestServiceIntegrationTest {

    @Autowired
    private IRestService restService;

    private Request getRequest;
    private Request postRequest;
    private Request putRequest;
    private Request deleteRequest;

    @BeforeEach
    void setUp() {
        getRequest = new Request();
        getRequest.setUrl(URI.create("https://api.restful-api.dev/objects"));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put( "Content-Type", Collections.singletonList("application/json"));
        getRequest.setHeaders(httpHeaders);
        getRequest.setBody("");
        getRequest.setMethod(HttpMethod.GET);

        postRequest = new Request();
        postRequest.setUrl(URI.create("https://api.restful-api.dev/objects"));
        postRequest.setHeaders(httpHeaders);
        postRequest.setBody("{ \"name\": \"Apple MacBook Pro 16\",   \"data\": {      \"year\": 2019,      \"price\": 1849.99,      \"CPU model\": \"Intel ore i9\",      \"Hard disk size\": \"1 TB\"   }}");
        postRequest.setMethod(HttpMethod.POST);
    }

    @Test
    void testGet() {
        RestDataCapture response = restService.get(getRequest);
        assertEquals(200, response.getCode());
    }

    @Test
    void testPost() {
        RestDataCapture response = restService.post(postRequest);
        assertEquals(200, response.getCode());
    }

//    @Test
//    void testPut() {
//        RestDataCapture response = restService.put(request);
//        assertEquals(200, response.getCode());
//    }
//
//    @Test
//    void testDelete() {
//        RestDataCapture response = restService.delete(request);
//        assertEquals(204, response.getCode());
//    }
}
