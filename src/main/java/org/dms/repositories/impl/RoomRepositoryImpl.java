package org.dms.repositories.impl;

import org.dms.annotations.Component;
import org.dms.models.Room;
import org.dms.models.RoomAssignment;
import org.dms.repositories.spec.IRoomRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class RoomRepositoryImpl implements IRoomRepository {
    private final Map<Integer, Room> roomMap = new HashMap<>();
    @Override
    public void save(Room room) {
        this.roomMap.put(room.roomNumber(), room);
    }
    @Override
    public Optional<Room> findById(Integer id) {
        return Optional.ofNullable(roomMap.get(id));
    }
    @Override
    public List<Map.Entry<Integer, Room>> findAll() {
        return roomMap.entrySet().stream().toList();
    }
}
