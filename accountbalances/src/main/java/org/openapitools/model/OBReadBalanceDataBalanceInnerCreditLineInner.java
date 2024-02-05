package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.model.OBReadBalanceDataBalanceInnerAmount;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * OBReadBalanceDataBalanceInnerCreditLineInner
 */

@JsonTypeName("OBReadBalance_Data_Balance_inner_CreditLine_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-05T13:18:51.291297100+05:30[Asia/Calcutta]")
public class OBReadBalanceDataBalanceInnerCreditLineInner {

  private Boolean included;

  private OBReadBalanceDataBalanceInnerAmount amount;

  private String type;

  public OBReadBalanceDataBalanceInnerCreditLineInner included(Boolean included) {
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

  public OBReadBalanceDataBalanceInnerCreditLineInner amount(OBReadBalanceDataBalanceInnerAmount amount) {
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
  public OBReadBalanceDataBalanceInnerAmount getAmount() {
    return amount;
  }

  public void setAmount(OBReadBalanceDataBalanceInnerAmount amount) {
    this.amount = amount;
  }

  public OBReadBalanceDataBalanceInnerCreditLineInner type(String type) {
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
    OBReadBalanceDataBalanceInnerCreditLineInner obReadBalanceDataBalanceInnerCreditLineInner = (OBReadBalanceDataBalanceInnerCreditLineInner) o;
    return Objects.equals(this.included, obReadBalanceDataBalanceInnerCreditLineInner.included) &&
        Objects.equals(this.amount, obReadBalanceDataBalanceInnerCreditLineInner.amount) &&
        Objects.equals(this.type, obReadBalanceDataBalanceInnerCreditLineInner.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(included, amount, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OBReadBalanceDataBalanceInnerCreditLineInner {\n");
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

