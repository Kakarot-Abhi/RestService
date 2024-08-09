package com.rest.RestService.service;

import com.rest.RestService.mapper.RestDataCapture;
import com.rest.RestService.model.Request;

public interface IRestService {
    RestDataCapture get(Request request);
    RestDataCapture post(Request request);
    RestDataCapture put(Request request);
    RestDataCapture delete(Request request);
}
