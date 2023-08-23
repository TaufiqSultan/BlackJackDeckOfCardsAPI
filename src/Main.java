import service.GameManager;
import service.InputManager;

public class Main {
    public static void main(String[] args) {
        InputManager inputManager;
        GameManager gameManager = new GameManager();
        inputManager = new InputManager();
        while (true) {
            gameManager.startGame();
            System.out.println("Do you want to play again? (y/n)");
            String answer = inputManager.getPlayerDecision(); // Use getPlayerDecision() here
            if (answer.equals("n")) {
                break;
            }
        }
        inputManager.closeScanner(); // Close the scanner properly
    }
}