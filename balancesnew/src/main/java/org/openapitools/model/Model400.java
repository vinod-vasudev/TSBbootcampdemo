package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.model.Model400ErrorsInner;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Model400
 */

@JsonTypeName("400")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-08T12:35:43.065827900+05:30[Asia/Calcutta]")
public class Model400 {

  private String code = "400 Bad Request";

  private String id = "2b5f0fb2-730b-11e8-adc0-fa7ae01bbebc";

  private String message = "Bad Request";

  @Valid
  private List<@Valid Model400ErrorsInner> errors;

  public Model400 code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
  */
  
  @Schema(name = "Code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Model400 id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "Id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Model400 message(String message) {
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

  public Model400 errors(List<@Valid Model400ErrorsInner> errors) {
    this.errors = errors;
    return this;
  }

  public Model400 addErrorsItem(Model400ErrorsInner errorsItem) {
    if (this.errors == null) {
      this.errors = new ArrayList<>();
    }
    this.errors.add(errorsItem);
    return this;
  }

  /**
   * Get errors
   * @return errors
  */
  @Valid 
  @Schema(name = "Errors", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("Errors")
  public List<@Valid Model400ErrorsInner> getErrors() {
    return errors;
  }

  public void setErrors(List<@Valid Model400ErrorsInner> errors) {
    this.errors = errors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Model400 _400 = (Model400) o;
    return Objects.equals(this.code, _400.code) &&
        Objects.equals(this.id, _400.id) &&
        Objects.equals(this.message, _400.message) &&
        Objects.equals(this.errors, _400.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, id, message, errors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Model400 {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
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

