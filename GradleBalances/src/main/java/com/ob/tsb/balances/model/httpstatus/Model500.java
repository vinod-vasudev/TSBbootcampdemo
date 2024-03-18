package com.ob.tsb.balances.model.httpstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Model500
 */

@JsonTypeName("500")
@Setter
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-08T12:35:43.065827900+05:30[Asia/Calcutta]")
public class Model500 {

  private String code = "500 Internal Server Error";

  private String id = "2b5f0fb2-730b-11e8-adc0-fa7ae01bbebc";

  private String message = "Internal Server Error";

  @Valid
  private List<@Valid Model500ErrorsInner> errors;

  public Model500 code(String code) {
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

  public Model500 id(String id) {
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

  public Model500 message(String message) {
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

  public Model500 errors(List<@Valid Model500ErrorsInner> errors) {
    this.errors = errors;
    return this;
  }

  public Model500 addErrorsItem(Model500ErrorsInner errorsItem) {
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
  public List<@Valid Model500ErrorsInner> getErrors() {
    return errors;
  }

  public void setErrors(List<@Valid Model500ErrorsInner> errors) {
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
    Model500 model500 = (Model500) o;
    return Objects.equals(this.code, model500.code) &&
        Objects.equals(this.id, model500.id) &&
        Objects.equals(this.message, model500.message) &&
        Objects.equals(this.errors, model500.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, id, message, errors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Model500 {\n");
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
