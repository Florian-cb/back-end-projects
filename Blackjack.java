import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Blackjack {

    private static final int BUST_LIMIT = 21;
    private static final int DEALER_STAND_THRESHOLD = 17;

    // Function to calculate the best hand value
    public static int calculateHandValue(ArrayList<String> hand) {
        int total = 0;
        int aces = 0;

        for (String card : hand) {
            String cardValue = card.substring(0, card.length() - 1);
            if (cardValue.equals("K") || cardValue.equals("Q") || cardValue.equals("J")) {
                total += 10;
            } else if (cardValue.equals("A")) {
                aces++;
                total += 1;
            } else {
                total += Integer.parseInt(cardValue);
            }
        }

        // Upgrade Ace values when possible
        while (total <= 11 && aces > 0) {
            total += 10;
            aces--;
        }

        return total;
    }

    public static String drawCard(Random random) {
        int card = random.nextInt(13) + 1;
        String[] suits = { "♠", "♥", "♦", "♣" };
        String suit = suits[random.nextInt(4)];
        if (card == 1) {
            return "A" + suit;
        } else if (card > 10) {
            switch (card) {
                case 11:
                    return "J" + suit;
                case 12:
                    return "Q" + suit;
                case 13:
                    return "K" + suit;
            }
        }
        return card + suit;
    }

    // Method to handle dealer's turn
    public static void dealerTurn(Random random, ArrayList<String> dealerHand) {
        int dealerTotal = calculateHandValue(dealerHand);
        while (dealerTotal < DEALER_STAND_THRESHOLD) {
            String newCard = drawCard(random);
            dealerHand.add(newCard);
            dealerTotal = calculateHandValue(dealerHand);
            System.out.println("Dealer drew a " + newCard + ".");
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        boolean inGame = false;

        gameLoop: while (true) {
            if (!inGame) {
                System.out.println("Blackjack!");
                System.out.println("Press P to play, Q to quit anytime");
            } else {
                System.out.println("Press P to play again, M for main menu, Q to quit anytime");
            }
            String menuChoice = scanner.next();

            if (menuChoice.equalsIgnoreCase("q")) {
                System.out.println("Thanks for playing!");
                break;
            } else if (menuChoice.equalsIgnoreCase("p")) {
                inGame = true;
                // Draw initial cards for player and dealer
                String playerCard1 = drawCard(random);
                String playerCard2 = drawCard(random);
                String dealerCard1 = drawCard(random);
                String dealerCard2 = drawCard(random);

                // Create ArrayLists to store hands
                ArrayList<String> playerHand = new ArrayList<>();
                ArrayList<String> dealerHand = new ArrayList<>();
                playerHand.add(playerCard1);
                playerHand.add(playerCard2);
                dealerHand.add(dealerCard1);
                dealerHand.add(dealerCard2);

                int playerTotal = calculateHandValue(playerHand);
                int dealerTotal = calculateHandValue(dealerHand);

                // Player's turn
                while (true) {
                    System.out.println("Dealer's hand: " + dealerHand.get(0) + " ??");
                    System.out.print("Your hand: ");
                    for (String card : playerHand) {
                        System.out.print(card + " ");
                    }
                    System.out.println();
                    System.out.println("Do you want to (H)it or (S)tand?");
                    String choice = scanner.next();

                    if (choice.equalsIgnoreCase("h")) {
                        String newCard = drawCard(random);
                        playerHand.add(newCard);
                        playerTotal = calculateHandValue(playerHand);
                        System.out.println("You drew a " + newCard + ".");
                        if (playerTotal > BUST_LIMIT) {
                            System.out.println("Bust! You lose.");
                            continue gameLoop;
                        }
                    } else if (choice.equalsIgnoreCase("s")) {
                        System.out.println("Dealer reveals his face down card: " + dealerHand.get(1));
                        dealerTurn(random, dealerHand);
                        System.out.print("Dealer's hand: ");
                        for (String card : dealerHand) {
                            System.out.print(card + " ");
                        }
                        System.out.println();
                        System.out.print("Your hand: ");
                        for (String card : playerHand) {
                            System.out.print(card + " ");
                        }
                        System.out.println();

                        dealerTotal = calculateHandValue(dealerHand);
                        if (dealerTotal > BUST_LIMIT || playerTotal > dealerTotal) {
                            System.out.println("You win!");
                        } else if (dealerTotal > playerTotal) {
                            System.out.println("You lose.");
                        } else {
                            System.out.println("It's a tie!");
                        }
                        break;
                    } else if (choice.equalsIgnoreCase("q")) {
                        System.out.println("Thanks for playing!");
                        break gameLoop;
                    } else {
                        System.out.println("Invalid Input. Please try again.");
                    }
                }
            } else if (menuChoice.equalsIgnoreCase("m")) {
                if (inGame) {
                    inGame = false;
                    continue;
                } else {
                    System.out.println("Invalid Input. Please try again.");
                }
            } else {
                System.out.println("Invalid Input. Please try again.");
            }
        }
        scanner.close();
    }
}
