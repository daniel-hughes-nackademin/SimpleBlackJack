import java.util.ArrayList;
import java.util.List;

class Player {

    private List<Card> hand = new ArrayList<>();
    private int points = 0;
    private int nrOf10PointsAces = 0;
    private int roundsWon = 0;


    List<Card> getHand() {
        return hand;
    }

    void emptyHand() {
        this.hand = new ArrayList<>();
        this.points = 0;
        this.nrOf10PointsAces = 0;
    }

    void changeAceValue() {
        for (Card card : hand) {
            if (card.getValue().equals("A") && card.getPoints() == 10) {
                card.setPoints(1);
                nrOf10PointsAces--;
                updatePoints();
                break;
            }
        }
    }

    public int getNrOf10PointsAces() {
        return nrOf10PointsAces;
    }

    void add10PointsAce(){
        this.nrOf10PointsAces++;
    }

    int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    void updatePoints() {
        this.points = 0;
        for (Card card : hand) {
            this.points += card.getPoints();
        }
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    void roundWin(){
        this.roundsWon++;
    }

    void resetRoundsWon(){
        this.roundsWon = 0;
    }
}
