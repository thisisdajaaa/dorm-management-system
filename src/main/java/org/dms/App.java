package org.dms;

import org.dms.configs.Injector;
import org.dms.configs.Seeder;
import org.dms.constants.ReportStatus;
import org.dms.models.IssueReport;
import org.dms.services.spec.IIssueReportService;
import org.dms.services.spec.IKeyService;
import org.dms.services.spec.IKitchenKeyLogService;
import org.dms.services.spec.IPersonService;
import org.dms.models.RoomAssignment;
import org.dms.services.spec.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.lang.StringTemplate.STR;

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

                // Example usage
                // System.out.println(keyService.findAll());
                // System.out.println(personService.findAll());
                // Example usage
                System.out.println("Key List: " + keyService.findAll());
                System.out.println(personService.findAll());
                 System.out.println(personService.findById(1).getEmail());
                //
                // // Example usage of adding kitchen key log
                 kitchenKeyLogService.addKitchenKeyLog(LocalDate.now(),6, 3);
                 System.out.println(keyService.findAll().stream().map(x ->
                 x.getValue().getKeyStatus()).toList().toString());
                //
                 System.out.println(personService.findAll());
                 System.out.println(kitchenKeyLogService.findAll().stream().map(x ->
                 x.getValue()).toList());

                // Example usage of marking kitchen key log complete
                 kitchenKeyLogService.markKitchenKeyLogAsComplete();
                //
                 kitchenKeyLogService.addKitchenKeyLog(LocalDate.now(),6, 3);

                // Example usage of kitchen key log sort by latest start date
                 System.out.println(kitchenKeyLogService.findAllByLatestStartDate());

                // Example of room usage
                 System.out.println("room assignmentn size ---> " +
                 roomAssignmentService.findAll().size());
                 roomAssignmentService.findAll()
                 .stream()
                 .map(e -> e.getValue())
                 .forEach(roomAssignment ->{
                 System.out.println(String.format("..{roomNo: %d, person: %s}..",
                 roomAssignment.getRoom().getRoomNumber(),
                 roomAssignment.getPerson().getName()));
                 });
        }
}
