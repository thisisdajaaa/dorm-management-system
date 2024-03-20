package org.dms;

import org.dms.configs.Injector;
import org.dms.configs.Seeder;
import org.dms.constants.Severity;
import org.dms.models.RoomAssignment;
import org.dms.services.spec.IIssueReportService;
import org.dms.services.spec.IKeyService;
import org.dms.services.spec.IKitchenKeyLogService;
import org.dms.services.spec.IPersonService;
import org.dms.services.spec.*;
import org.dms.views.Main;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        Injector.startApplication(App.class);

        // Inject the seeder and run it
        Seeder seeder = Injector.getService(Seeder.class);
        seeder.run();

        Main main = new Main();
        main.executeView();

    }
}
