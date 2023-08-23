package service;

import java.util.Scanner;

public class InputManager {
    private static Scanner scanner;

    public InputManager() {
        this.scanner = new Scanner(System.in);
    }

    // Constructor initializes the scanner instance, using the Singleton pattern-like approach

    public static String getPlayerDecision() {
        while (true) {
            System.out.print("Do you want to hit or stand? ");
            try {
                String decision = scanner.nextLine().trim().toLowerCase();
                if (decision.equals("hit") || decision.equals("stand")) {
                    return decision;
                } else {
                    System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
            }
        }
    }

    // Method for getting player decisions, involving user input and error handling

    public void closeScanner() {
        scanner.close();
    }

}
