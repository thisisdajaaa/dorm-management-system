package org.dms.services.impl;

import org.dms.annotations.Autowired;
import org.dms.annotations.Component;
import org.dms.constants.RoomStatus;
import org.dms.exceptions.RoomAssignmentException;
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

@Component
public class RoomAssignmentServiceImpl implements IRoomAssignmentService {
    @Autowired
    private IRoomAssignmentRepository roomAssignmentRepository;
    @Autowired
    private IPersonService personService;
    @Autowired
    private IRoomService roomService;

    public void addToRepository(RoomAssignment roomAssignment) {
        roomAssignmentRepository.save(roomAssignment);
    }
    @Override
    public RoomAssignment findById(Integer id) {
        return roomAssignmentRepository.findById(id).orElseThrow(RoomAssignmentException::new);
    }
    @Override
    public List<Map.Entry<Integer, RoomAssignment>> findAll() {
        return roomAssignmentRepository.findAll();
    }

}