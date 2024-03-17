package org.dms.configs;

import org.dms.annotations.Autowired;
import org.dms.annotations.Component;
import org.dms.models.Person;
import org.dms.constants.Role;
import org.dms.models.Room;
import org.dms.services.spec.IKeyService;
import org.dms.services.spec.IPersonService;
import org.dms.services.spec.IRoomAssignmentService;
import org.dms.services.spec.IRoomService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class Seeder {
    @Autowired
    private IKeyService keyService;

    @Autowired
    private IPersonService personService;
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IRoomAssignmentService roomAssignmentService;

    public void run() {
        seedAdmins();
        seedKeys();
        seedRooms();
        seedRoomAssignment();
    }

    private void seedRoomAssignment() {
        List<Map.Entry<Integer, Person>> persons = personService.findAll();

        persons.stream()
                .map(entry -> entry.getValue())
                .forEach(person -> {
                    if(roomService.checkInRoom().isPresent()) {
                        Room room = roomService.checkInRoom().get();
                        roomAssignmentService.addRoomAssignment(room.roomNumber(), person.getId(),
                                LocalDate.of(2023,02,21), LocalDate.of(202,01,01));
                    }
                });
    }

    private void seedRooms() {
        roomService.addRoom(101);
        roomService.addRoom(102);
    }

    public void seedAdmins() {
        personService.addPerson("Admin1", "admin1@example.com", "1234567890", Role.ADMIN);
        personService.addPerson("Admin2", "admin2@example.com", "0987654321", Role.ADMIN);
        personService.addPerson("Naruto", "naruto@example.com", "123123166", Role.STUDENT);
    }

    public void seedKeys() {
        keyService.addKey(true);
        keyService.addKey(false);
    }
}
