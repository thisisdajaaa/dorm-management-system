package org.dms.repositories.spec;

import org.dms.models.Key;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IKeyRepository extends IBaseRepository<Key> {
    Optional<Key> getPrimaryKey();
}
