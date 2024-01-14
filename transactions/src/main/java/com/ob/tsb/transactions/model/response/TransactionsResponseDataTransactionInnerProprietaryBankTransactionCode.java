package com.ob.tsb.transactions.model.response;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * TransactionsResponseDataTransactionInnerProprietaryBankTransactionCode
 */

@JsonTypeName("TransactionsResponse_Data_Transaction_inner_ProprietaryBankTransactionCode")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-14T20:54:23.886922600+05:30[Asia/Calcutta]")
public class TransactionsResponseDataTransactionInnerProprietaryBankTransactionCode {

  private String code;

  private String issuer;

  public TransactionsResponseDataTransactionInnerProprietaryBankTransactionCode code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
  */
  
  @Schema(name = "Code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public TransactionsResponseDataTransactionInnerProprietaryBankTransactionCode issuer(String issuer) {
    this.issuer = issuer;
    return this;
  }

  /**
   * Get issuer
   * @return issuer
  */
  
  @Schema(name = "Issuer", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Issuer")
  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionsResponseDataTransactionInnerProprietaryBankTransactionCode transactionsResponseDataTransactionInnerProprietaryBankTransactionCode = (TransactionsResponseDataTransactionInnerProprietaryBankTransactionCode) o;
    return Objects.equals(this.code, transactionsResponseDataTransactionInnerProprietaryBankTransactionCode.code) &&
        Objects.equals(this.issuer, transactionsResponseDataTransactionInnerProprietaryBankTransactionCode.issuer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, issuer);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionsResponseDataTransactionInnerProprietaryBankTransactionCode {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    issuer: ").append(toIndentedString(issuer)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

