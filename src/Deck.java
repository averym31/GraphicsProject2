import java.util.ArrayList;
import java.util.Objects;

public class Deck {
    public ArrayList<Card> cardDeck;

    public Deck(){
        cardDeck = new ArrayList<Card>();
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
        cardDeck.remove(randomInt);
        return randomCard;
    }

    public ArrayList<Card> getCardDeck(){
        return cardDeck;
    }

    public boolean validMoveSet(Card[][] cards){
        boolean check = false;
        int sum = 0;
        int numJack = 0;
        int numQueen = 0;
        int numKing = 0;
        for (int r = 0; r < cards.length; r++) {
            for (int c = 0; c < cards[0].length; c++) {
                if (cards[r][c].getValue() == "J"){
                    numJack++;
                }
                if (cards[r][c].getValue() == "Q"){
                    numQueen++;
                }
                if (cards[r][c].getValue() == "K"){
                    numKing++;
                }
            }
        }
        if (numJack > 0 && numQueen > 0 && numKing > 0){
            check = true;
        }
        else {
            ArrayList<Integer> intCards = getIntegers(cards);
            for (int i = 0; i < intCards.size(); i++){
                for (int j = 0; j < intCards.size(); j++){
                    sum = 0;
                    if (i != j){
                        sum = intCards.get(i) + intCards.get(j);
                    }
                    if (sum == 11){
                        check = true;
                        return check;
                    }
                }
            }
        }
        return check;
    }

    private static ArrayList<Integer> getIntegers(Card[][] cards) {
        ArrayList<Integer> cardsWithoutJQK = new ArrayList<>();
        for (Card[] card : cards) {
            for (int c = 0; c < cards[0].length; c++) {
                if (!Objects.equals(card[c].getValue(), "J") && !Objects.equals(card[c].getValue(), "Q") && !Objects.equals(card[c].getValue(), "K")) {
                    if (Objects.equals(card[c].getValue(), "A")) {
                        cardsWithoutJQK.add(1);
                    } else {
                        cardsWithoutJQK.add(Integer.parseInt(card[c].getValue()));
                    }
                }
            }
        }
        return cardsWithoutJQK;
    }
}
