package com.ob.tsb.transactions.model.response;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ob.tsb.transactions.model.response.TransactionsResponseDataTransactionInner;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * TransactionsResponseData
 */

@JsonTypeName("TransactionsResponse_Data")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-14T20:54:23.886922600+05:30[Asia/Calcutta]")
public class TransactionsResponseData {

  @Valid
  private List<@Valid TransactionsResponseDataTransactionInner> transaction;

  public TransactionsResponseData transaction(List<@Valid TransactionsResponseDataTransactionInner> transaction) {
    this.transaction = transaction;
    return this;
  }

  public TransactionsResponseData addTransactionItem(TransactionsResponseDataTransactionInner transactionItem) {
    if (this.transaction == null) {
      this.transaction = new ArrayList<>();
    }
    this.transaction.add(transactionItem);
    return this;
  }

  /**
   * Get transaction
   * @return transaction
  */
  @Valid 
  @Schema(name = "Transaction", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Transaction")
  public List<@Valid TransactionsResponseDataTransactionInner> getTransaction() {
    return transaction;
  }

  public void setTransaction(List<@Valid TransactionsResponseDataTransactionInner> transaction) {
    this.transaction = transaction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionsResponseData transactionsResponseData = (TransactionsResponseData) o;
    return Objects.equals(this.transaction, transactionsResponseData.transaction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transaction);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionsResponseData {\n");
    sb.append("    transaction: ").append(toIndentedString(transaction)).append("\n");
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

