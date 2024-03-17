package org.dms.repositories.impl;

import org.dms.annotations.Component;
import org.dms.models.KitchenKeyLog;
import org.dms.repositories.spec.IKitchenKeyLogRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class KitchenKeyLogRepositoryImpl implements IKitchenKeyLogRepository {
    private final Map<Integer, KitchenKeyLog> kitchenKeyLogs = new HashMap<>();

    @Override
    public void save(KitchenKeyLog kitchenKeyLog) {
        kitchenKeyLogs.put(kitchenKeyLog.getId(), kitchenKeyLog);
    }

    @Override
    public Optional<KitchenKeyLog> findById(Integer id) {
        return Optional.ofNullable(kitchenKeyLogs.get(id));
    }

    @Override
    public List<Map.Entry<Integer, KitchenKeyLog>> findAll() {
        return kitchenKeyLogs.entrySet().stream().toList();
    }
}
