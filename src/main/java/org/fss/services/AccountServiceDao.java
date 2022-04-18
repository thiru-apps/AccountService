package org.fss.services;

import java.util.List;
import java.util.Optional;

public interface AccountServiceDao<T> {

  Optional<T> save(T t);

  List<T> listAll();

  Optional<T> update(T t, final String id);

  Optional<T> findById(final String id);

}
