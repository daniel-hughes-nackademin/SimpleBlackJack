import javax.swing.*;

import static javax.swing.JOptionPane.*;

public class Game {

    Deck remainingCards = new Deck();
    Player you = new Player();
    Player opponent = new Player();

    private Game(){

        playGame();
    }

    private void playGame() {
        showMessageDialog(null, "Welcome to Black Jack! First to win 5 rounds wins the game!");
        you.resetRoundsWon();
        opponent.resetRoundsWon();

        int numberOfRoundsToWin = 5;
        while (you.getRoundsWon() < numberOfRoundsToWin && opponent.getRoundsWon() < numberOfRoundsToWin){
            playRound();
        }



        if (you.getRoundsWon() == numberOfRoundsToWin)
            showMessageDialog(null, "Congratulations! You Won!");
        else
            showMessageDialog(null, "Opponent won! Better get revenge!");

        int choice = showConfirmDialog(null, "Want to play another time?");
        if(choice == YES_OPTION)
            playGame();
        else
            showMessageDialog(null, "Thanks for playing!");
    }

    private void playRound() {
        resetDeckAndHands();
        opponentPlaysTurn();
        yourTurn();
        showMessageDialog(null, "You: " + you.getRoundsWon() + " - Opponent: " + opponent.getRoundsWon());
    }

    private void resetDeckAndHands() {
        remainingCards = new Deck();
        you.emptyHand();
        opponent.emptyHand();
    }

    private void opponentPlaysTurn() {
        while (opponent.getPoints() < 17){
            remainingCards.drawCard(opponent);
            if(opponent.getPoints() > 21 && opponent.getNrOf10PointsAces() > 0){
                opponent.changeAceValue();
            }
        }

        showMessageDialog(null, "Opponent drew " + opponent.getHand().size() + " cards.");
    }

    private void yourTurn() {
        showMessageDialog(null, "Your turn! Press OK to start");
        boolean isYourTurn = true;
        while (isYourTurn) {
            Card drawnCard = remainingCards.drawCard(you);

            while (you.getPoints() > 21 && you.getNrOf10PointsAces() > 0){
                you.changeAceValue();
            }

            if(you.getPoints() > 21 && you.getNrOf10PointsAces() == 0){
                showMessageDialog(null, "Your hand: " + you.getHand() + you.getPoints() + " points! You went bust! Opponent wins round");
                opponent.roundWin();
                return;
            }
            else {
                int input = showConfirmDialog(null, "You drew " + drawnCard + "! Your hand: " + you.getHand() + " Draw another card?");
                if(input == NO_OPTION)
                    isYourTurn = false;
                else if(input == CANCEL_OPTION){
                    int confirmExit = showConfirmDialog(null, "Sure you want to exit?", "Black Jack", YES_NO_OPTION);
                    if(confirmExit == YES_OPTION)
                        System.exit(0);
                }
            }
        }
        if(opponent.getPoints() > 21){
            showMessageDialog(null, "Opponent went bust with " + opponent.getPoints() + " points! Opponent's hand: " + opponent.getHand());
            showMessageDialog(null, "You win the round!");
            you.roundWin();
            return;
        }

        showMessageDialog(null, "Opponent's points: " + opponent.getPoints() + ", opponent's hand: " + opponent.getHand());
        showMessageDialog(null, "Your points: " + you.getPoints() + ", your hand: " + you.getHand());

        if(you.getPoints() > opponent.getPoints()){
            showMessageDialog(null, "You win with " + you.getPoints() + " points!");
            you.roundWin();
        }
        else if(you.getPoints() < opponent.getPoints()){
            showMessageDialog(null, "Opponent wins with " + opponent.getPoints() + " points!");
            opponent.roundWin();
        }
        else {
            showMessageDialog(null, "You were tied with the opponent at " + you.getPoints() + " points. Round draw!");
        }
    }


    public static void main(String[] args) {
        new Game();
    }
}
