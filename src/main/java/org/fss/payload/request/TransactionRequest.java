package org.fss.payload.request;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.fss.models.TransactionType;

@Getter
@Setter
public class TransactionRequest {

  @NotBlank
  private String accountID;

  @NotBlank
  private TransactionType transactionType;

  @NotBlank
  private BigDecimal transactionAmount;

  @NotBlank
  private String transactionNotes;

}
