package com.ob.tsb.balances.model.response;

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
 * BalanceByIdResponseDataBalanceAmount
 */

@JsonTypeName("BalanceByIdResponse_Data_Balance_Amount")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-11T13:32:53.566584800+05:30[Asia/Calcutta]")
public class BalanceByIdResponseDataBalanceAmount {

  private String amount;

  private String currency;

  public BalanceByIdResponseDataBalanceAmount amount(String amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  */
  
  @Schema(name = "Amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Amount")
  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public BalanceByIdResponseDataBalanceAmount currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Get currency
   * @return currency
  */
  
  @Schema(name = "Currency", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Currency")
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BalanceByIdResponseDataBalanceAmount balanceByIdResponseDataBalanceAmount = (BalanceByIdResponseDataBalanceAmount) o;
    return Objects.equals(this.amount, balanceByIdResponseDataBalanceAmount.amount) &&
        Objects.equals(this.currency, balanceByIdResponseDataBalanceAmount.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BalanceByIdResponseDataBalanceAmount {\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
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

