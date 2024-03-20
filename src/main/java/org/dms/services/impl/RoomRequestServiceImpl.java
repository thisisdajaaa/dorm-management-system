package org.dms.services.impl;

import org.dms.annotations.Autowired;
import org.dms.annotations.Component;
import org.dms.constants.RequestType;
import org.dms.exceptions.RoomException;
import org.dms.exceptions.RoomRequestException;
import org.dms.models.Person;
import org.dms.models.Room;
import org.dms.models.RoomAssignment;
import org.dms.models.RoomRequest;
import org.dms.repositories.spec.IRoomRequestRepository;
import org.dms.services.spec.IRoomAssignmentService;
import org.dms.services.spec.IRoomRequestService;
import org.dms.services.spec.IRoomService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Component
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
        if(roomRequest.getRequestType() == RequestType.CHANGE) {
            Optional<Room> availableRoom = roomService.checkInRoom();
            if(availableRoom.isPresent()) {
                changeRoom(roomRequest.getRoomAssignment(), availableRoom);
            } else {
                updateRoomRequestStatus(roomRequest);
                throw new RoomException.NotFoundException("No available room");
            }
        } else if(roomRequest.getRequestType() == RequestType.LEAVE) {
            makeRoomAvailable(roomRequest.getRoomAssignment());
        }
        updateRoomRequestStatus(roomRequest);
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

    private void makeRoomAvailable(RoomAssignment roomAssignment) {
        roomService.makeRoomAvailable(roomAssignment.getRoom());
        roomAssignmentService.removeFromRepository(roomAssignment);
    }

    private void changeRoom(RoomAssignment roomAssignment, Optional<Room> availableRoom) {
        Person person = roomAssignment.getPerson();
        makeRoomAvailable(roomAssignment);
        roomAssignmentService.addToRepository(new RoomAssignment(
                LocalDate.now(), LocalDate.now().plusYears(1), person, availableRoom.get()));
    }

    private void updateRoomRequestStatus(RoomRequest roomRequest) {
        roomRequest.setResolved(true);
        save(roomRequest);
    }
}
