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
 * BalanceByIdResponseMeta
 */

@JsonTypeName("BalanceByIdResponse_Meta")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-12T01:29:46.852384500+05:30[Asia/Calcutta]")
public class BalanceByIdResponseMeta {

  private Integer totalPages;

  public BalanceByIdResponseMeta totalPages(Integer totalPages) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BalanceByIdResponseMeta balanceByIdResponseMeta = (BalanceByIdResponseMeta) o;
    return Objects.equals(this.totalPages, balanceByIdResponseMeta.totalPages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalPages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BalanceByIdResponseMeta {\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
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

