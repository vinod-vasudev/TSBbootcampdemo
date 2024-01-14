package com.ob.tsb.transactions.model.response;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ob.tsb.transactions.model.response.TransactionsResponseData;
import com.ob.tsb.transactions.model.response.TransactionsResponseLinks;
import com.ob.tsb.transactions.model.response.TransactionsResponseMeta;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * TransactionsResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-14T20:54:23.886922600+05:30[Asia/Calcutta]")
public class TransactionsResponse {

  private TransactionsResponseData data;

  private TransactionsResponseLinks links;

  private TransactionsResponseMeta meta;

  public TransactionsResponse data(TransactionsResponseData data) {
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
  public TransactionsResponseData getData() {
    return data;
  }

  public void setData(TransactionsResponseData data) {
    this.data = data;
  }

  public TransactionsResponse links(TransactionsResponseLinks links) {
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
  public TransactionsResponseLinks getLinks() {
    return links;
  }

  public void setLinks(TransactionsResponseLinks links) {
    this.links = links;
  }

  public TransactionsResponse meta(TransactionsResponseMeta meta) {
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
  public TransactionsResponseMeta getMeta() {
    return meta;
  }

  public void setMeta(TransactionsResponseMeta meta) {
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
    TransactionsResponse transactionsResponse = (TransactionsResponse) o;
    return Objects.equals(this.data, transactionsResponse.data) &&
        Objects.equals(this.links, transactionsResponse.links) &&
        Objects.equals(this.meta, transactionsResponse.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, links, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionsResponse {\n");
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

