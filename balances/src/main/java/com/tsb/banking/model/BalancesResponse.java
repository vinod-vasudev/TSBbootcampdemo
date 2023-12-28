package com.tsb.banking.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.tsb.banking.model.BalanceByIdResponseLinks;
import com.tsb.banking.model.BalanceByIdResponseMeta;
import com.tsb.banking.model.BalancesResponseData;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * BalancesResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-27T16:06:24.218605700+05:30[Asia/Calcutta]")
public class BalancesResponse {

  private BalancesResponseData data;

  private BalanceByIdResponseLinks links;

  private BalanceByIdResponseMeta meta;

  public BalancesResponse data(BalancesResponseData data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
  */
  @Valid 
  @Schema(name = "Data", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Data")
  public BalancesResponseData getData() {
    return data;
  }

  public void setData(BalancesResponseData data) {
    this.data = data;
  }

  public BalancesResponse links(BalanceByIdResponseLinks links) {
    this.links = links;
    return this;
  }

  /**
   * Get links
   * @return links
  */
  @Valid 
  @Schema(name = "Links", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Links")
  public BalanceByIdResponseLinks getLinks() {
    return links;
  }

  public void setLinks(BalanceByIdResponseLinks links) {
    this.links = links;
  }

  public BalancesResponse meta(BalanceByIdResponseMeta meta) {
    this.meta = meta;
    return this;
  }

  /**
   * Get meta
   * @return meta
  */
  @Valid 
  @Schema(name = "Meta", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Meta")
  public BalanceByIdResponseMeta getMeta() {
    return meta;
  }

  public void setMeta(BalanceByIdResponseMeta meta) {
    this.meta = meta;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BalancesResponse balancesResponse = (BalancesResponse) o;
    return Objects.equals(this.data, balancesResponse.data) &&
        Objects.equals(this.links, balancesResponse.links) &&
        Objects.equals(this.meta, balancesResponse.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, links, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BalancesResponse {\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
    sb.append("    meta: ").append(toIndentedString(meta)).append("\n");
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

