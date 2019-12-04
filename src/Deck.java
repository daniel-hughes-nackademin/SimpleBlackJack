import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {

    private List<Card> cards;

    Deck(){
        cards = new ArrayList<>();

        String[] suits = {"\u2660", "\u2665", "\u2666", "\u2663"};
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (String suit : suits) {
            for (String value : values) {
                cards.add(new Card(suit, value));
            }
        }

        shuffleDeck();
    }


    void shuffleDeck(){
        Collections.shuffle(this.cards);
    }

    Card drawCard(Player player){
        Random random = new Random();
        int randomIndex = random.nextInt(cards.size());

        Card drawnCard = this.cards.get(randomIndex);
        this.cards.remove(drawnCard);

        player.getHand().add(drawnCard);
        player.updatePoints();
        if(drawnCard.getValue().equals("A"))
            player.add10PointsAce();

        return drawnCard;
    }

    public List<Card> getCards() {
        return cards;
    }

}
