package org.fss.controller;

import java.util.List;
import org.fss.models.Transactions;
import org.fss.payload.request.TransactionRequest;
import org.fss.payload.response.MessageResponse;
import org.fss.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/transaction")
public class TransactionController {

  private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

  @Autowired
  private TransactionService transactionService;

  @GetMapping("/list")
  public ResponseEntity<?> listOfTransactions() {
    List<Transactions> list = transactionService.listAll();
    logger.info("Transactions list count {}", list.size());
    if (list.size() > 0) {
      return ResponseEntity.ok().body(list);
    }
    return ResponseEntity.ok(HttpStatus.NO_CONTENT);
  }

  @PostMapping(value = {"/debit", "/credit"})
  public ResponseEntity<?> addCustomer(@RequestBody TransactionRequest transactionRequest) {
    if (transactionRequest.getTransactionType() == null) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Exception: TransactionType not allowed"));
    }
    return ResponseEntity.ok().body(transactionService.save(transactionRequest));
  }

}
