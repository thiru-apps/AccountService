package org.fss.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.fss.models.Transactions;
import org.fss.payload.request.TransactionRequest;
import org.fss.repository.TransactionRepository;
import org.fss.repository.TransactionTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements AccountServiceDao<Transactions> {

  private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private TransactionTypeRepository transactionTypeRepository;

  @Autowired
  private AccountService accountService;

  @Override
  public Optional<Transactions> save(Transactions transactions) {
    return Optional.ofNullable(transactionRepository.save(transactions));
  }

  public Optional<String> save(TransactionRequest transactionRequest) {
    var transactionTypes = transactionTypeRepository.findByTransactionType(
        transactionRequest.getTransactionType());
    var optionalAccount = accountService.findById(transactionRequest.getAccountID());
    if (optionalAccount.isEmpty()) {
      return Optional.ofNullable("Account is not valid");
    } else if (optionalAccount.get().getDateClosed() != null) {
      return Optional.ofNullable("Account has been close so you can't do the transaction");
    }

    var account = optionalAccount.get();
    var currentBalance = account.getCurrentBalance();
    var transactionAmount = transactionRequest.getTransactionAmount();

    switch (transactionRequest.getTransactionType().name()) {
      case "DEBIT":
        // TO DO If any checks for debit
        var debitBalance = currentBalance.add(transactionAmount);
        account.setCurrentBalance(debitBalance);
        break;
      case "WITHDRAWAL":
        Optional<String> preCheck = preCheckForWithdrawal(currentBalance, transactionAmount,
            account.getMinimumBalance());
        if (!preCheck.isEmpty()) {
          return Optional.ofNullable(preCheck.get());
        }
        var withdrawalBalance = currentBalance.subtract(transactionAmount);
        account.setCurrentBalance(withdrawalBalance);
        break;
    }

    var transaction = new Transactions(account, LocalDate.now(), transactionTypes.get(),
        transactionRequest.getTransactionAmount(), transactionRequest.getTransactionNotes(),
        account.getCurrentBalance());
    save(transaction);
    return Optional.ofNullable("Transaction completed");
  }

  @Override
  public List<Transactions> listAll() {
    return null;
  }

  @Override
  public Optional<Transactions> update(Transactions transactions, String id) {
    return Optional.empty();
  }

  @Override
  public Optional<Transactions> findById(String id) {
    return Optional.empty();
  }

  private Optional<String> preCheckForWithdrawal(BigDecimal currentBalance,
      BigDecimal transactionAmount, BigDecimal minimumBalance) {

    var tempBalance = currentBalance.subtract(transactionAmount);
    if (currentBalance.equals(BigDecimal.ZERO)) {
      return Optional.ofNullable("In sufficient balance : Zero");
    } else if (tempBalance.compareTo(minimumBalance) == -1) {
      return Optional.ofNullable("In sufficient balance : Overdraw with minimum balance");
    }
    return Optional.empty();
  }
}
