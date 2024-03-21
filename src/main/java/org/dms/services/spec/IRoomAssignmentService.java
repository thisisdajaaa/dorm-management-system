package org.dms.services.spec;

import org.dms.models.RoomAssignment;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IRoomAssignmentService {
    void addToRepository(RoomAssignment roomAssignment);
    RoomAssignment findById(Integer id);
    List<Map.Entry<Integer, RoomAssignment>> findAll();
    void removeFromRepository(RoomAssignment roomAssignment);
}
