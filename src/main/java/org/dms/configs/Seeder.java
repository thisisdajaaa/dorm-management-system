package org.dms.configs;

import org.dms.annotations.Autowired;
import org.dms.annotations.Component;
import org.dms.constants.RoomStatus;
import org.dms.models.Person;
import org.dms.constants.Role;
import org.dms.models.Room;
import org.dms.models.RoomAssignment;
import org.dms.services.spec.IKeyService;
import org.dms.services.spec.IPersonService;
import org.dms.services.spec.IRoomAssignmentService;
import org.dms.services.spec.IRoomService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        List<Room> rooms = roomService.findAll().stream().map(Map.Entry::getValue).toList();

        persons.stream()
                .map(Map.Entry::getValue)
                .filter(person -> person.getRole() == Role.STUDENT)
                .forEach(person -> {
                    Optional<Room> roomOption = getRoomOption(rooms);
                    if(roomOption.isPresent()) {
                        Room room = roomOption.get();
                        room.setStatus(RoomStatus.OCCUPIED);
                        RoomAssignment roomAssignment = new RoomAssignment(
                                LocalDate.of(2023, 01, 01),
                                LocalDate.of(2024, 01, 01),
                                person, room);
                        roomAssignmentService.addToRepository(roomAssignment);
                        System.out.println("ROOM ASSIGNMENT ADD.........");
                    }
                });
    }

    private static Optional<Room> getRoomOption(List<Room> rooms) {
        for (Room room: rooms) {
            if(room.getStatus() == RoomStatus.AVAILABLE) {
                return Optional.of(room);
            }
        }
        return Optional.empty();
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
