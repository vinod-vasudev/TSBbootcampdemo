package com.ob.tsb.balances.model.response;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ob.tsb.balances.model.response.BalancesResponseDataBalanceInnerAmount;
import com.ob.tsb.balances.model.response.BalancesResponseDataBalanceInnerCreditLineInner;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * BalancesResponseDataBalanceInner
 */

@JsonTypeName("BalancesResponse_Data_Balance_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-03T12:01:55.945311800+05:30[Asia/Calcutta]")
public class BalancesResponseDataBalanceInner {

  private String accountId;

  private BalancesResponseDataBalanceInnerAmount amount;

  private String creditDebitIndicator;

  private String type;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime dateTime;

  @Valid
  private List<@Valid BalancesResponseDataBalanceInnerCreditLineInner> creditLine;

  public BalancesResponseDataBalanceInner accountId(String accountId) {
    this.accountId = accountId;
    return this;
  }

  /**
   * Get accountId
   * @return accountId
  */
  
  @Schema(name = "AccountId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("AccountId")
  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public BalancesResponseDataBalanceInner amount(BalancesResponseDataBalanceInnerAmount amount) {
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
  public BalancesResponseDataBalanceInnerAmount getAmount() {
    return amount;
  }

  public void setAmount(BalancesResponseDataBalanceInnerAmount amount) {
    this.amount = amount;
  }

  public BalancesResponseDataBalanceInner creditDebitIndicator(String creditDebitIndicator) {
    this.creditDebitIndicator = creditDebitIndicator;
    return this;
  }

  /**
   * Get creditDebitIndicator
   * @return creditDebitIndicator
  */
  
  @Schema(name = "CreditDebitIndicator", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("CreditDebitIndicator")
  public String getCreditDebitIndicator() {
    return creditDebitIndicator;
  }

  public void setCreditDebitIndicator(String creditDebitIndicator) {
    this.creditDebitIndicator = creditDebitIndicator;
  }

  public BalancesResponseDataBalanceInner type(String type) {
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

  public BalancesResponseDataBalanceInner dateTime(OffsetDateTime dateTime) {
    this.dateTime = dateTime;
    return this;
  }

  /**
   * Get dateTime
   * @return dateTime
  */
  @Valid 
  @Schema(name = "DateTime", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("DateTime")
  public OffsetDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(OffsetDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public BalancesResponseDataBalanceInner creditLine(List<@Valid BalancesResponseDataBalanceInnerCreditLineInner> creditLine) {
    this.creditLine = creditLine;
    return this;
  }

  public BalancesResponseDataBalanceInner addCreditLineItem(BalancesResponseDataBalanceInnerCreditLineInner creditLineItem) {
    if (this.creditLine == null) {
      this.creditLine = new ArrayList<>();
    }
    this.creditLine.add(creditLineItem);
    return this;
  }

  /**
   * Get creditLine
   * @return creditLine
  */
  @Valid 
  @Schema(name = "CreditLine", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("CreditLine")
  public List<@Valid BalancesResponseDataBalanceInnerCreditLineInner> getCreditLine() {
    return creditLine;
  }

  public void setCreditLine(List<@Valid BalancesResponseDataBalanceInnerCreditLineInner> creditLine) {
    this.creditLine = creditLine;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BalancesResponseDataBalanceInner balancesResponseDataBalanceInner = (BalancesResponseDataBalanceInner) o;
    return Objects.equals(this.accountId, balancesResponseDataBalanceInner.accountId) &&
        Objects.equals(this.amount, balancesResponseDataBalanceInner.amount) &&
        Objects.equals(this.creditDebitIndicator, balancesResponseDataBalanceInner.creditDebitIndicator) &&
        Objects.equals(this.type, balancesResponseDataBalanceInner.type) &&
        Objects.equals(this.dateTime, balancesResponseDataBalanceInner.dateTime) &&
        Objects.equals(this.creditLine, balancesResponseDataBalanceInner.creditLine);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId, amount, creditDebitIndicator, type, dateTime, creditLine);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BalancesResponseDataBalanceInner {\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    creditDebitIndicator: ").append(toIndentedString(creditDebitIndicator)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    dateTime: ").append(toIndentedString(dateTime)).append("\n");
    sb.append("    creditLine: ").append(toIndentedString(creditLine)).append("\n");
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
