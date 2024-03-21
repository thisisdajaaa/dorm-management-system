package org.dms.views.student;

import org.dms.configs.Injector;
import org.dms.services.spec.IAuthenticationService;
import org.dms.views.Main;

import java.util.Scanner;

public class StudentHomeScreen {
    private final Scanner scanner;
    private final IAuthenticationService authenticationService;

    public StudentHomeScreen(Scanner scanner) {
        this.authenticationService = Injector.getService(IAuthenticationService.class);
        this.scanner = scanner;
    }

    public void showOptions() {
        boolean running = true;

        while (running) {
            System.out.println("\nAuthenticated student user menu:");
            System.out.println("1. Kitchen key");
            System.out.println("2. Room");
            System.out.println("3. Logout");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    new KitchenKeyScreen(scanner).showOptions();
                    running = false;
                    break;
                case 2:
                    new RoomScreen(scanner).showOptions();
                    running = false;
                    break;
                case 3:
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
