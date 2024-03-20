package org.dms.services.impl;

import org.dms.annotations.Autowired;
import org.dms.annotations.Component;
import org.dms.exceptions.IssueReportException;
import org.dms.exceptions.RoomAssignmentException;
import org.dms.models.RoomAssignment;
import org.dms.repositories.spec.IRoomAssignmentRepository;
import org.dms.services.spec.IPersonService;
import org.dms.services.spec.IRoomAssignmentService;
import org.dms.services.spec.IRoomService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        return roomAssignmentRepository.findById(id)
                .orElseThrow(RoomAssignmentException.NotFoundException::new);
    }

    @Override
    public List<Map.Entry<Integer, RoomAssignment>> findAll() {
        return roomAssignmentRepository.findAll();
    }

    @Override
    public void removeFromRepository(RoomAssignment roomAssignment) {
        roomAssignmentRepository.remove(roomAssignment.getId());
    }
}
