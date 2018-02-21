package kam.hazelrigg;

import kam.hazelrigg.Cards.Card;
import kam.hazelrigg.Cards.Deck;

import java.util.ArrayList;
import java.util.Collections;

public class War {
    private Deck p1Deck = new Deck(26);
    private Deck p2Deck = new Deck(26);
    private int turns = 0;

    public War() {
        giveDecks();
        System.out.println(p1Deck.getDeckString());
        System.out.println(p2Deck.getDeckString());
    }

    public void play() {

    }

    private void giveDecks() {
        Deck starter = new Deck();
        starter.shuffleCards();
        ArrayList<Card> deck = starter.getDeck();

        for (int i = 0; i < 26; i++) {
            p1Deck.addCard(deck.get(i));
        }
        for (int i = 26; i < 52; i++) {
            p2Deck.addCard(deck.get(i));
        }

    }

    boolean takeTurn() {
        Card p1Card = p1Deck.drawCard();
        Card p2Card = p2Deck.drawCard();
        if (p1Card.getValue() > p2Card.getValue()) {
            p1Deck.addToBottom(p1Card);
            p1Deck.addToBottom(p2Card);
        } else {
            p2Deck.addToBottom(p1Card);
            p2Deck.addToBottom(p2Card);
        }
        //System.out.println(p1Deck.getDeckString() + " SIZE: " + p1Deck.getSize() + "\n" + p2Deck.getDeckString() + " SIZE: " + p2Deck.getSize());
        return p1Deck.getSize() == 52 || p2Deck.getSize() == 52;
    }

    private void declareWar() {
        Card p1First = p1Deck.drawCard();
        Card p1Second = p1Deck.drawCard();

        Card p2First = p2Deck.drawCard();
        Card p2Second = p2Deck.drawCard();
    }

    int getTurns() {
        return turns;
    }

}
