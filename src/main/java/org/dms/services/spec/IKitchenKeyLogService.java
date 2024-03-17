package org.dms.services.spec;

import org.dms.models.KitchenKeyLog;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IKitchenKeyLogService {
    void addKitchenKeyLog(LocalDate borrowedStartDate, LocalDate borrowedEndDate, Integer keyId, Integer personId);

    KitchenKeyLog findById(Integer id);

    List<Map.Entry<Integer, KitchenKeyLog>> findAll();
}
