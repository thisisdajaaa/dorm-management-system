package org.dms.services.spec;

import org.dms.models.KitchenKeyLog;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IKitchenKeyLogService {
    void addKitchenKeyLog(LocalDate borrowedStartDate, Integer keyId, Integer personId);

    void markKitchenKeyLogAsComplete();

    KitchenKeyLog findById(Integer id);

    Optional<KitchenKeyLog> getOpenKeyLog();

    List<Map.Entry<Integer, KitchenKeyLog>> findAll();

    List<Map.Entry<Integer, KitchenKeyLog>> findAllByLatestStartDate();
}
