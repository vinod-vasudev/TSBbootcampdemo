package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.model.OBReadBalanceDataBalanceInner;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * OBReadBalanceData
 */

@JsonTypeName("OBReadBalance_Data")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-05T13:18:51.291297100+05:30[Asia/Calcutta]")
public class OBReadBalanceData {

  @Valid
  private List<@Valid OBReadBalanceDataBalanceInner> balance;

  public OBReadBalanceData balance(List<@Valid OBReadBalanceDataBalanceInner> balance) {
    this.balance = balance;
    return this;
  }

  public OBReadBalanceData addBalanceItem(OBReadBalanceDataBalanceInner balanceItem) {
    if (this.balance == null) {
      this.balance = new ArrayList<>();
    }
    this.balance.add(balanceItem);
    return this;
  }

  /**
   * Get balance
   * @return balance
  */
  @Valid 
  @Schema(name = "Balance", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Balance")
  public List<@Valid OBReadBalanceDataBalanceInner> getBalance() {
    return balance;
  }

  public void setBalance(List<@Valid OBReadBalanceDataBalanceInner> balance) {
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
    OBReadBalanceData obReadBalanceData = (OBReadBalanceData) o;
    return Objects.equals(this.balance, obReadBalanceData.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(balance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OBReadBalanceData {\n");
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

