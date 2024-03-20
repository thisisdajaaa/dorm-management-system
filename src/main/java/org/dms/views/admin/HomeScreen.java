package org.dms.views.admin;

import org.dms.configs.Injector;
import org.dms.services.spec.IAuthenticationService;
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
            System.out.println("\nAuthenticated admin user menu:");
            System.out.println("1. Manage Keys");
            System.out.println("2. Manage Rooms");
            System.out.println("3. Logout");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    new KeyManagementScreen(scanner).showOptions();
                    running = false;
                    break;
                case 2:
                    new RoomManagementScreen(scanner).showOptions();
                    running = false;
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
