package org.fss.services;

import java.util.List;
import java.util.Optional;
import org.fss.models.Customers;
import org.fss.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements AccountServiceDao<Customers> {

  @Autowired
  private CustomerRepository customerRepository;

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

  public boolean existsByCustomerID(String customerID) {
    return customerRepository.existsById(customerID);
  }

  public boolean existsByCustomerEmail(String customerEmail) {
    return customerRepository.existsByCustomerEmail(customerEmail);
  }

  @Override
  public Optional<Customers> findById(String id) {
    return Optional.empty();
  }
}
