public class Card {

    private String suit;
    private String value;
    private int points;

    Card(String suit, String value) {
        this.suit = suit;
        this.value = value;

        switch (value){
            case "J":
            case "Q":
            case "K":
            case "A":
                this.points = 10;
                break;
            default:
                this.points = Integer.parseInt(value);
                break;
        }
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return this.suit + this.value;
    }
}
