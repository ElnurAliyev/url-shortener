package com.bikent.cloudproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GatewayResponse {
	private String body;
	private Integer statusCode;
	private Map<String, String> headers;
	private boolean isBase64Encoded;
}
