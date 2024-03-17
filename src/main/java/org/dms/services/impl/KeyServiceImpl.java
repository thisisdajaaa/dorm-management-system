package org.dms.services.impl;

import org.dms.annotations.Autowired;
import org.dms.annotations.Component;
import org.dms.exceptions.KeyException;
import org.dms.models.Key;
import org.dms.repositories.spec.IKeyRepository;
import org.dms.services.spec.IKeyService;
import java.util.List;
import java.util.Map;

@Component
public class KeyServiceImpl implements IKeyService {
    @Autowired
    private IKeyRepository keyRepository;

    @Override
    public void addKey(boolean isPrimary) {
        Key newKey = new Key(isPrimary);
        keyRepository.save(newKey);
    }

    @Override
    public Key findById(Integer id) {
        return keyRepository.findById(id).orElseThrow(KeyException.NotFoundException::new);
    }

    @Override
    public boolean isPrimaryKey(Integer id) {
        return findById(id).getPrimary();
    }

    @Override
    public List<Map.Entry<Integer, Key>> findAll() {
        return keyRepository.findAll();
    }
}
