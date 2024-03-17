package org.dms;

import org.dms.configs.Injector;
import org.dms.configs.Seeder;
import org.dms.services.spec.IKeyService;
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


        // Example usage
        System.out.println(keyService.findAll());
        System.out.println(personService.findById(1).getEmail());
    }
}
