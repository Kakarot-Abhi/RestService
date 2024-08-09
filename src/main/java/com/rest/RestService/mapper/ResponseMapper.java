package com.rest.RestService.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {
    public RestRunnerResponse toResponse(ResponseEntity<Object> responseEntity ){
        return new RestRunnerResponse(responseEntity.getStatusCode(), responseEntity.getBody());
    }
}

