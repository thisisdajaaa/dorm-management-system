package org.dms.views.admin;

import org.dms.configs.Injector;
import org.dms.constants.Role;
import org.dms.constants.RoomStatus;
import org.dms.models.Person;
import org.dms.models.Room;
import org.dms.models.RoomAssignment;
import org.dms.services.spec.IAuthenticationService;
import org.dms.views.Main;
import org.dms.services.spec.IRoomAssignmentService;
import org.dms.services.spec.IRoomRequestService;
import org.dms.services.spec.IRoomService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class RoomManagementScreen {
    private final Scanner scanner;
    private final IAuthenticationService authenticationService;
    private final IRoomAssignmentService roomAssignmentService;
    private final IRoomRequestService roomRequestService;
    private final IRoomService roomService;

    public RoomManagementScreen(Scanner scanner) {
        this.authenticationService = Injector.getService(IAuthenticationService.class);
        this.roomRequestService = Injector.getService(IRoomRequestService.class);
        this.roomAssignmentService = Injector.getService(IRoomAssignmentService.class);
        this.roomService = Injector.getService(IRoomService.class);
        this.scanner = scanner;
    }

    public void showOptions() {
        boolean running = true;

        while (running) {
            System.out.println("Room Management:");
            System.out.println("1. Room status");
            System.out.println("2. Assigned room to a new student");
            System.out.println("3. Room assignment status");
            System.out.println("4. Room Request status");
            System.out.println("5. Change room to a student");
            System.out.println("6. Leave room request");
            System.out.println("7. Logout");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    running = false;
                    break;
                case 2:
                    running = false;
                    break;
                case 3:
                    showRoomAssignments();
                    break;
                case 4:
                    showRequestStatus();
                    break;
                case 5:
                    changeRoom();
                    break;
                case 6:
                    leaveRoom();
                    break;
                case 7:
                    System.out.println("Logging out...");
                    authenticationService.logout();
                    running = false;
                    Main main = new Main();
                    main.executeView();
                    break;
                default:
                    System.out.println("Invalid option provided. Please choose another option.");
                    break;
            }
        }
    }

    private void leaveRoom() {
    }

    private void changeRoom() {
        Optional<Room> availableRoom = findAvailableRoom();
        if(availableRoom.isEmpty()) {
            System.out.println("--------- NO EMPTY ROOM!!! --------");
            return;
        }


    }

    private void showRequestStatus() {
        Optional.ofNullable(roomRequestService.findAll().stream()
                .map(Map.Entry::getValue)).ifPresentOrElse(
                        roomRequests -> roomRequests.forEach(System.out::println),
                ()-> System.out.println("--------- NO ROOM REQUEST!!! --------")
        );
    }

    private void makeNewRoomAssignment() {
        System.out.println("INITIATING A NEW ROOM ASSIGNMENT TO A STUDENT...");

        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Please enter your contact Number: ");
        String contactNumber = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();

        Optional<Room> availableRoom = findAvailableRoom();
        if(availableRoom.isEmpty()) {
            System.out.println("--------- NO EMPTY ROOM!!! --------");
            return;
        }
        Room room = availableRoom.get();
        room.setStatus(RoomStatus.OCCUPIED);
        Person student = new Person(name,email,contactNumber,password, Role.STUDENT);
        RoomAssignment roomAssignment = new RoomAssignment(LocalDate.now(),
                LocalDate.now().plusYears(1), student, room);
        roomAssignmentService.addToRepository(roomAssignment);
    }
    private void showRoomAssignments() {
        roomAssignmentService.findAll()
                .stream()
                .map(Map.Entry::getValue)
                .forEach(System.out::println);
    }
    private void showRooms() {
        roomService.findAll()
                .stream()
                .map(Map.Entry::getValue)
                .forEach(System.out::println);
    }

    private Optional<Room> findAvailableRoom() {
        Optional<Room> roomOption = roomService.checkInRoom();
        if(roomOption.isEmpty()) {
            Optional.empty();
        }
        return roomOption;
    }
}

