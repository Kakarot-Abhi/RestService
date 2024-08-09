package com.rest.RestService.service.impl;

import com.rest.RestService.client.AbstractIClient;
import com.rest.RestService.client.RestClientClient;
import com.rest.RestService.mapper.RestDataCapture;
import com.rest.RestService.model.Request;
import com.rest.RestService.service.IRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RestService implements IRestService {

    @Autowired
    @Qualifier("restClientClient")
    AbstractIClient<Request, ResponseEntity<Object>, RestDataCapture> client;

    public RestDataCapture get(Request request) {
        return client.get(request);
    }

    public RestDataCapture post(Request request) {
        return client.post(request);
    }

    public RestDataCapture put(Request request) {
        return client.put(request);
    }

    public RestDataCapture delete(Request request) {
        return client.delete(request);
    }

}
