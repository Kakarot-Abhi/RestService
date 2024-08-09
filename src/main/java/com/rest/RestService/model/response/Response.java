package com.rest.RestService.model.response;

import lombok.Data;
import org.springframework.util.MultiValueMap;

@Data
public class Response {
    private String protocol;
    private String method;
    private String url;
    private Req req;
    private Res res;

    @Data
    class Req{
        private MultiValueMap<String, String> headers;
        private String body;
    }

    @Data
    class Res{
        private MultiValueMap<String, String> headers;
        private String body;
    }
}
