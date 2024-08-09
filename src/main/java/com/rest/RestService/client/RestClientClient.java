package com.rest.RestService.client;

import com.rest.RestService.http.CustomInterCeptor;
import com.rest.RestService.mapper.RestDataCapture;
import com.rest.RestService.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Qualifier("restClientClient")
public class RestClientClient extends AbstractIClient<Request, ResponseEntity<Object>, RestDataCapture> {

    RestClient restClient;

//    @Bean
    public RestTemplate restTemplate(){
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add(new CustomInterCeptor());
        return template;
    }

    @Autowired
    protected RestClientClient(IMapper<Request, ResponseEntity<Object>, RestDataCapture> mapper) {
        super(mapper);
        restClient = RestClient.create(restTemplate());
    }

    private ResponseEntity<Object> getResponse(Request request, HttpMethod method) {
        AtomicReference<ResponseEntity<Object>> reference = new AtomicReference<>();
        ResponseEntity<Object> responseEntity = restClient.method(method)
                .uri(request.getUrl())
                .headers(t -> t.addAll(request.getHeaders()))
                .body(request.getBody())
                .retrieve()
                .onStatus(HttpStatusCode::isError, (_, response) -> handleError(response, reference))
                .toEntity(Object.class);

        return Optional.ofNullable(reference.get())
                .orElse(responseEntity);
    }

    private void handleError(ClientHttpResponse response, AtomicReference<ResponseEntity<Object>> reference) throws IOException {
        reference.set(
                new ResponseEntity<>(RestDataCapture.builder()
                        .resBody(new String(response.getBody().readAllBytes()))
                        .code(response.getStatusCode().value())
                        .codeDesc(response.getStatusText())
                        .responseHeader(response.getHeaders())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /**
     * @param request
     * @param mapper
     * @return
     */
    @Override
    protected RestDataCapture delete(Request request, IMapper<Request, ResponseEntity<Object>, RestDataCapture> mapper) {
        ResponseEntity<Object> entity = getResponse(request, HttpMethod.DELETE);
        return mapper.isError(request, entity) ? mapper.toError(request, entity) : mapper.toData(request, entity);
    }

    /**
     * @param request
     * @param mapper
     * @return
     */
    @Override
    protected RestDataCapture get(Request request, IMapper<Request, ResponseEntity<Object>, RestDataCapture> mapper) {
        ResponseEntity<Object> entity = getResponse(request, HttpMethod.GET);
        return mapper.isError(request, entity) ? mapper.toError(request, entity) : mapper.toData(request, entity);
    }

    /**
     * @param request
     * @param mapper
     * @return
     */
    @Override
    protected RestDataCapture post(Request request, IMapper<Request, ResponseEntity<Object>, RestDataCapture> mapper) {
        ResponseEntity<Object> entity = getResponse(request, HttpMethod.POST);
        return mapper.isError(request, entity) ? mapper.toError(request, entity) : mapper.toData(request, entity);
    }

    /**
     * @param request
     * @param mapper
     * @return
     */
    @Override
    protected RestDataCapture put(Request request, IMapper<Request, ResponseEntity<Object>, RestDataCapture> mapper) {
        ResponseEntity<Object> entity = getResponse(request, HttpMethod.PUT);
        return mapper.isError(request, entity) ? mapper.toError(request, entity) : mapper.toData(request, entity);
    }
}
