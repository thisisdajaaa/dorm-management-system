package org.dms.services.spec;

import org.dms.models.Key;
import java.util.List;
import java.util.Map;

public interface IKeyService {
    void addKey(boolean isPrimary);

    Key findById(Integer id);

    boolean isPrimaryKey(Integer id);

    List<Map.Entry<Integer, Key>> findAll();
}
