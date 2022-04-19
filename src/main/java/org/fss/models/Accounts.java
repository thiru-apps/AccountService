package org.fss.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
public class Accounts {

  @Id
  @Column(name = "Account_ID", nullable = false)
  private String id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "Customer_ID")
  private Customers customers;

  @Column(name = "Date_Opened")
  private LocalDate dateOpened;

  @Column(name = "Date_Closed")
  private LocalDate dateClosed;

  @Column(name = "Minimum_Balance")
  private BigDecimal minimumBalance;

  @Column(name = "Current_Balance")
  private BigDecimal currentBalance;

  public Accounts(String id, Customers customers, LocalDate dateOpened,
      LocalDate dateClosed, BigDecimal minimumBalance, BigDecimal currentBalance) {
    this.id = id;
    this.customers = customers;
    this.dateOpened = dateOpened;
    this.dateClosed = dateClosed;
    this.minimumBalance = minimumBalance;
    this.currentBalance = currentBalance;
  }
}
