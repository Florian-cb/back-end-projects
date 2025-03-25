# Back-End-Projects

1. Blackjack
The Blackjack project is a simple console-based game that allows the player to hit or stand while playing against a dealer. The program uses:

Random number generation to simulate card drawing.
Console input/output for user interaction.
Basic game logic to calculate hand values and determine win/loss conditions.
Although it runs in a command-line environment, the code serves as an example of back-end logic that could be adapted for more complex applications.

Features

Game Mechanics:
Implements card drawing, hand value calculation (handling Aces appropriately), and basic dealer behavior (dealer draws until reaching a threshold).
Aces have a value of 11. If the total hand value results in a bust, Ace value is 1.
Dealer will draw until a hand value of 17 is reached or until the hand value results in a bust, whichever comes first.

User Interaction:
Provides a simple text-based menu to start a game, hit, stand, or quit.

Modular Design:
Uses helper methods (e.g., calculateHandValue(), drawCard(), and dealerTurn()) to separate parts of the game logic.

Constants for Game Limits:
Uses BUST_LIMIT and DEALER_STAND_THRESHOLD to define key rules, making it easy to update game rules.

Technologies

Java SE:
The application uses standard Java libraries:
java.util.Random for generating random card draws.
java.util.Scanner for reading user input from the console.
java.util.ArrayList for managing dynamic lists of cards.

Command-Line Interface (CLI):
Runs as a console application, demonstrating back-end logic without a graphical user interface.


Setup & Running

Prerequisites
Java Development Kit (JDK):
Ensure that you have JDK 8 or higher installed. You can check your version by running:
java -version
Compile and Run
Clone the repository:
git clone https://github.com/your-username/back-end-projects.git
cd back-end-projects
Compile the Java Code:
javac Blackjack.java
Run the Application:
java Blackjack
The program will launch in the terminal and display the Blackjack menu. Follow the on-screen prompts to play the game.

Code Structure & Explanation

Main Class: Blackjack
calculateHandValue(ArrayList<String> hand):
Calculates the best total value of a hand by summing card values, handling face cards (worth 10) and Aces (which can be 1 or 11) appropriately.
drawCard(Random random):
Simulates drawing a card by randomly choosing a number (with special cases for Ace and face cards) and a suit.
dealerTurn(Random random, ArrayList<String> dealerHand):
Contains the logic for the dealer’s turn. The dealer will continue drawing cards until the hand value reaches or exceeds the DEALER_STAND_THRESHOLD.
main(String[] args):
Contains the main game loop:
Presents a menu to the user.
Handles user choices for playing, hitting, standing, or quitting.
Manages the flow of a Blackjack round including initial card dealing, player decisions, and dealer actions.
Uses a labeled loop (gameLoop) to allow clean exits from nested loops when the game is over.
Error Handling & Input Validation
The code checks for invalid input and continues to prompt the user until a valid option is given.
The scanner is properly closed at the end of the program.
