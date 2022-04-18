package org.fss.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
public class Customers {

  @NotBlank
  @Id
  @Column(name = "Customer_ID")
  private String id;

  @NotBlank
  @Column(name = "Customer_Name")
  private String customerName;

  @NotBlank
  @Column(name = "Customer_Phone")
  private String customerPhone;

  @NotBlank
  @Email
  @Column(name = "Customer_Email")
  private String customerEmail;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @JsonFormat(pattern = "MM/dd/yyyy")
  @Column(name = "Date_Opened")
  private LocalDate dateOpened;

  @Column(name = "Date_Closed")
  private LocalDate dateClosed;

  public Customers(CustomersBuilder customersBuilder) {
    this.id = customersBuilder.id;
    this.customerName = customersBuilder.customerName;
    this.customerPhone = customersBuilder.customerPhone;
    this.customerEmail = customersBuilder.customerEmail;
    this.dateClosed = customersBuilder.dateClosed;
    this.dateOpened = customersBuilder.dateOpened;
  }

  public static class CustomersBuilder {

    private final String id;
    private final String customerName;
    private final String customerPhone;
    private final String customerEmail;
    private LocalDate dateOpened;
    private LocalDate dateClosed;

    public CustomersBuilder(String id, String customerName, String customerPhone,
        String customerEmail,
        LocalDate dateOpened, LocalDate dateClosed) {
      this.id = id;
      this.customerName = customerName;
      this.customerPhone = customerPhone;
      this.customerEmail = customerEmail;
      this.dateClosed = dateClosed;
      this.dateOpened = dateOpened;
    }

    public Customers build() {
      return new Customers(this);
    }

  }

}
