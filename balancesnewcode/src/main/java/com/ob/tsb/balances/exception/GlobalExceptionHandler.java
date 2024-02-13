package com.ob.tsb.balances.exception;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ServerResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ServerResponse.status(HttpStatus.NOT_FOUND)
                .bodyValue(new CustomException(ex.getMessage(), "Resource Not  Found"));
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException ex) {
        return new ResponseEntity<>("unauthorized access   ",HttpStatus.FORBIDDEN);
        /*return ServerResponse.status(HttpStatus.FORBIDDEN)
                .bodyValue(new CustomException(ex.getMessage(), "Permission denied"));*/
    }

    @ExceptionHandler(ObCustomException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> handleObCustomExceptionException(ObCustomException ex) {
        return new ResponseEntity<>(ex.getObError(),HttpStatus.NOT_ACCEPTABLE);
        /*return ServerResponse.status(HttpStatus.FORBIDDEN)
                .bodyValue(new CustomException(ex.getMessage(), "Permission denied"));*/
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ServerResponse> globalExceptionHandler(Exception ex, WebRequest request) {
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .bodyValue(new CustomException(ex.getMessage(), "Something went wrong"));
    }



}
