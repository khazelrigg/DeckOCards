package kam.hazelrigg;

import kam.hazelrigg.Cards.Card;
import kam.hazelrigg.Cards.Deck;

import java.util.ArrayList;
import java.util.Random;

public class War {
    private Deck p1Deck = new Deck(26);
    private Deck p2Deck = new Deck(26);
    private Deck warDeck = new Deck(0);

    private Card p1turnCard;
    private Card p2turnCard;

    private Random random = new Random();
    private int turnCount = 0;

    War() {
        createPlayerDecks();
    }

    /**
     * Fills in the player decks with half of a shuffled 52 card deck
     */
    private void createPlayerDecks() {
        Deck reg52 = new Deck();
        reg52.shuffleCards();
        ArrayList<Card> splitter = reg52.getDeck();

        for (int i = 0; i < 26; i++) {
            p1Deck.addCard(splitter.get(i));
        }
        for (int i = 26; i < 52; i++) {
            p2Deck.addCard(splitter.get(i));
        }
    }

    int takeTurn() {
        this.turnCount++;
        p1turnCard = drawCard(p1Deck);
        p2turnCard = drawCard(p2Deck);

        switch (compareCards()) {
            case -1:
                battle();
                break;
            case 1:
                reward(p1Deck);
                break;
            case 2:
                reward(p2Deck);
                break;
        }

        return checkWinner();
    }

    private int compareCards() {
        if (p1turnCard == null)
            return 2;
        else if (p2turnCard == null)
            return 1;
        if (p1turnCard == p2turnCard)
            return -1;
        if (p1turnCard.getValue() > p2turnCard.getValue())
            return 1;
        return 2;
    }


    private void reward(Deck deck) {
        if (random.nextInt(10) < 6) {
            deck.addCard(p1turnCard);
            deck.addCard(p2turnCard);
        } else {
            deck.addCard(p2turnCard);
            deck.addCard(p2turnCard);
        }

        p1turnCard = null;
        p2turnCard = null;
    }

    private int checkWinner() {
        if (p1Deck.getSize() < 1) return 2;
        if (p2Deck.getSize() < 1) return 1;
        return 0;
    }


    private void battle() {
        Card p1First = drawCard(p1Deck);
        Card p1Second = drawCard(p1Deck);

        if (p1First == null || p1Second == null) {
            winBattle(p2Deck);
            return;
        }

        Card p2First = drawCard(p2Deck);
        Card p2Second = drawCard(p2Deck);
        if (p2First == null || p2Second == null) {
            winBattle(p1Deck);
            return;
        }

        warDeck.addCard(p1First);
        warDeck.addCard(p1Second);
        warDeck.addCard(p2First);
        warDeck.addCard(p2Second);

        if (p1Second == p2Second) {
            battle();
        } else if (p1Second.getValue() > p2Second.getValue()) {
            winBattle(p1Deck);
        } else {
            winBattle(p2Deck);
        }
    }

    private void winBattle(Deck deck) {
        while (warDeck.getSize() != 0) {
            deck.addToBottom(drawCard(warDeck));
        }
        warDeck = new Deck(0);
        reward(deck);
    }

    private Card drawCard(Deck deck) {
        if (deck.getSize() < 1) return null;
        return deck.drawCard();
    }

    public int getTurnCount() {
        return turnCount;
    }
}
