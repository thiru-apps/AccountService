package org.fss.repository;

import java.util.Optional;
import org.fss.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {

  Optional<Customers> findByCustomerName(String customerName);

  Boolean existsById(String customerID);

  Boolean existsByCustomerEmail(String customerEmail);

}
