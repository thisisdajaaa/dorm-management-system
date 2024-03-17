package org.dms.services.spec;

import org.dms.models.RoomAssignment;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IRoomAssignmentService {
    void addRoomAssignment(Integer roomNumber, Integer personId, LocalDate startDate, LocalDate endDate);
    RoomAssignment findById(Integer id);
    List<Map.Entry<Integer, RoomAssignment>> findAll();
}
