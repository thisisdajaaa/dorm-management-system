package org.dms.views.student;

import org.dms.configs.Injector;
import org.dms.constants.KeyStatus;
import org.dms.models.Key;
import org.dms.models.Person;
import org.dms.services.spec.IAuthenticationService;
import org.dms.services.spec.IKeyService;
import org.dms.services.spec.IKitchenKeyLogService;
import org.dms.views.Main;

import java.time.LocalDate;
import java.util.Scanner;

public class KitchenKeyScreen {
    private final Scanner scanner;
    private final IAuthenticationService authenticationService;
    private final IKeyService keyService;
    private final IKitchenKeyLogService kitchenKeyLogService;
    private Person currentLoggedInPerson;

    public KitchenKeyScreen(Scanner scanner) {
        this.authenticationService = Injector.getService(IAuthenticationService.class);
        this.keyService = Injector.getService(IKeyService.class);
        this.kitchenKeyLogService = Injector.getService(IKitchenKeyLogService.class);
        this.scanner = scanner;
    }

    public void showOptions() {
        boolean running = true;

        authenticationService.getCurrentLoggedInUser().ifPresentOrElse(
                person -> currentLoggedInPerson = person,
                () -> System.out.println("User needs to be logged in!")
        );

        while (running) {
            System.out.println("Key Management:");
            System.out.println("1. View current borrower");
            System.out.println("2. Get key");
            System.out.println("3. Report lost key");
            System.out.println("4. Change key status to available");
            System.out.println("5. Logout");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    handleViewCurrentBorrower();
                    break;
                case 2:
                    handleGetKey();
                    break;
                case 3:
                    handleReportLostKey();
                    break;
                case 4:
                    handleChangeKeyStatusToAvailable();
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

    private void handleGetKey() {
        Key primaryKey = keyService.getPrimaryKey();

        kitchenKeyLogService.addKitchenKeyLog(LocalDate.now(), primaryKey.getId(), currentLoggedInPerson.getId());

        System.out.println("Successfully borrowed the kitchen key!");
    }

    private void handleReportLostKey() {
        keyService.reportStolenKey();
        kitchenKeyLogService.markKitchenKeyLogAsComplete(KeyStatus.LOST);

        System.out.println("Successfully reported that the kitchen key is lost!");
    }

    private void handleChangeKeyStatusToAvailable() {
        kitchenKeyLogService.markKitchenKeyLogAsComplete(KeyStatus.AVAILABLE);

        System.out.println("Successfully set the kitchen key status to available!");
    }
}
