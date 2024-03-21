package org.dms.views;

import org.dms.configs.Injector;
import org.dms.constants.Role;
import org.dms.models.Person;
import org.dms.services.spec.IAuthenticationService;
import org.dms.views.admin.HomeScreen;

import java.util.Scanner;

public class Main {
    private final IAuthenticationService authenticationService;
    private final Scanner scanner;
    private Person currentLoggedInPerson;

    public Main() {
        this.authenticationService = Injector.getService(IAuthenticationService.class);
        this.scanner = new Scanner(System.in);
    }

    public void executeView() {
        System.out.println("Welcome to the Dorm Management System by DOM!");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");

            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                if (isLoginAttemptSuccessful()) {
                    System.out.println("Login successful. Welcome!");
                    showAuthenticatedOptions();
                    break;
                } else {
                    System.out.println("Login failed. Please check your credentials and try again.");
                }
            } else if (option == 2) {
                attemptRegister();
            } else if (option == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid option provided. Please choose another option.");
            }
        }
    }


    private boolean isLoginAttemptSuccessful() {
        System.out.println("Please enter your email:");
        String email = scanner.nextLine();

        System.out.println("Please enter your password:");
        String password = scanner.nextLine();

        try {
            authenticationService.login(email, password);

            if (authenticationService.getCurrentLoggedInUser().isPresent()) {
                currentLoggedInPerson = authenticationService.getCurrentLoggedInUser().get();
            }

            return true;
        } catch (Exception _) {
            return false;
        }
    }


    private void attemptRegister() {
        System.out.println("Please enter your name:");
        String name = scanner.nextLine();

        System.out.println("Please enter your email:");
        String email = scanner.nextLine();

        System.out.println("Please enter your contact number:");
        String contactNumber = scanner.nextLine();

        System.out.println("Please enter your password:");
        String password = scanner.nextLine();

        authenticationService.register(name, email, contactNumber, password);
    }

    private void showAuthenticatedOptions() {
        if (currentLoggedInPerson.getRole().equals(Role.ADMIN)) {
            HomeScreen homeScreen = new HomeScreen(scanner);
            homeScreen.showOptions();
        }
    }
}
