package org.dms.repositories.spec;

import org.dms.models.Key;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IBaseRepository <T> {
    void save(T t);

    Optional<T> findById(Integer id);

    List<Map.Entry<Integer, T>> findAll();
}
