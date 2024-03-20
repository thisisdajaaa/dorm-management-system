package org.dms.services.impl;

import org.dms.annotations.Autowired;
import org.dms.annotations.Component;
import org.dms.constants.KeyStatus;
import org.dms.exceptions.KeyException;
import org.dms.models.Key;
import org.dms.repositories.spec.IKeyRepository;
import org.dms.services.spec.IKeyService;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class KeyServiceImpl implements IKeyService {
    @Autowired
    private IKeyRepository keyRepository;
    @Override
    public void addKey() {
        Key newKey;
        //Check if there exists a Primary Key
        List<Key> keys = findAll().stream().
                filter(mapEntry -> mapEntry.getValue().getPrimary())
                .map(Map.Entry::getValue).toList();
        
        if(keys.isEmpty())
            newKey = new Key(true);
        else
            newKey = new Key(false);

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
    public Key getPrimaryKey() {
        return keyRepository.getPrimaryKey().orElseThrow(KeyException.NotFoundException::new);
    }

    @Override
    public KeyStatus checkPrimaryKeyStatus() {
        return getPrimaryKey().getKeyStatus();
    }

    @Override
    public List<Map.Entry<Integer, Key>> findAll() {
        return keyRepository.findAll();
    }

    @Override
    public void setKeyStatus(Integer id, KeyStatus keyStatus) {
        Key key = findById(id);
        key.setKeyStatus(keyStatus);
        keyRepository.save(key);
    }

    @Override
    public void reportStolenKey() {
        Key primaryKey = getPrimaryKey();
        primaryKey.setPrimary(false);
        primaryKey.setKeyStatus(KeyStatus.LOST);

        //Make another key Primary since the Primary Key was reported lost
        Optional<Key> secondaryKeyToPrimary = findAll().stream()
                .filter(key -> key.getValue().getKeyStatus() == KeyStatus.AVAILABLE)
                .map(Map.Entry::getValue)
                .findFirst();

        secondaryKeyToPrimary.ifPresentOrElse(secondaryKey -> secondaryKey.setPrimary(true),
                KeyException.NoSecondaryKeyException::new);
    }
}
