/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.5.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.ob.tsb.balances.api;

import com.ob.tsb.balances.model.httpstatus.*;
import com.ob.tsb.balances.model.ob.OBReadBalance;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-08T12:35:43.065827900+05:30[Asia/Calcutta]")
@Validated
@Tag(name = "balances", description = "the balances API")
public interface BalancesApi {

    /**
     * GET /api/v1/balances : Get balance details
     *
     * @return Balances information retrieved successfully (status code 200)
     *         or Bad Request (status code 400)
     *         or Unauthorised access (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     *         or Internal Server Error (status code 500)
     */
    @Operation(
        operationId = "balances",
        summary = "Get balance details",
        tags = { "balances" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Balances information retrieved successfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = OBReadBalance.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Model400.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorised access", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Model401.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Model403.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Model404.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Model500.class))
            })
        },
        security = {
            @SecurityRequirement(name = "jwt")
        }
    )
    @GetMapping(
        value = "/api/v1/balances",
        produces = { "application/json" }
    )
    default Mono<ResponseEntity<OBReadBalance>> balancesDefault(
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        return balances(exchange);
    }

    // Override this method
    default  Mono<ResponseEntity<OBReadBalance>> balances( final ServerWebExchange exchange) {
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
     * GET /api/v1/accounts/{AccountId}/balances : Get balance details by Id
     *
     * @param accountId Account ID (required)
     * @return Balance detail by Id information retrieved successfully (status code 200)
     *         or Bad Request (status code 400)
     *         or Unauthorised access (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     *         or Internal Server Error (status code 500)
     */
    @Operation(
        operationId = "balancesById",
        summary = "Get balance details by Id",
        tags = { "balances" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Balance detail by Id information retrieved successfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = OBReadBalance.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Model400.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorised access", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Model401.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Model403.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Model404.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Model500.class))
            })
        },
        security = {
            @SecurityRequirement(name = "jwt")
        }
    )
    @GetMapping(
        value = "/api/v1/accounts/{AccountId}/balances",
        produces = { "application/json" }
    )
    default Mono<ResponseEntity<OBReadBalance>> balancesByIdDefult(
        @Parameter(name = "AccountId", description = "Account ID", required = true, in = ParameterIn.PATH) @PathVariable("AccountId") String accountId,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        return balancesById(accountId, exchange);
    }

    // Override this method
    default  Mono<ResponseEntity<OBReadBalance>> balancesById(String accountId,  final ServerWebExchange exchange) {
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
     * GET /api/v1/health-check : Balance MS Health Check
     *
     * @return Checking Balances MS Health (status code 200)
     *         or Not Found (status code 404)
     */
    @Operation(
        operationId = "healthCheck",
        summary = "Balance MS Health Check",
        tags = { "balances" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Checking Balances MS Health", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found")
        },
        security = {
            @SecurityRequirement(name = "jwt")
        }
    )
    @GetMapping(
        value = "/api/v1/health-check",
        produces = { "application/json" }
    )
    default Mono<ResponseEntity<String>> healthCheckDefault(
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
