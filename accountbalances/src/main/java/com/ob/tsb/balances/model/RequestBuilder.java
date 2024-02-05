package com.ob.tsb.balances.model;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;

public record RequestBuilder(String url, HttpMethod method, HttpHeaders headers, LinkedMultiValueMap payload, Object responseTYpe){ }