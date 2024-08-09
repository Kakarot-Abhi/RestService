package com.rest.RestService;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@SpringBootApplication
public class RestServiceApplication {

	@Autowired
	private HttpClientService test;

	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}


	@EventListener(ApplicationReadyEvent.class)
	public void invoke() throws IOException {
		test.sendRequest("https://echo.free.beeceptor.com?adgjhs=4345&hdkhfs=sjdgjs");
	}

}
