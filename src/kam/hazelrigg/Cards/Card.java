package kam.hazelrigg.Cards;

public class Card {
    public enum Suit {HEARTS, DIAMONDS, CLUBS, SPADES}
    private int value;
    private Suit suit;

    public Card(int value, Suit suit) {
        if (value < 1 || value > 13) {
            throw new IllegalArgumentException("Card value out of range 1 - 13");
        }
        this.value = value;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return value + getSuitSymbol();
    }

    public String toString(boolean b) {
        if (b) {
            return getValueString() + getSuitSymbol();
        }
        return toString();
    }

    private String getValueString() {
        switch (value) {
            case 13:
                return "K";
            case 12:
                return "Q";
            case 11:
                return "J";
            case 1:
                return "A";
            default:
                return String.valueOf(value);
        }
    }

    private String getSuitSymbol() {
        switch (suit) {
            case DIAMONDS:
                return "♦";
            case SPADES:
                return "♠";
            case HEARTS:
                return "♥";
            case CLUBS:
                return "♣";
            default:
                return "";
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        // Verify instances before attempting value comparison
        if (!(o instanceof Card)) {
            return false;
        }
        Card card = (Card) o;


        return this.value == card.value && this.suit == card.suit;
    }

    public Suit getSuit() {
        return suit;
    }

}
