package org.fss.exception;

import java.time.LocalDate;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ExceptionMessage {

  private int statusCode;
  private LocalDate timestamp;
  private String message;
  private String description;

  public ExceptionMessage(int statusCode, LocalDate timestamp, String message,
      String description) {
    this.statusCode = statusCode;
    this.timestamp = timestamp;
    this.message = message;
    this.description = description;
  }
}
