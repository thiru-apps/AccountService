package org.fss.seed;

import java.util.Arrays;
import org.fss.models.TransactionType;
import org.fss.models.TransactionTypes;
import org.fss.repository.TransactionTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StaticRoleLoader implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(StaticRoleLoader.class);

  @Autowired
  private TransactionTypeRepository transactionTypeRepository;

  @Override
  public void run(String... args) throws Exception {
    loadRoleData();
  }

  private void loadRoleData() {
    long rowCount = transactionTypeRepository.count();
    if (transactionTypeRepository.count() == 0) {
      Arrays.stream(TransactionType.values()).forEach(x -> {
        TransactionTypes transactionTypes = new TransactionTypes();
        transactionTypes.setTransactionType(x);
        transactionTypeRepository.save(transactionTypes);
      });
    } else {
      logger.info("TransactionType exists hence skipping the seed data");
    }
  }
}
