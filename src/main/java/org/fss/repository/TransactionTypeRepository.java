package org.fss.repository;

import java.util.Optional;
import org.fss.models.TransactionType;
import org.fss.models.TransactionTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionTypes, Long> {

  Optional<TransactionTypes> findByTransactionType(TransactionType transactionType);
}
