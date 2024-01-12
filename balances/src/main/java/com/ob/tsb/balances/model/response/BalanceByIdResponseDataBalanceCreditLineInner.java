package com.ob.tsb.balances.model.response;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ob.tsb.balances.model.response.BalanceByIdResponseDataBalanceAmount;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * BalanceByIdResponseDataBalanceCreditLineInner
 */

@JsonTypeName("BalanceByIdResponse_Data_Balance_CreditLine_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-12T01:29:46.852384500+05:30[Asia/Calcutta]")
public class BalanceByIdResponseDataBalanceCreditLineInner {

  private Boolean included;

  private BalanceByIdResponseDataBalanceAmount amount;

  private String type;

  public BalanceByIdResponseDataBalanceCreditLineInner included(Boolean included) {
    this.included = included;
    return this;
  }

  /**
   * Get included
   * @return included
  */
  
  @Schema(name = "Included", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Included")
  public Boolean getIncluded() {
    return included;
  }

  public void setIncluded(Boolean included) {
    this.included = included;
  }

  public BalanceByIdResponseDataBalanceCreditLineInner amount(BalanceByIdResponseDataBalanceAmount amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  */
  @Valid 
  @Schema(name = "Amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Amount")
  public BalanceByIdResponseDataBalanceAmount getAmount() {
    return amount;
  }

  public void setAmount(BalanceByIdResponseDataBalanceAmount amount) {
    this.amount = amount;
  }

  public BalanceByIdResponseDataBalanceCreditLineInner type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  */
  
  @Schema(name = "Type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BalanceByIdResponseDataBalanceCreditLineInner balanceByIdResponseDataBalanceCreditLineInner = (BalanceByIdResponseDataBalanceCreditLineInner) o;
    return Objects.equals(this.included, balanceByIdResponseDataBalanceCreditLineInner.included) &&
        Objects.equals(this.amount, balanceByIdResponseDataBalanceCreditLineInner.amount) &&
        Objects.equals(this.type, balanceByIdResponseDataBalanceCreditLineInner.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(included, amount, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BalanceByIdResponseDataBalanceCreditLineInner {\n");
    sb.append("    included: ").append(toIndentedString(included)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

