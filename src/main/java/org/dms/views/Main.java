package org.dms.views;

import org.dms.configs.Injector;
import org.dms.services.spec.IAuthenticationService;

import java.util.Scanner;

public class Main {
    private final IAuthenticationService authenticationService;
    private final Scanner scanner;

    public Main() {
        this.authenticationService = Injector.getService(IAuthenticationService.class);
        this.scanner = new Scanner(System.in);
    }

    public void executeView() {
        System.out.println("Welcome to the Dorm Management System by DOM!");

        while (true) {
            // Check if user is already logged in
            if (authenticationService.getCurrentLoggedInUser().isPresent()) {
                // User is logged in, show authenticated options
                showAuthenticatedOptions();
                break;  // Break the loop if we've moved on to authenticated options
            } else {
                // User is not logged in, show login/register screen
                System.out.println("\nSelect an option:");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");

                int option = scanner.nextInt();
                scanner.nextLine(); // consume the rest of the line

                if (option == 1) {
                    if (isLoginAttemptSuccessful()) {
                        System.out.println("Login successful. Welcome!");
                        showAuthenticatedOptions();
                        break;  // Break the loop if login is successful
                    } else {
                        System.out.println("Login failed. Please check your credentials and try again.");
                    }
                } else if (option == 2) {
                    attemptRegister();
                    // Do not break after registration; the loop will continue and show the main menu again
                } else if (option == 3) {
                    System.out.println("Exiting...");
                    break;  // Exit the application
                } else {
                    System.out.println("Invalid option provided. Please choose another option.");
                }
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
            return true;
        } catch (Exception _) {
            return false;
        }
    }


    private void attemptRegister() {
        // Implement the logic to register a new user
        // After successful registration, you might want to log the user in directly
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
        boolean running = true;

        while (running) {
            System.out.println("\nAuthenticated user menu:");
            System.out.println("1. Some Option");
            System.out.println("2. Another Option");
            System.out.println("3. Logout");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume the rest of the line

            switch (option) {
                case 1:
                    // Handle "Some Option"
                    break;
                case 2:
                    // Handle "Another Option"
                    break;
                case 3:
                    System.out.println("Logging out...");
                    authenticationService.logout(); // Assuming there is a logout method
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option provided. Please choose another option.");
                    break;
            }
        }
    }
}
