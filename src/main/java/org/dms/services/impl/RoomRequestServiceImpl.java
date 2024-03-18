package org.dms.services.impl;

import org.dms.annotations.Autowired;
import org.dms.constants.RequestType;
import org.dms.exceptions.IssueReportException;
import org.dms.exceptions.RoomException;
import org.dms.exceptions.RoomRequestException;
import org.dms.models.Room;
import org.dms.models.RoomAssignment;
import org.dms.models.RoomRequest;
import org.dms.repositories.spec.IRoomRequestRepository;
import org.dms.services.spec.IRoomAssignmentService;
import org.dms.services.spec.IRoomRequestService;
import org.dms.services.spec.IRoomService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RoomRequestServiceImpl implements IRoomRequestService {
    @Autowired
    IRoomRequestRepository roomRequestRepository;
    @Autowired
    IRoomAssignmentService roomAssignmentService;
    @Autowired
    IRoomService roomService;
    @Override
    public void acknowledgeRoomRequest(Integer roomRequestId) {
        RoomRequest roomRequest = findById(roomRequestId);
        RoomAssignment roomAssignment = roomRequest.getRoomAssignment();
        if(roomRequest.getRequestType() == RequestType.CHANGE) {
            // TODO: room is available then change it
            Optional<Room> roomOption = roomService.checkInRoom();
            if(roomOption.isPresent()) {
                Room room = roomOption.get();
                // update room assignment repo
            } else {
                throw new RoomException.NotFoundException("No available room");
            }
            //TODO: roomAssignmentService.findAll().
        } else {
            //TODO: make room available
        }
        roomRequest.setResolved(true);
        save(roomRequest);
    }
    @Override
    public RoomRequest findById(Integer roomRequestId) {
        return roomRequestRepository.findById(roomRequestId)
                .orElseThrow(RoomRequestException.NotFoundException::new);
    }
    @Override
    public void save(RoomRequest roomRequest) {
        roomRequestRepository.save(roomRequest);
    }
    @Override
    public List<Map.Entry<Integer, RoomRequest>> findAll() {
        return roomRequestRepository.findAll();
    }
}
