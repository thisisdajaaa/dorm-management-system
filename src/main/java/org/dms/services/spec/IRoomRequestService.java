package org.dms.services.spec;

import org.dms.models.RoomRequest;

import java.util.List;
import java.util.Map;

public interface IRoomRequestService {
    public void acknowledgeRoomRequest(Integer roomRequestId);
    public RoomRequest findById(Integer roomRequestId);
    public void save(RoomRequest roomRequest);
    public List<Map.Entry<Integer, RoomRequest>> findAll();
}
