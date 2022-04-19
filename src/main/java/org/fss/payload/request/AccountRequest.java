package org.fss.payload.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {

  @NotBlank
  private String accountID;
}
