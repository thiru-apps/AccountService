package org.fss.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transactions {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "Account_ID")
  private Accounts accounts;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @JsonFormat(pattern = "MM/dd/yyyy")
  @Column(name = "Transaction_Date")
  private LocalDate transactionDate;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "Transaction_Type_ID")
  private TransactionTypes transactionTypes;

  @Column(name = "Transaction_Amount")
  private BigDecimal transactionAmount;

  @Column(name = "Transaction_Notes")
  private String transactionNotes;

  @Column(name = "Balance")
  private BigDecimal balance;

  public Transactions(Accounts accounts, LocalDate transactionDate,
      TransactionTypes transactionTypes, BigDecimal transactionAmount,
      String transactionNotes, BigDecimal balance) {
    this.accounts = accounts;
    this.transactionDate = transactionDate;
    this.transactionTypes = transactionTypes;
    this.transactionAmount = transactionAmount;
    this.transactionNotes = transactionNotes;
    this.balance = balance;
  }
}
