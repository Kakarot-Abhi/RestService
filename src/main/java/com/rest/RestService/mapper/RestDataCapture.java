package com.rest.RestService.mapper;

import lombok.*;
import org.springframework.util.MultiValueMap;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RestDataCapture{
    private MultiValueMap<String, String> requestHeader;
    private MultiValueMap<String, String> responseHeader;
    private String url;
    private Object resBody;
    private Object reqBody;
    private String method;
    private int code;
    private String codeDesc;
    private String codeDesc1;
    private long time;
}
