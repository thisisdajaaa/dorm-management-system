package org.dms;

import org.dms.configs.Injector;
import org.dms.configs.Seeder;
import org.dms.services.spec.IKeyService;
import org.dms.services.spec.IKitchenKeyLogService;
import org.dms.services.spec.IPersonService;


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

        // Example usage
//         System.out.println(keyService.findAll());
//         System.out.println(personService.findAll());
//         System.out.println(personService.findById(1).getEmail());

        // Example usage of adding kitchen key log
//          kitchenKeyLogService.addKitchenKeyLog(LocalDate.now(),4, 3);
//          System.out.println(keyService.findAll().stream().map(x -> x.getValue().getKeyStatus()).toList().toString());

        // Example usage of checking the primary key status if it is borrowed, lost, or available
//         System.out.println(keyService.checkPrimaryKeyStatus());
    }
}
