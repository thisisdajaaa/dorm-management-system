package org.dms.repositories.spec;

import org.dms.models.Key;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IKeyRepository {
    void save(Key key);

    Optional<Key> findById(Integer id);

    List<Map.Entry<Integer, Key>> findAll();
}
