package com.rest.RestService.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public class RestRunnerRequest {





    private HttpStatusCode status;
    private Object body;

    @Override
    public String toString() {
        return "RestRunnerResponse{" +
                "status=" + status +
                ", body=" + body +
                '}';
    }
}
