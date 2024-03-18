package org.dms.repositories.impl;


import org.dms.annotations.Component;
import org.dms.models.RoomAssignment;
import org.dms.repositories.spec.IRoomAssignmentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Component
public class RoomAssignmentImpl implements IRoomAssignmentRepository {
    private final static Map<Integer, RoomAssignment> roomAssignMap = new HashMap<>();

    @Override
    public void save(RoomAssignment roomAssignment) {
        this.roomAssignMap.put(roomAssignment.getId(), roomAssignment);
    }

    @Override
    public Optional<RoomAssignment> findById(Integer id) {
        return Optional.ofNullable(roomAssignMap.get(id));
    }

    @Override
    public List<Map.Entry<Integer, RoomAssignment>> findAll() {
        return roomAssignMap.entrySet().stream().toList();
    }
}
