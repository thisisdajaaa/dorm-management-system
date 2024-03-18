package org.dms.repositories.spec;

import org.dms.models.RoomAssignment;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IRoomAssignmentRepository {
    void save(RoomAssignment roomAssignment);

    Optional<RoomAssignment> findById(Integer id);

    List<Map.Entry<Integer, RoomAssignment>> findAll();
}
