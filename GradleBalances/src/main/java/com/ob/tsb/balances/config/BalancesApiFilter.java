package com.ob.tsb.balances.config;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ob.tsb.balances.exception.CustomException;
import com.ob.tsb.balances.exception.ObCustomException;
import com.ob.tsb.balances.model.errors.ErrorCodes;
import com.ob.tsb.balances.model.errors.ObError;
import com.ob.tsb.balances.service.AuthService;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

import static com.ob.tsb.balances.util.ApplicationConstants.*;



@Component
@Order(1)
public class BalancesApiFilter implements WebFilter{
    private final AuthService authService;
    private final ObjectMapper objectMapper;



    public BalancesApiFilter(AuthService authService, ObjectMapper objectMapper) {
        this.authService = authService;
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();
        if (path.contains("health") || path.contains("mock") || path.contains("consent") || path.contains(SWAGGER_DOC) || path.contains(SWAGGER_UI_PATH) || path.contains(SWAGGER_RESOURCES_PATH)) {
            return chain.filter(exchange);
        }

        HttpHeaders httpHeaders = exchange.getRequest().getHeaders();

        String token = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);

        if (token == null || token.isEmpty() || !authService.validateToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            DataBufferFactory df = new DefaultDataBufferFactory();
            try {
                ObCustomException ob = new ObCustomException(new ObError(ErrorCodes.UNAUTHORIZED_HTTP_401.getHttpStatus(),ErrorCodes.UNAUTHORIZED_HTTP_401.getErrorCode(), UUID.randomUUID().toString(), "Request is Unauthorised", List.of(new ObError.Description(ErrorCodes.UNAUTHORIZED_HTTP_401.getErrorCode(), ERROR_DEFAULT_MSG, "/api/v1/balances", "https://api.example.com/docs/errors/"))), "Request is Unauthorised");
                JsonNode dataStr = objectMapper.convertValue(ob.getObError(), JsonNode.class);
                byte[] data = objectMapper.writeValueAsBytes(dataStr);
                DataBuffer dataBufferFactory = df.wrap(data);
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
                return exchange.getResponse().writeWith(Mono.just(dataBufferFactory));
            } catch (Exception e) {
                throw new CustomException("Something went wrong");
            }
        }
        return chain.filter(exchange);
    }
}
