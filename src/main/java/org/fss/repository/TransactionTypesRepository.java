package org.fss.repository;

import org.fss.models.TransactionTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypesRepository extends JpaRepository<TransactionTypes, Long> {

}
