package org.dms.views.student;

import org.dms.configs.Injector;
import org.dms.services.spec.IAuthenticationService;
import org.dms.views.Main;

import java.util.Scanner;

public class RoomScreen {
    private final Scanner scanner;
    private final IAuthenticationService authenticationService;

    public RoomScreen(Scanner scanner) {
        this.authenticationService = Injector.getService(IAuthenticationService.class);
        this.scanner = scanner;
    }

    public void showOptions() {
        boolean running = true;

        while (running) {
            System.out.println("Room Management:");
            System.out.println("1. Report issues");
            System.out.println("2. Request to change room");
            System.out.println("3. Leave");
            System.out.println("4. Logout");

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
                    running = false;
                    break;
                case 4:
                    System.out.println("Logging out...");
                    authenticationService.logout();
                    running = false;
                    Main main = new Main();
                    main.executeView();
                    break;
                default:
                    System.out.println("Invalid option provided. Please choose another option.");
                    break;
            }
        }
    }
}
