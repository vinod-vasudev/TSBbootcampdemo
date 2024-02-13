package org.openapitools.model;

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
 * Model400ErrorsInner
 */

@JsonTypeName("_400_Errors_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-08T12:35:43.065827900+05:30[Asia/Calcutta]")
public class Model400ErrorsInner {

  private String errorCode = "400 Bad Request";

  private String message = "400 Bad Request";

  private String path = "/api/v1/balance";

  private String url = "https://api.example.com/docs/errors/UNAUTHORISED ACCESS";

  public Model400ErrorsInner errorCode(String errorCode) {
    this.errorCode = errorCode;
    return this;
  }

  /**
   * Get errorCode
   * @return errorCode
  */
  
  @Schema(name = "ErrorCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ErrorCode")
  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public Model400ErrorsInner message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  */
  
  @Schema(name = "Message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Model400ErrorsInner path(String path) {
    this.path = path;
    return this;
  }

  /**
   * Get path
   * @return path
  */
  
  @Schema(name = "Path", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Path")
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Model400ErrorsInner url(String url) {
    this.url = url;
    return this;
  }

  /**
   * Get url
   * @return url
  */
  
  @Schema(name = "Url", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Url")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Model400ErrorsInner _400errorsInner = (Model400ErrorsInner) o;
    return Objects.equals(this.errorCode, _400errorsInner.errorCode) &&
        Objects.equals(this.message, _400errorsInner.message) &&
        Objects.equals(this.path, _400errorsInner.path) &&
        Objects.equals(this.url, _400errorsInner.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorCode, message, path, url);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Model400ErrorsInner {\n");
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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
