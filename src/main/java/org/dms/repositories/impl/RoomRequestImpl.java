package org.dms.repositories.impl;

import org.dms.models.RoomRequest;
import org.dms.repositories.spec.IRoomRequestRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RoomRequestImpl implements IRoomRequestRepository {
    private final static Map<Integer, RoomRequest> roomRequestMap = new HashMap<>();
    @Override
    public void save(RoomRequest roomRequest) {
        roomRequestMap.put(roomRequest.getId(), roomRequest);
    }

    @Override
    public Optional<RoomRequest> findById(Integer id) {
        return Optional.ofNullable(roomRequestMap.get(id));
    }

    @Override
    public List<Map.Entry<Integer, RoomRequest>> findAll() {
        return roomRequestMap.entrySet().stream().toList();
    }
}
