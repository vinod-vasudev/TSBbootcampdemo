package com.ob.tsb.balances.model.response;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ob.tsb.balances.model.response.BalanceByIdResponseDataBalance;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * BalanceByIdResponseData
 */

@JsonTypeName("BalanceByIdResponse_Data")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-13T10:33:51.107823100+05:30[Asia/Calcutta]")
public class BalanceByIdResponseData {

  private BalanceByIdResponseDataBalance balance;

  public BalanceByIdResponseData balance(BalanceByIdResponseDataBalance balance) {
    this.balance = balance;
    return this;
  }

  /**
   * Get balance
   * @return balance
  */
  @Valid 
  @Schema(name = "Balance", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Balance")
  public BalanceByIdResponseDataBalance getBalance() {
    return balance;
  }

  public void setBalance(BalanceByIdResponseDataBalance balance) {
    this.balance = balance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BalanceByIdResponseData balanceByIdResponseData = (BalanceByIdResponseData) o;
    return Objects.equals(this.balance, balanceByIdResponseData.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(balance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BalanceByIdResponseData {\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
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

