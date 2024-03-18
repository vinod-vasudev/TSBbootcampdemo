package com.ob.tsb.balances.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorDesc {

    public static Mono<Throwable> handle5xxClientError(String msg, ClientResponse clientResponse) {
        log.info("5xx error msg {}", msg);
        HttpStatusCode httpStatusCode = clientResponse.statusCode();
        switch (httpStatusCode) {
            case INTERNAL_SERVER_ERROR -> throw new CustomException(" Internal Server Error");
            case SERVICE_UNAVAILABLE -> throw new CustomException(" Server is unavailable");
            default -> throw new CustomException(" have Server Error");
        }
    }

    public static Mono<Throwable> handle4xxClientError(String msg, ClientResponse clientResponse) {
        HttpStatusCode httpStatusCode = clientResponse.statusCode();
        switch (httpStatusCode){
            case NOT_FOUND ->  throw new ResourceNotFoundException(httpStatusCode, msg + " Client API Not Found" );
            case UNAUTHORIZED ->  throw new UnauthorizedException(msg + " Authorization Failed");
            case NOT_ACCEPTABLE -> throw new ResourceNotFoundException(httpStatusCode, msg + " Client Url Not Accepted" );
            default -> throw new ResourceNotFoundException(httpStatusCode, msg);
        }
    }
}
