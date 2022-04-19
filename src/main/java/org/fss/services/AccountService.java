package org.fss.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.fss.models.Accounts;
import org.fss.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements AccountServiceDao<Accounts> {

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public Optional<Accounts> save(Accounts accounts) {
    return Optional.ofNullable(accountRepository.save(accounts));
  }

  @Override
  public List<Accounts> listAll() {
    return accountRepository.findAll();
  }

  @Override
  public Optional<Accounts> update(Accounts accounts, String id) {
    return Optional.empty();
  }

  @Override
  public Optional<Accounts> findById(String id) {
    return accountRepository.findById(id);
  }

  public Optional<?> getBalance(String accountID) {
    var accounts = findById(accountID);
    if (accounts.isPresent()) {
      return Optional.ofNullable(accounts.get().getCurrentBalance());
    } else {
      return Optional.ofNullable("AccountId is not valid");
    }
  }

  public Optional<String> closeAccount(String accountID) {
    var accounts = findById(accountID);
    if (accounts.isPresent()) {
      accounts.get().setDateClosed(LocalDate.now());
      accountRepository.save(accounts.get());
      return Optional.ofNullable("Account has been closed");
    } else {
      return Optional.ofNullable("Account ID not present please register with us");
    }
  }

}
