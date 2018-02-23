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
        if (!(o instanceof Card)) {
            return false;
        }

        Card card = (Card) o;

        // Custom equality check here.
        return this.value == card.getValue();
    }


    private Suit getSuit() {
        return suit;
    }

}
