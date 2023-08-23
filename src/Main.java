import interfaces.PlayerEventListener;
import service.GameManager;
import service.InputManager;

public class Main {
    public static void main(String[] args) {
        // Create an instance of InputManager to handle user input
        InputManager inputManager = new InputManager();

        // Create an instance of GameManager to manage the game logic
        GameManager gameManager = new GameManager();

        // Define a PlayerEventListener implementation to handle player events
        PlayerEventListener playerEventListener = new PlayerEventListener() {
            @Override
            public void onPlayerEvent(String message) {
                System.out.println("Event received: " + message);
            }
        };

        // Set the player event listener for the game manager
        gameManager.setPlayerEventListener(playerEventListener);

        // Start a new game and repeat as long as the player wants to continue
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
