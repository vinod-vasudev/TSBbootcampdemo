/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.5.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.ob.tsb.accounts.api;

import com.ob.tsb.accounts.response.AccountsResponse;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-12T00:03:17.859005+05:30[Asia/Calcutta]")
@Validated
@Tag(name = "accounts", description = "the accounts API")
public interface AccountsApi {

    /**
     * GET /api/v1/accounts : Get full list of accounts for AISP
     *
     * @param authorization  (optional)
     * @param xFapiAuthDate  (optional)
     * @param xFapiCustomerIpAddress  (optional)
     * @param xFapiInteractionId  (optional)
     * @param accept  (optional)
     * @return Accounts information retrieved successfully (status code 200)
     */
    @Operation(
        operationId = "accounts",
        summary = "Get full list of accounts for AISP",
        tags = { "accounts" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Accounts information retrieved successfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = AccountsResponse.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/v1/accounts",
        produces = { "application/json" }
    )
    default Mono<ResponseEntity<AccountsResponse>> _accounts(
        @Parameter(name = "Authorization", description = "", in = ParameterIn.HEADER) @RequestHeader(value = "Authorization", required = false) String authorization,
        @Parameter(name = "x-fapi-auth-date", description = "", in = ParameterIn.HEADER) @RequestHeader(value = "x-fapi-auth-date", required = false) String xFapiAuthDate,
        @Parameter(name = "x-fapi-customer-ip-address", description = "", in = ParameterIn.HEADER) @RequestHeader(value = "x-fapi-customer-ip-address", required = false) String xFapiCustomerIpAddress,
        @Parameter(name = "x-fapi-interaction-id", description = "", in = ParameterIn.HEADER) @RequestHeader(value = "x-fapi-interaction-id", required = false) String xFapiInteractionId,
        @Parameter(name = "Accept", description = "", in = ParameterIn.HEADER) @RequestHeader(value = "Accept", required = false) String accept,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        return accounts(authorization, xFapiAuthDate, xFapiCustomerIpAddress, xFapiInteractionId, accept, exchange);
    }

    // Override this method
    default  Mono<ResponseEntity<AccountsResponse>> accounts(String authorization, String xFapiAuthDate, String xFapiCustomerIpAddress, String xFapiInteractionId, String accept,  final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                String exampleString = "{ \"Meta\" : { \"TotalPages\" : 0 }, \"Links\" : { \"Self\" : \"Self\" }, \"Data\" : { \"Account\" : [ { \"Status\" : \"Status\", \"StatusUpdateDateTime\" : \"2000-01-23T04:56:07.000+00:00\", \"Account\" : [ { \"SecondaryIdentification\" : \"SecondaryIdentification\", \"SchemeName\" : \"SchemeName\", \"Identification\" : \"Identification\", \"Name\" : \"Name\" }, { \"SecondaryIdentification\" : \"SecondaryIdentification\", \"SchemeName\" : \"SchemeName\", \"Identification\" : \"Identification\", \"Name\" : \"Name\" } ], \"OpeningDate\" : \"OpeningDate\", \"AccountId\" : \"AccountId\", \"Currency\" : \"Currency\", \"AccountType\" : \"AccountType\", \"AccountSubType\" : \"AccountSubType\", \"Nickname\" : \"Nickname\" }, { \"Status\" : \"Status\", \"StatusUpdateDateTime\" : \"2000-01-23T04:56:07.000+00:00\", \"Account\" : [ { \"SecondaryIdentification\" : \"SecondaryIdentification\", \"SchemeName\" : \"SchemeName\", \"Identification\" : \"Identification\", \"Name\" : \"Name\" }, { \"SecondaryIdentification\" : \"SecondaryIdentification\", \"SchemeName\" : \"SchemeName\", \"Identification\" : \"Identification\", \"Name\" : \"Name\" } ], \"OpeningDate\" : \"OpeningDate\", \"AccountId\" : \"AccountId\", \"Currency\" : \"Currency\", \"AccountType\" : \"AccountType\", \"AccountSubType\" : \"AccountSubType\", \"Nickname\" : \"Nickname\" } ] } }";
                result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
                break;
            }
        }
        return result.then(Mono.empty());

    }


    /**
     * GET /api/v1/accounts/{accountId} : Get Account details by account Id
     *
     * @param accountId Id of the account to get (required)
     * @param authorization  (optional)
     * @param xFapiAuthDate  (optional)
     * @param xFapiCustomerIpAddress  (optional)
     * @param xFapiInteractionId  (optional)
     * @param accept  (optional)
     * @return Account information retrieved successfully (status code 200)
     */
    @Operation(
        operationId = "accountsById",
        summary = "Get Account details by account Id",
        tags = { "accounts" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Account information retrieved successfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = AccountsResponse.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/v1/accounts/{accountId}",
        produces = { "application/json" }
    )
    default Mono<ResponseEntity<AccountsResponse>> _accountsById(
        @Parameter(name = "accountId", description = "Id of the account to get", required = true, in = ParameterIn.PATH) @PathVariable("accountId") String accountId,
        @Parameter(name = "Authorization", description = "", in = ParameterIn.HEADER) @RequestHeader(value = "Authorization", required = false) String authorization,
        @Parameter(name = "x-fapi-auth-date", description = "", in = ParameterIn.HEADER) @RequestHeader(value = "x-fapi-auth-date", required = false) String xFapiAuthDate,
        @Parameter(name = "x-fapi-customer-ip-address", description = "", in = ParameterIn.HEADER) @RequestHeader(value = "x-fapi-customer-ip-address", required = false) String xFapiCustomerIpAddress,
        @Parameter(name = "x-fapi-interaction-id", description = "", in = ParameterIn.HEADER) @RequestHeader(value = "x-fapi-interaction-id", required = false) String xFapiInteractionId,
        @Parameter(name = "Accept", description = "", in = ParameterIn.HEADER) @RequestHeader(value = "Accept", required = false) String accept,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        return accountsById(accountId, authorization, xFapiAuthDate, xFapiCustomerIpAddress, xFapiInteractionId, accept, exchange);
    }

    // Override this method
    default  Mono<ResponseEntity<AccountsResponse>> accountsById(String accountId, String authorization, String xFapiAuthDate, String xFapiCustomerIpAddress, String xFapiInteractionId, String accept,  final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                String exampleString = "{ \"Meta\" : { \"TotalPages\" : 0 }, \"Links\" : { \"Self\" : \"Self\" }, \"Data\" : { \"Account\" : [ { \"Status\" : \"Status\", \"StatusUpdateDateTime\" : \"2000-01-23T04:56:07.000+00:00\", \"Account\" : [ { \"SecondaryIdentification\" : \"SecondaryIdentification\", \"SchemeName\" : \"SchemeName\", \"Identification\" : \"Identification\", \"Name\" : \"Name\" }, { \"SecondaryIdentification\" : \"SecondaryIdentification\", \"SchemeName\" : \"SchemeName\", \"Identification\" : \"Identification\", \"Name\" : \"Name\" } ], \"OpeningDate\" : \"OpeningDate\", \"AccountId\" : \"AccountId\", \"Currency\" : \"Currency\", \"AccountType\" : \"AccountType\", \"AccountSubType\" : \"AccountSubType\", \"Nickname\" : \"Nickname\" }, { \"Status\" : \"Status\", \"StatusUpdateDateTime\" : \"2000-01-23T04:56:07.000+00:00\", \"Account\" : [ { \"SecondaryIdentification\" : \"SecondaryIdentification\", \"SchemeName\" : \"SchemeName\", \"Identification\" : \"Identification\", \"Name\" : \"Name\" }, { \"SecondaryIdentification\" : \"SecondaryIdentification\", \"SchemeName\" : \"SchemeName\", \"Identification\" : \"Identification\", \"Name\" : \"Name\" } ], \"OpeningDate\" : \"OpeningDate\", \"AccountId\" : \"AccountId\", \"Currency\" : \"Currency\", \"AccountType\" : \"AccountType\", \"AccountSubType\" : \"AccountSubType\", \"Nickname\" : \"Nickname\" } ] } }";
                result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
                break;
            }
        }
        return result.then(Mono.empty());

    }


    /**
     * GET /api/v1/health-check : Account MS Health Check
     *
     * @return Checking Accounts MS Health (status code 200)
     *         or Not Found (status code 404)
     */
    @Operation(
        operationId = "healthCheck",
        summary = "Account MS Health Check",
        tags = { "accounts" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Checking Accounts MS Health", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/v1/health-check",
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
