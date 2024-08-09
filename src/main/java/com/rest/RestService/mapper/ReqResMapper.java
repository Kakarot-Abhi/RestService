package com.rest.RestService.mapper;

import com.rest.RestService.client.IMapper;
import com.rest.RestService.model.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReqResMapper implements IMapper<Request, ResponseEntity<Object>, RestDataCapture> {

    @Override
    public RestDataCapture toData(Request request, ResponseEntity<Object> entity){
        return RestDataCapture.builder()
                .resBody(entity.getBody())
                .reqBody(request.getBody())
                .code(entity.getStatusCode().value())
                .codeDesc(((HttpStatus) entity.getStatusCode()).name())
                .responseHeader(entity.getHeaders())
                .url(request.getUrl().toString())
                .method(String.valueOf(request.getMethod()))
                .requestHeader(request.getHeaders())
                .build();
    }

    /**
     * @param request
     * @param responseEntity
     * @return
     */
    @Override
    public RestDataCapture toError(Request request, ResponseEntity<Object> responseEntity) {
        RestDataCapture entity = (RestDataCapture) responseEntity.getBody();
        return RestDataCapture.builder()
                .resBody(entity.getResBody())
                .reqBody(request.getBody())
                .code(entity.getCode())
                .codeDesc(entity.getCodeDesc())
                .codeDesc1(entity.getCodeDesc1())
                .responseHeader(entity.getResponseHeader())
                .url(request.getUrl().toString())
                .method(String.valueOf(request.getMethod()))
                .requestHeader(request.getHeaders())
                .build();
    }

    /**
     * @param request
     * @param responseEntity
     * @return
     */
    @Override
    public boolean isError(Request request, ResponseEntity<Object> responseEntity) {
        return Optional.ofNullable(responseEntity)
                .map(ResponseEntity::getBody)
                .filter(RestDataCapture.class::isInstance).isPresent();
    }

//    @SneakyThrows
//    public RestDataCapture toError(Request request, RestDataCapture entity) {
//        return RestDataCapture.builder()
//                .resBody(entity.getResBody())
//                .reqBody(request.getBody())
//                .code(entity.getCode())
//                .codeDesc(entity.getCodeDesc())
//                .codeDesc1(entity.getCodeDesc1())
//                .responseHeader(entity.getResponseHeader())
//                .url(request.getUrl().toString())
//                .method(String.valueOf(request.getMethod()))
//                .requestHeader(request.getHeaders())
//                .build();
//    }
}


