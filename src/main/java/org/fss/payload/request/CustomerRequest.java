package org.fss.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

  @NotBlank
  private String customerID;

  @NotBlank
  private String customerName;

  @NotBlank
  private String customerPhone;

  @NotBlank
  @Size(max = 50)
  @Email
  private String customerEmail;

}
