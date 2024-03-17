package org.dms.repositories.impl;

import org.dms.annotations.Component;
import org.dms.models.Key;
import org.dms.repositories.spec.IKeyRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class KeyRepositoryImpl implements IKeyRepository {
    private final Map<Integer, Key> keys = new HashMap<>();

    @Override
    public void save(Key key) {
        keys.put(key.getId(), key);
    }

    @Override
    public Optional<Key> findById(Integer id) {
        return Optional.ofNullable(keys.get(id));
    }

    @Override
    public Optional<Key> getPrimaryKey() {
        Optional<Key> key = findAll().stream().filter(x -> x.getValue().getPrimary()).flatMap(List::map).findAny();

        return Optional.ofNullable(keys.get(id));
    }

    @Override
    public List<Map.Entry<Integer, Key>> findAll() {
        return keys.entrySet().stream().toList();
    }
}
