package org.dms.services.spec;

import org.dms.constants.KeyStatus;
import org.dms.models.Key;
import java.util.List;
import java.util.Map;

public interface IKeyService {
    void addKey(boolean isPrimary);

    Key findById(Integer id);

    boolean isPrimaryKey(Integer id);

    Key getPrimaryKey();

    KeyStatus checkPrimaryKeyStatus();

    List<Map.Entry<Integer, Key>> findAll();

    void setKeyStatus(Integer id, KeyStatus keyStatus);
}
