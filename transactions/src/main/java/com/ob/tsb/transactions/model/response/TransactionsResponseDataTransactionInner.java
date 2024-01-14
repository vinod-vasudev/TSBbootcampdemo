package com.ob.tsb.transactions.model.response;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ob.tsb.transactions.model.response.TransactionsResponseDataTransactionInnerAmount;
import com.ob.tsb.transactions.model.response.TransactionsResponseDataTransactionInnerBalance;
import com.ob.tsb.transactions.model.response.TransactionsResponseDataTransactionInnerBankTransactionCode;
import com.ob.tsb.transactions.model.response.TransactionsResponseDataTransactionInnerProprietaryBankTransactionCode;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * TransactionsResponseDataTransactionInner
 */

@JsonTypeName("TransactionsResponse_Data_Transaction_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-14T20:54:23.886922600+05:30[Asia/Calcutta]")
public class TransactionsResponseDataTransactionInner {

  private String accountId;

  private String transactionId;

  private String transactionReference;

  private TransactionsResponseDataTransactionInnerAmount amount;

  private String creditDebitIndicator;

  private String status;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime bookingDateTime;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime valueDateTime;

  private String transactionInformation;

  private TransactionsResponseDataTransactionInnerBankTransactionCode bankTransactionCode;

  private TransactionsResponseDataTransactionInnerProprietaryBankTransactionCode proprietaryBankTransactionCode;

  private TransactionsResponseDataTransactionInnerBalance balance;

