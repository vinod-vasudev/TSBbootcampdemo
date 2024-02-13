package com.ob.tsb.balances.model.errors;

import java.util.List;

/**
 *
 * @param Code
 * @param Id
 * @param Message
 * @param Errors
 */
public record ObError(String Code, String Id, String Message, List<Description> Errors) {
    public record Description(String ErrorCode, String Message, String Path, String Url) {
    }
}
