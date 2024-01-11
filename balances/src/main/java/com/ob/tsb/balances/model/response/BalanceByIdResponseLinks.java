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
 * BalanceByIdResponseLinks
 */

@JsonTypeName("BalanceByIdResponse_Links")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-11T13:32:53.566584800+05:30[Asia/Calcutta]")
public class BalanceByIdResponseLinks {

  private String self;

  public BalanceByIdResponseLinks self(String self) {
    this.self = self;
    return this;
  }

  /**
   * Get self
   * @return self
  */
  
  @Schema(name = "Self", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Self")
  public String getSelf() {
    return self;
  }

  public void setSelf(String self) {
    this.self = self;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BalanceByIdResponseLinks balanceByIdResponseLinks = (BalanceByIdResponseLinks) o;
    return Objects.equals(this.self, balanceByIdResponseLinks.self);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BalanceByIdResponseLinks {\n");
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
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

