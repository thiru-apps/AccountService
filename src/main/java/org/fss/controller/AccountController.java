package org.fss.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.fss.models.Accounts;
import org.fss.payload.request.AccountRequest;
import org.fss.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/account")
public class AccountController {

  private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

  @Autowired
  private AccountService accountService;

  @GetMapping("/list")
  public ResponseEntity<?> listOfAccounts() {
    List<Accounts> accountList = accountService.listAll();
    logger.info("Accounts list count {}", accountList.size());
    if (accountList.size() > 0) {
      return ResponseEntity.ok().body(accountList);
    }
    return ResponseEntity.ok(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/balance")
  public ResponseEntity<?> getBalance(@RequestBody AccountRequest accountRequest) {
    Optional<?> accounts = accountService.getBalance(accountRequest.getAccountID());
    if (accounts.get() instanceof BigDecimal) {
      return ResponseEntity.ok().body("Your Account Balance : " + accounts.get());
    } else if (accounts.get() instanceof String) {
      return ResponseEntity.ok().body(accounts.get());
    }
    return ResponseEntity.ok().body(Optional.empty());
  }


  @PutMapping("/close")
  public ResponseEntity<?> closeAccount(@RequestBody AccountRequest accountRequest) {
    if (accountRequest.getAccountID() == null || accountRequest.getAccountID().isBlank()) {
      return ResponseEntity.ok().body("Account ID is not valid");
    }
    return ResponseEntity.ok().body(accountService.closeAccount(accountRequest.getAccountID()));
  }


}
