package org.dms;

import org.dms.configs.Injector;
import org.dms.configs.Seeder;
import org.dms.models.RoomAssignment;
import org.dms.services.spec.*;

import javax.swing.*;
import java.time.LocalDate;

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
        IRoomService roomService = Injector.getService(IRoomService.class);
        IRoomAssignmentService roomAssignmentService = Injector.getService(IRoomAssignmentService.class);

         // Example usage
         // System.out.println(keyService.findAll());
         System.out.println(personService.findAll());
        // System.out.println(personService.findById(1).getEmail());

         // Example usage of adding kitchen key log
         // kitchenKeyLogService.addKitchenKeyLog(LocalDate.now(),4, 3);

        //Example of room usage
        System.out.println("room assignmentn size ---> " + roomAssignmentService.findAll().size());
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
