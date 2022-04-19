package org.fss.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.fss.models.Accounts;
import org.fss.models.Customers;
import org.fss.payload.request.CustomerRequest;
import org.fss.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements AccountServiceDao<Customers> {

  private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

  private static final String ALPHA_NUMERIC_DIGIT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private AccountService accountService;

  @Override
  public Optional<Customers> save(Customers customers) {
    return Optional.ofNullable(customerRepository.save(customers));
  }

  @Override
  public List<Customers> listAll() {
    return customerRepository.findAll();
  }

  @Override
  public Optional<Customers> update(Customers customers, String id) {
    return Optional.ofNullable(customerRepository.save(customers));
  }

  public Optional<Customers> update(CustomerRequest customerRequest, String id) {
    var customers = findById(id).get();
    customers.setCustomerName(customerRequest.getCustomerName());
    customers.setCustomerPhone(customerRequest.getCustomerPhone());
    customers.setCustomerEmail(customerRequest.getCustomerEmail());
    return update(customers, id);
  }

  public boolean existsByCustomerID(String customerID) {
    return customerRepository.existsById(customerID);
  }

  public boolean existsByCustomerEmail(String customerEmail) {
    return customerRepository.existsByCustomerEmail(customerEmail);
  }

  public Optional<Customers> save(CustomerRequest customerRequest) {
    LocalDate now = LocalDate.now();
    var customers = new Customers.CustomersBuilder(customerRequest.getCustomerID(),
        customerRequest.getCustomerName(), customerRequest.getCustomerPhone(),
        customerRequest.getCustomerEmail(), now, null).build();
    customers.setId(customerRequest.getCustomerID());
    customers.setCustomerName(customerRequest.getCustomerName());
    customers.setCustomerPhone(customerRequest.getCustomerPhone());
    customers.setCustomerEmail(customerRequest.getCustomerEmail());
    customers.setDateOpened(now);

    var accounts = new Accounts(createAccountID(), customers, LocalDate.now(), null,
        BigDecimal.valueOf(5000), BigDecimal.valueOf(5000));
    accountService.save(accounts);
    return save(customers);
  }

  @Override
  public Optional<Customers> findById(String id) {
    return Optional.empty();
  }

  private String createAccountID() {
    StringBuilder accountIDBuilder = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < 8; i++) {
      accountIDBuilder.append(
          ALPHA_NUMERIC_DIGIT.charAt(random.nextInt(ALPHA_NUMERIC_DIGIT.length())));
    }
    return accountIDBuilder.toString();
  }
}
