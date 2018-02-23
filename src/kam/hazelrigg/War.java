package kam.hazelrigg;

import kam.hazelrigg.Cards.Card;
import kam.hazelrigg.Cards.Deck;

import java.util.ArrayList;
import java.util.Random;

public class War implements CardGame {
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

    @Override
    public void play() {
        int winner = 0;
        while (winner == 0) {
            winner = takeTurn();
        }
        System.out.println("Player " + winner + " has won the war in " + turnCount + " turns!");
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

    private int takeTurn() {
        this.turnCount++;
        p1turnCard = p1Deck.drawCard();
        p2turnCard = p2Deck.drawCard();

        int i = compareCards();
        if (i == -1) {
            battle();
        } else if (i == 1) {
            reward(p1Deck);
        } else if (i == 2) {
            reward(p2Deck);
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
            deck.addToBottom(p1turnCard);
            deck.addToBottom(p2turnCard);
        } else {
            deck.addToBottom(p2turnCard);
            deck.addToBottom(p1turnCard);
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
        Card p1First = p1Deck.drawCard();
        Card p1Second = p1Deck.drawCard();

        if (p1First == null || p1Second == null) {
            winBattle(p2Deck);
            return;
        }

        Card p2First = p2Deck.drawCard();
        Card p2Second = p2Deck.drawCard();
        if (p2First == null || p2Second == null) {
            winBattle(p1Deck);
            return;
        }

        warDeck.addAllCards(p1First, p1Second, p2First, p2Second);

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
            deck.addToBottom(warDeck.drawCard());
        }
        // Reset the war deck after giving away cards
        warDeck = new Deck(0);
        reward(deck);
    }

    public int getTurns() {
        return turnCount;
    }

}