  public TransactionsResponseDataTransactionInner accountId(String accountId) {
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

  public TransactionsResponseDataTransactionInner transactionId(String transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  /**
   * Get transactionId
   * @return transactionId
  */
  
  @Schema(name = "TransactionId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("TransactionId")
  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public TransactionsResponseDataTransactionInner transactionReference(String transactionReference) {
    this.transactionReference = transactionReference;
    return this;
  }

  /**
   * Get transactionReference
   * @return transactionReference
  */
  
  @Schema(name = "TransactionReference", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("TransactionReference")
  public String getTransactionReference() {
    return transactionReference;
  }

  public void setTransactionReference(String transactionReference) {
    this.transactionReference = transactionReference;
  }

  public TransactionsResponseDataTransactionInner amount(TransactionsResponseDataTransactionInnerAmount amount) {
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
  public TransactionsResponseDataTransactionInnerAmount getAmount() {
    return amount;
  }

  public void setAmount(TransactionsResponseDataTransactionInnerAmount amount) {
    this.amount = amount;
  }

  public TransactionsResponseDataTransactionInner creditDebitIndicator(String creditDebitIndicator) {
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

  public TransactionsResponseDataTransactionInner status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  
  @Schema(name = "Status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Status")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public TransactionsResponseDataTransactionInner bookingDateTime(OffsetDateTime bookingDateTime) {
    this.bookingDateTime = bookingDateTime;
    return this;
  }

  /**
   * Get bookingDateTime
   * @return bookingDateTime
  */
  @Valid 
  @Schema(name = "BookingDateTime", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("BookingDateTime")
  public OffsetDateTime getBookingDateTime() {
    return bookingDateTime;
  }

  public void setBookingDateTime(OffsetDateTime bookingDateTime) {
    this.bookingDateTime = bookingDateTime;
  }

  public TransactionsResponseDataTransactionInner valueDateTime(OffsetDateTime valueDateTime) {
    this.valueDateTime = valueDateTime;
    return this;
  }

  /**
   * Get valueDateTime
   * @return valueDateTime
  */
  @Valid 
  @Schema(name = "ValueDateTime", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ValueDateTime")
  public OffsetDateTime getValueDateTime() {
    return valueDateTime;
  }

  public void setValueDateTime(OffsetDateTime valueDateTime) {
    this.valueDateTime = valueDateTime;
  }

  public TransactionsResponseDataTransactionInner transactionInformation(String transactionInformation) {
    this.transactionInformation = transactionInformation;
    return this;
  }

  /**
   * Get transactionInformation
   * @return transactionInformation
  */
  
  @Schema(name = "TransactionInformation", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("TransactionInformation")
  public String getTransactionInformation() {
    return transactionInformation;
  }

  public void setTransactionInformation(String transactionInformation) {
    this.transactionInformation = transactionInformation;
  }

  public TransactionsResponseDataTransactionInner bankTransactionCode(TransactionsResponseDataTransactionInnerBankTransactionCode bankTransactionCode) {
    this.bankTransactionCode = bankTransactionCode;
    return this;
  }

  /**
   * Get bankTransactionCode
   * @return bankTransactionCode
  */
  @Valid 
  @Schema(name = "BankTransactionCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("BankTransactionCode")
  public TransactionsResponseDataTransactionInnerBankTransactionCode getBankTransactionCode() {
    return bankTransactionCode;
  }

  public void setBankTransactionCode(TransactionsResponseDataTransactionInnerBankTransactionCode bankTransactionCode) {
    this.bankTransactionCode = bankTransactionCode;
  }

  public TransactionsResponseDataTransactionInner proprietaryBankTransactionCode(TransactionsResponseDataTransactionInnerProprietaryBankTransactionCode proprietaryBankTransactionCode) {
    this.proprietaryBankTransactionCode = proprietaryBankTransactionCode;
    return this;
  }

  /**
   * Get proprietaryBankTransactionCode
   * @return proprietaryBankTransactionCode
  */
  @Valid 
  @Schema(name = "ProprietaryBankTransactionCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ProprietaryBankTransactionCode")
  public TransactionsResponseDataTransactionInnerProprietaryBankTransactionCode getProprietaryBankTransactionCode() {
    return proprietaryBankTransactionCode;
  }

  public void setProprietaryBankTransactionCode(TransactionsResponseDataTransactionInnerProprietaryBankTransactionCode proprietaryBankTransactionCode) {
    this.proprietaryBankTransactionCode = proprietaryBankTransactionCode;
  }

  public TransactionsResponseDataTransactionInner balance(TransactionsResponseDataTransactionInnerBalance balance) {
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
  public TransactionsResponseDataTransactionInnerBalance getBalance() {
    return balance;
  }

  public void setBalance(TransactionsResponseDataTransactionInnerBalance balance) {
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
    TransactionsResponseDataTransactionInner transactionsResponseDataTransactionInner = (TransactionsResponseDataTransactionInner) o;
    return Objects.equals(this.accountId, transactionsResponseDataTransactionInner.accountId) &&
        Objects.equals(this.transactionId, transactionsResponseDataTransactionInner.transactionId) &&
        Objects.equals(this.transactionReference, transactionsResponseDataTransactionInner.transactionReference) &&
        Objects.equals(this.amount, transactionsResponseDataTransactionInner.amount) &&
        Objects.equals(this.creditDebitIndicator, transactionsResponseDataTransactionInner.creditDebitIndicator) &&
        Objects.equals(this.status, transactionsResponseDataTransactionInner.status) &&
        Objects.equals(this.bookingDateTime, transactionsResponseDataTransactionInner.bookingDateTime) &&
        Objects.equals(this.valueDateTime, transactionsResponseDataTransactionInner.valueDateTime) &&
        Objects.equals(this.transactionInformation, transactionsResponseDataTransactionInner.transactionInformation) &&
        Objects.equals(this.bankTransactionCode, transactionsResponseDataTransactionInner.bankTransactionCode) &&
        Objects.equals(this.proprietaryBankTransactionCode, transactionsResponseDataTransactionInner.proprietaryBankTransactionCode) &&
        Objects.equals(this.balance, transactionsResponseDataTransactionInner.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId, transactionId, transactionReference, amount, creditDebitIndicator, status, bookingDateTime, valueDateTime, transactionInformation, bankTransactionCode, proprietaryBankTransactionCode, balance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionsResponseDataTransactionInner {\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    transactionReference: ").append(toIndentedString(transactionReference)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    creditDebitIndicator: ").append(toIndentedString(creditDebitIndicator)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    bookingDateTime: ").append(toIndentedString(bookingDateTime)).append("\n");
    sb.append("    valueDateTime: ").append(toIndentedString(valueDateTime)).append("\n");
    sb.append("    transactionInformation: ").append(toIndentedString(transactionInformation)).append("\n");
    sb.append("    bankTransactionCode: ").append(toIndentedString(bankTransactionCode)).append("\n");
    sb.append("    proprietaryBankTransactionCode: ").append(toIndentedString(proprietaryBankTransactionCode)).append("\n");
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

