package org.dms.services.impl;

import org.dms.annotations.Autowired;
import org.dms.constants.Role;
import org.dms.constants.RoomStatus;
import org.dms.exceptions.PersonException;
import org.dms.exceptions.RoomException;
import org.dms.models.Person;
import org.dms.models.Room;
import org.dms.repositories.spec.IRoomRepository;
import org.dms.services.spec.IRoomService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoomServiceImpl implements IRoomService {
    @Autowired
    IRoomRepository roomRepository;

    @Override
    public void addRoom(Integer roomNumber) {
        roomRepository.save(new Room(roomNumber));
    }

    @Override
    public Room findById(Integer roomNumber) {
        return roomRepository.findById(roomNumber).orElseThrow(RoomException.NotFoundException::new);
    }

    @Override
    public List<Map.Entry<Integer, Room>> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> checkInRoom() {
        if(!isRoomAvailable()){
            return Optional.empty();
        }
        return findAll().stream()
                .map(entry -> entry.getValue())
                .filter(r -> r.getStatus() == RoomStatus.AVAILABLE)
                .findFirst();
    }

    private boolean isRoomAvailable() {
        return findAll().size() < IRoomService.MAX_ROOM_CAPACITY;
    }
}
