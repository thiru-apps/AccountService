package org.fss.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fss.models.TransactionType;
import org.fss.payload.request.TransactionRequest;
import org.fss.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

  @InjectMocks
  TransactionController transactionController;

  @Mock
  TransactionService transactionService;

  @Test
  void addCustomerPositiveTest() {
    TransactionRequest transactionRequest = new TransactionRequest();
    transactionRequest.setTransactionType(TransactionType.DEBIT);
    ResponseEntity<?> response = transactionController.addCustomer(transactionRequest);
    assertEquals(200, response.getStatusCode().value());
  }

  @Test
  void addCustomerNegativeTest() {
    TransactionRequest transactionRequest = new TransactionRequest();
    ResponseEntity<?> response = transactionController.addCustomer(transactionRequest);
    assertEquals(400, response.getStatusCode().value());
  }
}