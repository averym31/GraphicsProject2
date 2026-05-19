public class Card {
    private String suit;
    private String value;
    private String filename;

    public Card(String suit, String value){
        this.suit = suit;
        this.value = value;
        filename = "card_" + suit + "_" + value;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    public String toString(){
        return "Suit: " + suit + " Value: " + value + " Filename: " + filename;
    }
}
