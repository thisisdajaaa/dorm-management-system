package org.dms;

import org.dms.configs.Injector;
import org.dms.configs.Seeder;
import org.dms.constants.RequestType;
import org.dms.models.RoomAssignment;
import org.dms.models.RoomRequest;
import org.dms.constants.Severity;
import org.dms.models.RoomAssignment;
import org.dms.services.spec.IIssueReportService;
import org.dms.services.spec.IKeyService;
import org.dms.services.spec.IKitchenKeyLogService;
import org.dms.services.spec.IPersonService;
import org.dms.services.spec.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        Injector.startApplication(App.class);

        // Inject the seeder and run it
        Seeder seeder = Injector.getService(Seeder.class);
        seeder.run();

        // Utilize the key service
        IPersonService personService = Injector.getService(IPersonService.class);
        IKeyService keyService = Injector.getService(IKeyService.class);
        IKitchenKeyLogService kitchenKeyLogService = Injector.getService(IKitchenKeyLogService.class);
        IIssueReportService issueReportService = Injector.getService(IIssueReportService.class);
        IRoomService roomService = Injector.getService(IRoomService.class);
        IRoomAssignmentService roomAssignmentService = Injector.getService(IRoomAssignmentService.class);
        IRoomRequestService roomRequestService = Injector.getService(IRoomRequestService.class);
        IAuthenticationService authenticationService = Injector.getService(IAuthenticationService.class);

        // Example usage
        System.out.println(keyService.findAll());
        System.out.println(personService.findAll());
        // Example usage
        System.out.println("Key List: " + keyService.findAll());
        System.out.println(personService.findAll());
        System.out.println(personService.findById(1).getEmail());
        //
        // Example usage of adding kitchen key log
        kitchenKeyLogService.addKitchenKeyLog(LocalDate.now(), 6, 3);
        System.out.println(keyService.findAll().stream().map(x ->
                x.getValue().getKeyStatus()).toList().toString());

        System.out.println(personService.findAll());
        System.out.println(kitchenKeyLogService.findAll().stream().map(x ->
                x.getValue()).toList());

        // Example usage of marking kitchen key log complete
        kitchenKeyLogService.markKitchenKeyLogAsComplete();
        kitchenKeyLogService.addKitchenKeyLog(LocalDate.now(), 6, 3);

        // Example usage of kitchen key log sort by latest start date
        System.out.println(kitchenKeyLogService.findAllByLatestStartDate());
        System.out.println("BEFORE MAKING ROOM CHANGE/LEAVE REQUEST (ROOM ASSIGNMENT) ====================\n" + roomAssignmentService.findAll());
        List<RoomAssignment> allRoomAssignments = roomAssignmentService
                .findAll()
                .stream()
                .map(Map.Entry::getValue).toList();
        //leave request
        exampleLeaveRoomRequest(allRoomAssignments, roomRequestService, roomAssignmentService);
        //change room request
        exampleChangeRoomRequest(allRoomAssignments, roomRequestService, roomAssignmentService);
        // This is to test the reportStolenKey functionality
         System.out.println("BEFORE REPORT ====================\n" + keyService.findAll());
         keyService.reportStolenKey();
         System.out.println("AFTER REPORT====================\n" + keyService.findAll());

         // Test login functionality
         System.out.println("LOGGGINN");
         authenticationService.login("naruto@example.com", "test12345");

         System.out.println("user: " + personService.findById(3));
         System.out.println("user: " + personService.findAll());

        //This is to test the addIssueReport functionality
        Optional<RoomAssignment> roomAssignment = roomAssignmentService.findAll()
                .stream()
                .map(Map.Entry::getValue)
                .findFirst();

        System.out.println("Here are the Issue Reports Before : " + issueReportService.findAll());
        issueReportService.addIssueReport("Sink clogged", LocalDate.now(), Severity.THREE,roomAssignment.get());
        issueReportService.addIssueReport("Heater not working", LocalDate.now(), Severity.THREE,roomAssignment.get());
        System.out.println("Here are the Issue Reports After : " + issueReportService.findAll());
    }

    private static void exampleLeaveRoomRequest(List<RoomAssignment> allRoomAssignments, IRoomRequestService roomRequestService, IRoomAssignmentService roomAssignmentService) {
        RoomRequest roomRequest = new RoomRequest(LocalDate.now(),
                allRoomAssignments.get(1), RequestType.LEAVE);
        roomRequestService.save(roomRequest);
        roomRequestService.acknowledgeRoomRequest(roomRequest.getId());
        System.out.println("AFTER MAKING ROOM LEAVE REQUEST (ROOM ASSIGNMENT) ====================\n" + roomAssignmentService.findAll());
    }

    private static void exampleChangeRoomRequest(List<RoomAssignment> allRoomAssignments, IRoomRequestService roomRequestService, IRoomAssignmentService roomAssignmentService) {
        RoomRequest request1 = new RoomRequest(LocalDate.now(),
                allRoomAssignments.get(0), RequestType.CHANGE);
        roomRequestService.save(request1);
        roomRequestService.acknowledgeRoomRequest(request1.getId());
        System.out.println("AFTER MAKING ROOM CHANGE REQUEST (ROOM ASSIGNMENT) ====================\n" + roomAssignmentService.findAll());
    }
}
