package org.fss.controller;

import java.util.List;
import javax.validation.Valid;
import org.fss.models.Customers;
import org.fss.payload.request.CustomerRequest;
import org.fss.payload.response.MessageResponse;
import org.fss.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/customer")
public class CustomerController {

  private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  @Autowired
  private CustomerService customerService;

  @GetMapping("/list")
  public ResponseEntity<?> listOfCustomer() {
    List<Customers> customerList = customerService.listAll();
    logger.info("Customers list count {}", customerList.size());
    if (customerList.size() > 0) {
      return ResponseEntity.ok().body(customerList);
    }
    return ResponseEntity.ok(HttpStatus.NO_CONTENT);
  }

  @PostMapping("/add")
  public ResponseEntity<?> addCustomer(@RequestBody CustomerRequest customerRequest) {
    if (customerService.existsByCustomerID(customerRequest.getCustomerID())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Exception: customer ID is already in use!"));
    } else if (customerService.existsByCustomerEmail(customerRequest.getCustomerEmail())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Exception: Email is already in use!"));
    }
    return ResponseEntity.ok().body(customerService.save(customerRequest));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> updateCustomer(@Valid @RequestBody CustomerRequest customerRequest,
      @PathVariable String id) {
    return ResponseEntity.ok().body(customerService.update(customerRequest, id));
  }

}
