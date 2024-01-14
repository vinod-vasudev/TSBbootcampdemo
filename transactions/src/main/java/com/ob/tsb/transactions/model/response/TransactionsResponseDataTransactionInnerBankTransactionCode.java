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
 * TransactionsResponseDataTransactionInnerBankTransactionCode
 */

@JsonTypeName("TransactionsResponse_Data_Transaction_inner_BankTransactionCode")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-14T20:54:23.886922600+05:30[Asia/Calcutta]")
public class TransactionsResponseDataTransactionInnerBankTransactionCode {

  private String code;

  private String subCode;

  public TransactionsResponseDataTransactionInnerBankTransactionCode code(String code) {
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

  public TransactionsResponseDataTransactionInnerBankTransactionCode subCode(String subCode) {
    this.subCode = subCode;
    return this;
  }

  /**
   * Get subCode
   * @return subCode
  */
  
  @Schema(name = "SubCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("SubCode")
  public String getSubCode() {
    return subCode;
  }

  public void setSubCode(String subCode) {
    this.subCode = subCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionsResponseDataTransactionInnerBankTransactionCode transactionsResponseDataTransactionInnerBankTransactionCode = (TransactionsResponseDataTransactionInnerBankTransactionCode) o;
    return Objects.equals(this.code, transactionsResponseDataTransactionInnerBankTransactionCode.code) &&
        Objects.equals(this.subCode, transactionsResponseDataTransactionInnerBankTransactionCode.subCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, subCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionsResponseDataTransactionInnerBankTransactionCode {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    subCode: ").append(toIndentedString(subCode)).append("\n");
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

