package org.dms.views.admin;

import org.dms.configs.Injector;
import org.dms.services.spec.IAuthenticationService;

import java.util.Scanner;

public class KeyManagementScreen {
    private final Scanner scanner;
    private final IAuthenticationService authenticationService;

    public KeyManagementScreen(Scanner scanner) {
        this.authenticationService = Injector.getService(IAuthenticationService.class);
        this.scanner = scanner;
    }

    public void showOptions() {
        boolean running = true;

        while (running) {
            System.out.println("Key Management:");
            System.out.println("1. Get Key");
            System.out.println("2. Report lost key");
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
