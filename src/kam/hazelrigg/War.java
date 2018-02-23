package kam.hazelrigg;

import kam.hazelrigg.Cards.Card;
import kam.hazelrigg.Cards.Deck;

import java.util.ArrayList;
import java.util.Collections;

public class War {
    private Deck p1Deck = new Deck(26);
    private Deck p2Deck = new Deck(26);
    private int turns = 0;

    private Deck warDeck = new Deck(0);

    public War() {
        giveDecks();
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

    int takeTurn() {
        this.turns++;

        Card p1Card = drawCard(1);
        Card p2Card = drawCard(2);

        if (p1Card == null) {
            return 2;
        } else if (p2Card == null) {
            return 1;
        }

        return finishTurn(p1Card, p2Card);
    }

    private int finishTurn(Card p1, Card p2) {
        if (p1 == p2) {
            warDeck.addCard(p1);
            warDeck.addCard(p2);
            int result = declareWar();
            if (result < 1) return result;
        } else if (p1.getValue() > p2.getValue()) {
            p1Deck.addToBottom(p1);
            p1Deck.addToBottom(p2);
        } else {
            p2Deck.addToBottom(p1);
            p2Deck.addToBottom(p2);
        }
        return 0;
    }

    private int declareWar() {
        Card p1First = drawCard(1);
        Card p1Second = drawCard(1);
        if (p1First == null || p1Second == null) {
            winWar(2);
            return 2;
        }
        Card p2First = drawCard(2);
        Card p2Second = drawCard(2);
        if (p2First == null || p2Second == null) {
            winWar(1);
            return 1;
        }

        warDeck.addCard(p1First);
        warDeck.addCard(p1Second);
        warDeck.addCard(p2First);
        warDeck.addCard(p2Second);

        if (p1Second == p2Second) {
            declareWar();
        } else if (p1Second.getValue() > p2Second.getValue()) {
            winWar(1);
        } else if (p1Second.getValue() < p2Second.getValue()){
            winWar(2);
        }
        return 0;
    }

    private void winWar(int player) {
        if (player == 1) {
            while (warDeck.getSize() > 0) {
                p1Deck.addToBottom(warDeck.drawCard());
            }
        } else {
            while (warDeck.getSize() > 0) {
                p2Deck.addToBottom(warDeck.drawCard());
            }
        }
        warDeck = new Deck(0);
    }

    private Card drawCard(int player) {
        if (player == 1) {
            if (p1Deck.getSize() < 1) return null;
            return p1Deck.drawCard();
        }
        else {
            if (p2Deck.getSize() < 1) return null;
            return p2Deck.drawCard();
        }
    }

    public int getTurns() {
        return turns;
    }
}
