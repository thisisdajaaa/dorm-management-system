package org.dms.services.impl;

import org.dms.annotations.Autowired;
import org.dms.annotations.Component;
import org.dms.exceptions.RoomException;
import org.dms.models.Person;
import org.dms.models.Room;
import org.dms.models.RoomAssignment;
import org.dms.repositories.spec.IRoomAssignmentRepository;
import org.dms.repositories.spec.IRoomRepository;
import org.dms.services.spec.IPersonService;
import org.dms.services.spec.IRoomAssignmentService;
import org.dms.services.spec.IRoomService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class RoomAssignmentServiceImpl implements IRoomAssignmentService {
    @Autowired
    private IRoomAssignmentRepository roomAssignmentRepository;

    @Autowired
    private IRoomRepository roomRepository;
    @Autowired
    private IPersonService personService;
    @Autowired
    private IRoomService roomService;

    public void addRoomAssignment(Integer roomNumber, Integer personId, LocalDate startDate, LocalDate endDate) {
        Person person = personService.findById(personId);
        Room room = roomService.findById(roomNumber);

        RoomAssignment roomAssignment = new RoomAssignment(startDate, endDate);
        roomAssignment.assignRoomToPerson(room, person);

        roomAssignmentRepository.save(roomAssignment);
    }

    @Override
    public RoomAssignment findById(Integer id) {
        return null;
    }

    @Override
    public List<Map.Entry<Integer, RoomAssignment>> findAll() {
        return null;
    }
}
