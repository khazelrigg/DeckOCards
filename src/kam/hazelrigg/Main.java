package kam.hazelrigg;

import kam.hazelrigg.Cards.Card;
import kam.hazelrigg.Cards.Deck;

public class Main {

    public static void main(String[] args) {
        Card myCard = new Card(11, Card.Suit.DIAMONDS);
        System.out.println(myCard);

        Deck myDeck = new Deck(6);
        myDeck.addCard(myCard);
        myDeck.addCard(new Card(2, Card.Suit.CLUBS));
        myDeck.addCard(new Card(1, Card.Suit.HEARTS));
        myDeck.addCard(new Card(6, Card.Suit.SPADES));
        System.out.println(myDeck);
        myDeck.shuffleCards();
        System.out.println(myDeck);
        myDeck.drawCard();
        System.out.println(myDeck);

        Deck defaultDeck = new Deck();
       // defaultDeck.shuffleCards();
        System.out.println(defaultDeck);
    }
}
