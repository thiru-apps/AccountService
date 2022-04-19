package org.fss.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "transactiontypes")
@Data
public class TransactionTypes {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Transaction_Type_ID")
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private TransactionType transactionType;

}
