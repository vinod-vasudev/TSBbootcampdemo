package com.ob.tsb.transactions.model.response;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
 * TransactionsResponseMeta
 */

@JsonTypeName("TransactionsResponse_Meta")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-14T20:54:23.886922600+05:30[Asia/Calcutta]")
public class TransactionsResponseMeta {

  private Integer totalPages;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime firstAvailableDateTime;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime lastAvailableDateTime;

  public TransactionsResponseMeta totalPages(Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  /**
   * Get totalPages
   * @return totalPages
  */
  
  @Schema(name = "TotalPages", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("TotalPages")
  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public TransactionsResponseMeta firstAvailableDateTime(OffsetDateTime firstAvailableDateTime) {
    this.firstAvailableDateTime = firstAvailableDateTime;
    return this;
  }

  /**
   * Get firstAvailableDateTime
   * @return firstAvailableDateTime
  */
  @Valid 
  @Schema(name = "FirstAvailableDateTime", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("FirstAvailableDateTime")
  public OffsetDateTime getFirstAvailableDateTime() {
    return firstAvailableDateTime;
  }

  public void setFirstAvailableDateTime(OffsetDateTime firstAvailableDateTime) {
    this.firstAvailableDateTime = firstAvailableDateTime;
  }

  public TransactionsResponseMeta lastAvailableDateTime(OffsetDateTime lastAvailableDateTime) {
    this.lastAvailableDateTime = lastAvailableDateTime;
    return this;
  }

  /**
   * Get lastAvailableDateTime
   * @return lastAvailableDateTime
  */
  @Valid 
  @Schema(name = "LastAvailableDateTime", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("LastAvailableDateTime")
  public OffsetDateTime getLastAvailableDateTime() {
    return lastAvailableDateTime;
  }

  public void setLastAvailableDateTime(OffsetDateTime lastAvailableDateTime) {
    this.lastAvailableDateTime = lastAvailableDateTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionsResponseMeta transactionsResponseMeta = (TransactionsResponseMeta) o;
    return Objects.equals(this.totalPages, transactionsResponseMeta.totalPages) &&
        Objects.equals(this.firstAvailableDateTime, transactionsResponseMeta.firstAvailableDateTime) &&
        Objects.equals(this.lastAvailableDateTime, transactionsResponseMeta.lastAvailableDateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalPages, firstAvailableDateTime, lastAvailableDateTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionsResponseMeta {\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
    sb.append("    firstAvailableDateTime: ").append(toIndentedString(firstAvailableDateTime)).append("\n");
    sb.append("    lastAvailableDateTime: ").append(toIndentedString(lastAvailableDateTime)).append("\n");
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

