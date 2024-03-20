package org.dms.views.student;

import org.dms.configs.Injector;
import org.dms.services.spec.IAuthenticationService;
import org.dms.views.admin.KeyManagementScreen;
import org.dms.views.admin.RoomManagementScreen;

import java.util.Scanner;

public class HomeScreen {
    private final Scanner scanner;
    private final IAuthenticationService authenticationService;

    public HomeScreen(Scanner scanner) {
        this.authenticationService = Injector.getService(IAuthenticationService.class);
        this.scanner = scanner;
    }

    public void showOptions() {
        boolean running = true;

        while (running) {
            System.out.println("\nAuthenticated student user menu:");
            System.out.println("1. Get key");
            System.out.println("2. Report lost key");
            System.out.println("3. Logout");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Logging out...");
                    authenticationService.logout();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option provided. Please choose another option.");
                    break;
            }
        }
    }
}
