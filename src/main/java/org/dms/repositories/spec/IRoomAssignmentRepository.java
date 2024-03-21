package org.dms.repositories.spec;

import org.dms.models.RoomAssignment;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IRoomAssignmentRepository extends  IBaseRepository<RoomAssignment>{
    void remove(Integer roomAssignmentId);
}
