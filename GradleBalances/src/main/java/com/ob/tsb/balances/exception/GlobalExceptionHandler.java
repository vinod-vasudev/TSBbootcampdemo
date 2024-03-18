package com.ob.tsb.balances.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ServerResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ServerResponse.status(HttpStatus.NOT_FOUND)
                .bodyValue(new CustomException("Resource Not  Found"));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex) {
        return new ResponseEntity<>("unauthorized access   ",HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(ObCustomException.class)
    public ResponseEntity<Object> handleObCustomExceptionException(ObCustomException ex) {
        return new ResponseEntity<>(ex.getObError(),ex.getObError().httpStatus());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ServerResponse> globalExceptionHandler(Exception ex, WebRequest request) {
        log.error("error while processing API {}", request);
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .bodyValue(new CustomException("Something went wrong"));
    }



}
