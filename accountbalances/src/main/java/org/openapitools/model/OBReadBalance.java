package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.model.OBReadBalanceData;
import org.openapitools.model.OBReadBalanceLinks;
import org.openapitools.model.OBReadBalanceMeta;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * OBReadBalance
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-05T13:18:51.291297100+05:30[Asia/Calcutta]")
public class OBReadBalance {

  private OBReadBalanceData data;

  private OBReadBalanceLinks links;

  private OBReadBalanceMeta meta;

  public OBReadBalance data(OBReadBalanceData data) {
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
  public OBReadBalanceData getData() {
    return data;
  }

  public void setData(OBReadBalanceData data) {
    this.data = data;
  }

  public OBReadBalance links(OBReadBalanceLinks links) {
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
  public OBReadBalanceLinks getLinks() {
    return links;
  }

  public void setLinks(OBReadBalanceLinks links) {
    this.links = links;
  }

  public OBReadBalance meta(OBReadBalanceMeta meta) {
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
  public OBReadBalanceMeta getMeta() {
    return meta;
  }

  public void setMeta(OBReadBalanceMeta meta) {
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
    OBReadBalance obReadBalance = (OBReadBalance) o;
    return Objects.equals(this.data, obReadBalance.data) &&
        Objects.equals(this.links, obReadBalance.links) &&
        Objects.equals(this.meta, obReadBalance.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, links, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OBReadBalance {\n");
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

