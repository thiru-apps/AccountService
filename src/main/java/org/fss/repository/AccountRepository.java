package org.fss.repository;

import java.util.Optional;
import org.fss.models.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {

  Optional<Accounts> findById(String id);

}
