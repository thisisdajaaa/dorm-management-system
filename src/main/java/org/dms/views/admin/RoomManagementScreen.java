package org.dms.views.admin;

import org.dms.configs.Injector;
import org.dms.constants.RequestType;
import org.dms.constants.Role;
import org.dms.constants.RoomStatus;
import org.dms.models.Person;
import org.dms.models.Room;
import org.dms.models.RoomAssignment;
import org.dms.models.RoomRequest;
import org.dms.services.spec.IAuthenticationService;
import org.dms.views.Main;
import org.dms.services.spec.IRoomAssignmentService;
import org.dms.services.spec.IRoomRequestService;
import org.dms.services.spec.IRoomService;

import java.time.LocalDate;
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
            System.out.println("1. Check Room Status");
            System.out.println("2. Make Room Assignment");
            System.out.println("3. Check Room Assignments");
            System.out.println("4. Check Room Requests");
            System.out.println("5. Acknowledge Room Requests");
            System.out.println("6. Logout");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    checkRoomStatus();
                    break;
                case 2:
                    makeRoomAssignment();
                    break;
                case 3:
                    checkRoomAssignments();
                    break;
                case 4:
                    checkRoomRequest();
                    break;
                case 5:
                    acknowledgeRoomRequests();
                    break;
                case 6:
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

    private void acknowledgeRoomRequests() {
        if(roomRequestService.findAll().isEmpty()) {
            System.out.println("--------- NO ROOM REQUEST!!! --------");
            return;
        }
        roomRequestService.findAll()
                .stream().map(Map.Entry::getValue)
                .forEach(roomRequest ->  {
                           if(roomRequest.getRequestType() == RequestType.CHANGE) {
                               changeRoom(roomRequest);
                           } else {
                               leaveRoom(roomRequest);
                           }
                    roomRequest.setResolved(true);
                });
    }



    private void leaveRoom(RoomRequest roomRequest) {
        roomRequestService.acknowledgeRoomRequest(roomRequest.getId());
    }

    private void changeRoom(RoomRequest roomRequest) {
        Optional<Room> availableRoom = roomService.checkInRoom();
        if(availableRoom.isEmpty()) {
            System.out.println("--------- NO AVAILABLE ROOM!!! --------");
            return;
        }

        Room room = availableRoom.get();
        roomRequestService.acknowledgeRoomRequest(roomRequest.getId());
    }

    private void checkRoomRequest() {
        Optional.ofNullable(roomRequestService.findAll().stream()
                .map(Map.Entry::getValue)).ifPresentOrElse(
                        roomRequests -> roomRequests.forEach(System.out::println),
                ()-> System.out.println("--------- NO ROOM REQUEST!!! --------")
        );
    }

    private void makeRoomAssignment() {
        System.out.println("INITIATING A NEW ROOM ASSIGNMENT TO A STUDENT...");
        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Please enter your contact Number: ");
        String contactNumber = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();

        Optional<Room> availableRoom = roomService.checkInRoom();
        availableRoom.ifPresentOrElse(
                room -> {
                    room.setStatus(RoomStatus.OCCUPIED);
                    Person student = new Person(name,email,contactNumber,password, Role.STUDENT);
                    roomAssignmentService.addToRepository(new RoomAssignment(LocalDate.now(),
                            LocalDate.now().plusYears(1), student, room));
                },
                () -> System.out.println("--------- NO EMPTY ROOM!!! --------")
        );
    }
    private void checkRoomAssignments() {
        roomAssignmentService.findAll()
                .stream()
                .map(Map.Entry::getValue)
                .forEach(System.out::println);
    }
    private void checkRoomStatus() {
        roomService.findAll()
                .stream()
                .map(Map.Entry::getValue)
                .forEach(System.out::println);
    }
}

