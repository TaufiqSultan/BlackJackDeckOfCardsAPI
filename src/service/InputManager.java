package service;

import java.util.Scanner;

public class InputManager {
    private Scanner scanner;

    public InputManager() {
        this.scanner = new Scanner(System.in);
    }

    public String getPlayerDecision() {
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

    public void closeScanner() {
        scanner.close();
    }
}
