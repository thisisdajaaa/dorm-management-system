package org.dms.services.spec;

import org.dms.models.Room;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IRoomService {
    final int MAX_ROOM_CAPACITY = 3;
    void addRoom(Integer roomNumber);

    Room findById(Integer roomNumber);

    List<Map.Entry<Integer, Room>> findAll();

    Optional<Room> checkInRoom();
}
