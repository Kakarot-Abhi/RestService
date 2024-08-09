package com.rest.RestService.controller;

import com.rest.RestService.model.Request;
import com.rest.RestService.service.IRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
public class RestControllers {

    @Autowired
    IRestService service;

    @PostMapping("/get")
    public ResponseEntity<Object> get(@RequestBody Request request) {

        Object entity = service.get(request);
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/post")
    public ResponseEntity<Object> post(@RequestBody Request request) {

        Object entity = service.post(request);
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> delete(@RequestBody Request request) {

        Object entity = service.delete(request);
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/put")
    public ResponseEntity<Object> put(@RequestBody Request request) {

        Object entity = service.put(request);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("server is up");
    }

}
