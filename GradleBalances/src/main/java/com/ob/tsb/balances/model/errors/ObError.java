package com.ob.tsb.balances.model.errors;

import org.springframework.http.HttpStatus;

import java.beans.Transient;
import java.util.List;

/**
 *
 * @param code
 * @param Id
 * @param Message
 * @param Errors
 */
public record ObError(
        @Transient HttpStatus httpStatus,
        String code, String Id, String Message, List<Description> Errors) {
    public record Description(String ErrorCode, String Message, String Path, String Url) {
    }
}
