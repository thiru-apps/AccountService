package org.fss.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.fss.models.Customers;
import org.fss.payload.request.CustomerRequest;
import org.fss.payload.response.MessageResponse;
import org.fss.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

  @InjectMocks
  CustomerController customerController;

  @Mock
  CustomerService customerService;

  @Test
  void listOfCustomer() {
    Customers customers = new Customers();
    customers.setCustomerEmail("email@gmail.com");
    customers.setCustomerPhone("+91 768973456");
    customers.setDateOpened(LocalDate.now());
    customers.setId("cust123");
    customers.setDateClosed(null);
    List<Customers> customerList = new ArrayList<>();
    customerList.add(customers);
    when(customerService.listAll()).thenReturn(customerList);
    ResponseEntity<?> response = customerController.listOfCustomer();
    List respList = (List) response.getBody();
    Customers respBody = (Customers) respList.get(0);
    assertEquals(200, response.getStatusCode().value());
    assertEquals(customers.getId(), respBody.getId());
  }

  @Test
  void mptyListOfCustomer() {
    List<Customers> customerList = new ArrayList<>();
    when(customerService.listAll()).thenReturn(customerList);
    ResponseEntity<?> response = customerController.listOfCustomer();
    assertEquals(200, response.getStatusCode().value());
    assertEquals(HttpStatus.NO_CONTENT, response.getBody());
  }

  @Test
  void addCustomerWithExistingId() {
    when(customerService.existsByCustomerID(Mockito.anyString())).thenReturn(true);
    CustomerRequest customerRequest = new CustomerRequest();
    customerRequest.setCustomerID("cust123");
    ResponseEntity<?> response = customerController.addCustomer(customerRequest);
    MessageResponse messageResponse = (MessageResponse) response.getBody();
    assertEquals("Exception: customer ID is already in use!", messageResponse.getMessageResponse());
  }

  @Test
  void addCustomerPositiveTest() {
    when(customerService.existsByCustomerID(Mockito.anyString())).thenReturn(false);
    CustomerRequest customerRequest = new CustomerRequest();
    customerRequest.setCustomerID("cust123");
    ResponseEntity<?> response = customerController.addCustomer(customerRequest);
    MessageResponse messageResponse = (MessageResponse) response.getBody();
    assertEquals(200, response.getStatusCode().value());
  }

  @Test
  void addCustomerWithExistingEmail() {
    when(customerService.existsByCustomerEmail(Mockito.anyString())).thenReturn(true);
    CustomerRequest customerRequest = new CustomerRequest();
    customerRequest.setCustomerID("cust123");
    ResponseEntity<?> response = customerController.addCustomer(customerRequest);
    MessageResponse messageResponse = (MessageResponse) response.getBody();
    assertEquals("Exception: Email is already in use!", messageResponse.getMessageResponse());
  }

  @Test
  void updateCustomer() {
    CustomerRequest customerRequest = new CustomerRequest();
    customerRequest.setCustomerID("cust123");
    ResponseEntity<?> response = customerController.updateCustomer(customerRequest, "cust123");
    assertEquals(200, response.getStatusCode().value());
  }
}