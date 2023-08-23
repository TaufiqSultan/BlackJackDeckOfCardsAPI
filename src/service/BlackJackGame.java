package service;


import model.Card;
import model.Deck;
import model.Player;

import java.io.IOException;
import java.util.Scanner;

public class BlackJackGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Blackjack!");

        try {
            Deck deck = Deck.createNewDeck();
            deck.shuffle();

            Player player = new Player("Player");
            Player dealer = new Player("Dealer");

            // Initial deal: Player and Dealer each get two cards
            player.addDealtCard(deck.drawCards(2));
            dealer.addDealtCard(deck.drawCards(2));

            // Show initial cards
            System.out.println("Player's cards: " + player.getDealtCards());
            System.out.println("Dealer's visible card: " + dealer.getDealtCards().get(0));

            // Player's turn
            while (true) {
                System.out.print("Do you want to hit or stand? ");
                String decision = scanner.nextLine().toLowerCase();

                if (decision.equals("hit")) {
                    player.addDealtCard(deck.drawCards(1));
                    System.out.println("Player's cards: " + player.getDealtCards());

                    if (calculatePoints(player) > 21) {
                        System.out.println("Player busts! Dealer wins.");
                        break;
                    }
                } else if (decision.equals("stand")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
                }
            }

            // Dealer's turn
            while (calculatePoints(dealer) < 17) {
                dealer.addDealtCard(deck.drawCards(1));
            }
            System.out.println("Dealer's cards: " + dealer.getDealtCards());

            // Determine winner
            int playerPoints = calculatePoints(player);
            int dealerPoints = calculatePoints(dealer);

            if (playerPoints > 21) {
                System.out.println("Dealer wins!");
            } else if (dealerPoints > 21 || playerPoints > dealerPoints) {
                System.out.println("Player wins!");
            } else if (playerPoints < dealerPoints) {
                System.out.println("Dealer wins!");
            } else {
                System.out.println("It's a tie!");
            }

        } catch (IOException e) {
            System.out.println("Error connecting to the API: " + e.getMessage());
        }

        scanner.close();
    }

    private static int calculatePoints(Player player) {
        int points = 0;
        for (Card card : player.getDealtCards()) {
            String cardValue = card.getValue();
            if ("KING QUEEN JACK".contains(cardValue)) {
                points += 10;
            } else if (cardValue.equals("ACE")) {
                points += 11;
            } else {
                points += Integer.parseInt(cardValue);
            }
        }
        return points;
    }
}