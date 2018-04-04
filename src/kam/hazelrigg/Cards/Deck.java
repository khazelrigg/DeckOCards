package kam.hazelrigg.Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Deck implements Iterable<Card> {
    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<>(52);
        fillDeck();
    }

    public Deck(int length) {
        deck = new ArrayList<>(length);
    }

    public Deck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public void addAllCards(Card ...  c) {
        for (Card card : c) {
            addCard(card);
        }
    }

    public Card drawCard() {
        Card last = deck.get(deck.size() - 1);
        for (int i = deck.size() - 1; i > 0 ; i--) {
            deck.set(i, deck.get(i - 1));
        }
        deck.set(0, last);
        deck.remove(0);
        return last;
    }

    public Card drawCard(int e) {
        Card last = deck.get(e);
        for (int i = e; i > 0 ; i--) {
            deck.set(i, deck.get(i - 1));
        }
        deck.set(0, last);
        deck.remove(0);
        return last;
    }

    public void addToBottom(Card card) {
        deck.add(0, card);
    }

    public int getSize() {
        return deck.size();
    }

    public void shuffleCards() {
        Collections.shuffle(deck);
    }

    private void fillDeck() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (int i = 1; i < 14; i++) {
                deck.add(new Card(i, suit));
            }
        }
    }

    public ArrayList<Card> getCards() {
        return deck;
    }

    public Card peek(int e) {
        return deck.get(e);
    }

    public void empty() {
        deck = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder bob = new StringBuilder();
        for (Card card : deck) {
            bob.append(card).append(" ");
        }
        return bob.toString();
    }

    @Override
    public Iterator<Card> iterator() {
        Iterator<Card> deckIterator = new Iterator<Card>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < deck.size() && deck.get(index) != null;
            }

            @Override
            public Card next() {
                return deck.get(index++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return deckIterator;
    }
}
