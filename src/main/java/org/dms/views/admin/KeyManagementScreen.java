package org.dms.views.admin;

import org.dms.configs.Injector;
import org.dms.services.spec.IAuthenticationService;
import org.dms.services.spec.IKeyService;
import org.dms.services.spec.IKitchenKeyLogService;
import org.dms.views.Main;

import java.util.Scanner;

public class KeyManagementScreen {
    private final Scanner scanner;
    private final IAuthenticationService authenticationService;
    private final IKeyService keyService;
    private final IKitchenKeyLogService kitchenKeyLogService;

    public KeyManagementScreen(Scanner scanner) {
        this.authenticationService = Injector.getService(IAuthenticationService.class);
        this.keyService = Injector.getService(IKeyService.class);
        this.kitchenKeyLogService = Injector.getService(IKitchenKeyLogService.class);
        this.scanner = scanner;
    }

    public void showOptions() {
        boolean running = true;

        while (running) {
            System.out.println("Key Management:");
            System.out.println("1. View current borrower");
            System.out.println("2. View borrowing history");
            System.out.println("3. View list of keys");
            System.out.println("4. Add key");
            System.out.println("5. Logout");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    handleViewCurrentBorrower();
                    break;
                case 2:
                    handleViewBorrowingHistory();
                    break;
                case 3:
                    handleViewKeys();
                    break;
                case 4:
                    handleAddKey();
                    break;
                case 5:
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

    private void handleViewCurrentBorrower() {
        System.out.println("Current Borrower:");

        kitchenKeyLogService.getOpenKeyLog().ifPresentOrElse(System.out::println, () -> {
                    System.out.println("There are no current borrower for the kitchen key");
                }
        );
    }

    private void handleViewBorrowingHistory() {
        System.out.println("Borrow History Logs:");

        if (kitchenKeyLogService.findAll().isEmpty()) {
            System.out.println("No result...");
            return;
        }

        kitchenKeyLogService.findAllByLatestStartDate().forEach(System.out::println);
    }

    private void handleAddKey() {
        keyService.addKey();

        System.out.println("Successfully added a duplicate key!");
    }

    private void handleViewKeys() {
        System.out.println("Kitchen Keys:");

        if (keyService.findAll().isEmpty()) {
            System.out.println("No result...");
            return;
        }

        keyService.findAll().forEach(System.out::println);
    }
}
