package org.dms.views.admin;

import org.dms.configs.Injector;
import org.dms.services.spec.IAuthenticationService;

import java.util.Scanner;

public class RoomManagementScreen {
    private final Scanner scanner;
    private final IAuthenticationService authenticationService;

    public RoomManagementScreen(Scanner scanner) {
        this.authenticationService = Injector.getService(IAuthenticationService.class);
        this.scanner = scanner;
    }

    public void showOptions() {
        boolean running = true;

        while (running) {
            System.out.println("Room Management:");
            System.out.println("1. Assign room");
            System.out.println("2. Get available rooms");
            System.out.println("3. Logout");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    running = false;
                    break;
                case 2:
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
