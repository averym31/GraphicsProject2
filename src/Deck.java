import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cardDeck;

    public Deck(){
        String[] suitList = {"clubs","diamonds","hearts","spades"};
        String[] valueList = {"A", "02", "03", "04", "05", "06", "07", "08", "09", "10", "J", "Q", "K"};
        for (String string : suitList) {
            for (String s : valueList) {
                Card newCard = new Card(string, s);
                cardDeck.add(newCard);
            }
        }
    }

    public Card getRandomCard(){
        int randomInt = (int) (Math.random() * cardDeck.size());
        Card randomCard = cardDeck.get(randomInt);
        cardDeck.remove(cardDeck.get(randomInt));
        return randomCard;
    }
}
