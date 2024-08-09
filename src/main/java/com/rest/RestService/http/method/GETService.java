package com.rest.RestService.http.method;

import com.rest.RestService.model.Request;
import com.rest.RestService.model.response.Response;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Component
public class GETService {

    RestTemplate restTemplate = new RestTemplate();

    public String getRestTemplate(Request request) {

        RestClient restClient = RestClient.create();
        restClient.post().retrieve();

        RequestEntity<String> entity = new RequestEntity<>(HttpMethod.GET, request.getUrl());

        ResponseEntity<String> response = restTemplate.exchange(entity, String.class);

        Response finalResp = new Response();

        finalResp.setMethod(request.getMethod().name());
        finalResp.setUrl(request.getUrl().toString());
//        finalResp.setProtocol(response.g);

        return response.getBody();
    }
}
