package com.tsb.accountsDetails.model.request.token;


import lombok.Builder;


@Builder
public record TokenRequest(
        String clientId,
        String clientSecret
) {

}
