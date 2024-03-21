package org.dms.views.student;

import org.dms.configs.Injector;
import org.dms.constants.RequestType;
import org.dms.constants.Severity;
import org.dms.models.IssueReport;
import org.dms.models.Person;
import org.dms.models.RoomAssignment;
import org.dms.models.RoomRequest;
import org.dms.services.spec.IAuthenticationService;
import org.dms.services.spec.IIssueReportService;
import org.dms.services.spec.IRoomAssignmentService;
import org.dms.services.spec.IRoomRequestService;
import org.dms.views.Main;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class RoomScreen {
    private final Scanner scanner;
    private final IAuthenticationService authenticationService;
    private final IRoomAssignmentService roomAssignmentService;
    private final IIssueReportService issueReportService;
    private final IRoomRequestService roomRequestService;
    private Person currentLoggedInPerson;

    public RoomScreen(Scanner scanner) {
        this.authenticationService = Injector.getService(IAuthenticationService.class);
        this.scanner = scanner;
        this.roomAssignmentService = Injector.getService(IRoomAssignmentService.class);
        this.issueReportService = Injector.getService(IIssueReportService.class);
        this.roomRequestService = Injector.getService(IRoomRequestService.class);
    }
        public void showRoomScreenOptions() {
            boolean running = true;

            System.out.println("Please select an option:");
            while (running) {
                System.out.println("Room Management:");
                System.out.println("1. Report issues");
                System.out.println("2. Request to change room");
                System.out.println("3. Leave");
                System.out.println("1. Report Room Issues");
                System.out.println("2. Request To Change Room");
                System.out.println("3. Leave Room");
                System.out.println("4. Logout");

                int option = scanner.nextInt();
                scanner.nextLine();

                authenticationService.getCurrentLoggedInUser().ifPresentOrElse(
                        person -> currentLoggedInPerson = person,
                        () -> System.out.println("User needs to be logged in!"));

                switch (option) {
                    case 1:
                        running = false;
                        createIssueReport();
                        System.out.println("Successfully Created Issue Report!");
                        break;
                    case 2:
                        running = false;
                        changeRoomRequest();
                        break;
                    case 3:
                        running = false;
                        leaveRoomRequest();
                        break;
                    case 4:
                        System.out.println("Logging out...");
                        Main main = new Main();
                        main.executeView();
                        break;
                        default:
                            System.out.println("Invalid option provided. Please choose another option.");
                            break;
                }
            }
        }

        public void createIssueReport() {
            System.out.println("Please describe the issue: ");
            String description = scanner.nextLine();
            System.out.println("Please input the severity of the issue (1-5): ");
            int issueSeverity = scanner.nextInt();
            Severity severity = getIssueSeverity(issueSeverity);

            Optional<RoomAssignment> roomAssignmentOfPerson = fetchRoomAssignment();

            if (roomAssignmentOfPerson.isPresent()) {
                IssueReport issueReport = new IssueReport(description, LocalDate.now(), severity,
                        roomAssignmentOfPerson.get());
                issueReportService.save(issueReport);
                System.out.println("Successfully reported room issue");
            } else {
                System.out.println("You currently don't have a Room Assignment.");
            }
        }

        public void changeRoomRequest() {
            Optional<RoomAssignment> roomAssignmentOfPerson = fetchRoomAssignment();
            roomAssignmentOfPerson.ifPresentOrElse(roomAssignment -> {
                RoomRequest roomRequest = new RoomRequest(LocalDate.now(), roomAssignment, RequestType.CHANGE);
                roomRequestService.save(roomRequest);
                System.out.println("Successfully submitted Change Room request.");
            }, () -> System.out.println("You currently don't have a Room Assignment."));
        }

        private Optional<RoomAssignment> fetchRoomAssignment() {
            return roomAssignmentService.findAll()
                    .stream()
                    .filter(roomAssignment -> roomAssignment.getValue().getPerson().equals(currentLoggedInPerson))
                    .map(Map.Entry::getValue)
                    .findFirst();
        }
        public void leaveRoomRequest()
        {
            Optional<RoomAssignment> roomAssignmentOfPerson = fetchRoomAssignment();
            roomAssignmentOfPerson.ifPresentOrElse(roomAssignment ->
            {
                RoomRequest roomRequest = new RoomRequest(LocalDate.now(), roomAssignment, RequestType.LEAVE);
                roomRequestService.save(roomRequest);
                System.out.println("Successfully submitted Leave Room request.");
            }, () -> System.out.println("You currently don't have a Room Assignment."));
        }
        private Severity getIssueSeverity(int severity)
        {
            switch(severity)
            {
                case 1:
                    return Severity.ONE;
                case 2:
                    return Severity.TWO;
                case 3:
                    return Severity.THREE;
                case 4:
                    return Severity.FOUR;
                case 5:
                    return Severity.FIVE;
                default:
                    return Severity.ONE;
            }
        }
}