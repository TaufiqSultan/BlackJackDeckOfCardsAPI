import interfaces.PlayerEventListener;
import service.GameManager;
import service.InputManager;

public class Main {
    public static void main(String[] args) {
        InputManager inputManager = new InputManager();

        GameManager gameManager = new GameManager();

        PlayerEventListener playerEventListener = new PlayerEventListener() {
            @Override
            public void onPlayerEvent(String message) {
                System.out.println("Event received: " + message);
            }
        };

        gameManager.setPlayerEventListener(playerEventListener);

        while (true) {
            gameManager.startGame(); // Start a new game round
            System.out.println("Do you want to play again? (y/n)");
            String answer = InputManager.getPlayerDecision(); // Get player decision
            if (answer.equals("n")) {
                break; // Exit the loop if the player decides not to play again
            }
        }

        inputManager.closeScanner(); // Close the scanner after the loop
    }
}
