/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.5.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.tsb.banking.api;

import com.tsb.banking.model.BalancesResponse;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.codec.multipart.Part;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-27T16:06:24.218605700+05:30[Asia/Calcutta]")
@Validated
@Tag(name = "balances", description = "the balances API")
public interface BalanceApi {

    /**
     * GET /balance/v1/balances : Get balance details
     *
     * @return Balances information retrieved successfully (status code 200)
     */
    @Operation(
        operationId = "balances",
        summary = "Get balance details",
        tags = { "balances" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Balances information retrieved successfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = BalancesResponse.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/balance/v1/balances",
        produces = { "application/json" }
    )
    default Mono<ResponseEntity<BalancesResponse>> _balances(
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        return balances(exchange);
    }

    // Override this method
    default  Mono<ResponseEntity<BalancesResponse>> balances( final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                String exampleString = "{ \"Meta\" : { \"TotalPages\" : 0 }, \"Links\" : { \"Self\" : \"Self\" }, \"Data\" : { \"Balance\" : [ { \"Type\" : \"Type\", \"AccountId\" : \"AccountId\", \"CreditLine\" : [ { \"Type\" : \"Type\", \"Included\" : true, \"Amount\" : { \"Amount\" : \"Amount\", \"Currency\" : \"Currency\" } }, { \"Type\" : \"Type\", \"Included\" : true, \"Amount\" : { \"Amount\" : \"Amount\", \"Currency\" : \"Currency\" } } ], \"Amount\" : { \"Amount\" : \"Amount\", \"Currency\" : \"Currency\" }, \"CreditDebitIndicator\" : \"CreditDebitIndicator\", \"DateTime\" : \"2000-01-23T04:56:07.000+00:00\" }, { \"Type\" : \"Type\", \"AccountId\" : \"AccountId\", \"CreditLine\" : [ { \"Type\" : \"Type\", \"Included\" : true, \"Amount\" : { \"Amount\" : \"Amount\", \"Currency\" : \"Currency\" } }, { \"Type\" : \"Type\", \"Included\" : true, \"Amount\" : { \"Amount\" : \"Amount\", \"Currency\" : \"Currency\" } } ], \"Amount\" : { \"Amount\" : \"Amount\", \"Currency\" : \"Currency\" }, \"CreditDebitIndicator\" : \"CreditDebitIndicator\", \"DateTime\" : \"2000-01-23T04:56:07.000+00:00\" } ] } }";
                result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
                break;
            }
        }
        return result.then(Mono.empty());

    }


    /**
     * GET /balance/v1/health-check : Balance Health Check
     *
     * @return Checking Balances MS Health (status code 200)
     *         or Not Found (status code 404)
     */
    @Operation(
        operationId = "healthCheck",
        summary = "Balance Health Check",
        responses = {
            @ApiResponse(responseCode = "200", description = "Checking Balances MS Health", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/balance/v1/health-check",
        produces = { "application/json" }
    )
    default Mono<ResponseEntity<String>> _healthCheck(
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        return healthCheck(exchange);
    }

    // Override this method
    default  Mono<ResponseEntity<String>> healthCheck( final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }

}
