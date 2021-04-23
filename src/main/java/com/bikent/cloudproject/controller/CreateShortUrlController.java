package com.bikent.cloudproject.controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.bikent.cloudproject.model.CreateShortUrlRequestDto;
import com.bikent.cloudproject.model.CreateShortUrlResponseDto;
import com.bikent.cloudproject.model.GatewayResponse;
import com.bikent.cloudproject.service.UrlGenerationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.HashMap;

public class CreateShortUrlController implements RequestHandler<HashMap<String, Object>, GatewayResponse> {
	private final ObjectMapper mapper;
	private final Gson gson;
	private final UrlGenerationService urlGenerationService;
	private LambdaLogger logger;
	
	public CreateShortUrlController() {
		urlGenerationService = new UrlGenerationService();
		gson = new Gson();
		mapper = new ObjectMapper();
	}
	
	@Override
	public GatewayResponse handleRequest(HashMap<String, Object> map, Context context) {
		String lol = gson.toJson(map);
		logger = context.getLogger();
		logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
		logger.log("CONTEXT: " + gson.toJson(context));
		logger.log("OBJECT: " + gson.toJson(map));
		
		try {
			String body = String.valueOf(map.get("body"));
			CreateShortUrlRequestDto requestDto = mapper.readValue(body, CreateShortUrlRequestDto.class);
			CreateShortUrlResponseDto responseDto = urlGenerationService.createShortUrl(requestDto);
			return GatewayResponse.builder()
					.body(gson.toJson(responseDto))
					.statusCode(200)
					.headers(Collections.emptyMap())
					.isBase64Encoded(false).build();
		} catch (Exception e) {
			return GatewayResponse.builder()
					.body(e.getMessage())
					.statusCode(500)
					.headers(Collections.emptyMap())
					.isBase64Encoded(false).build();
		}
	}
}









//	@Override
//	public GatewayResponse handleRequest(HashMap<String, Object> map, Context context) {
//		LambdaLogger logger = context.getLogger();
//		Gson gson = new Gson();
//		System.out.println(map);
//		String lol = gson.toJson(map);
//		logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
//		logger.log("CONTEXT: " + gson.toJson(context));
//		logger.log("object: " + gson.toJson(map));
//		String message = "wtf";
//		System.out.println(message);
////		GatewayResponse response = new GatewayResponse(message, 
////				302, 
////				Collections.singletonMap("Location", "http://www.facebook.com"),
////				false);
//		
//		
//		
//		GatewayResponse response = new GatewayResponse(lol,
//				200,
//				Collections.singletonMap("zzz","udiho"),
//				false);
//		return response;
//	}


//		GatewayResponse response = new GatewayResponse(message, 
//				302, 
//				Collections.singletonMap("Location", "http://www.facebook.com"),
//				false);