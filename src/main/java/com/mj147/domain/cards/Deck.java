package com.mj147.domain.cards;

import javax.persistence.*;
import java.security.SecureRandom;
import java.util.*;

@Entity
public class Deck {

    @Id
    @GeneratedValue
    private Long id;
    private static final int CARDS_IN_FULL_DECK = 52;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Card> cards;

    public Deck() {
        cards = createFreshDeck();
    }

    private List<Card> createFreshDeck() {
        List<Card> freshDeck = new ArrayList<>(CARDS_IN_FULL_DECK);
        for (Rank rank : EnumSet.allOf(Rank.class)) {
            for (Suit suit : EnumSet.allOf(Suit.class)) {
                freshDeck.add(new Card(rank, suit));
            }
        }
        return freshDeck;
    }

    public void shuffle() {
        Collections.shuffle(cards, new SecureRandom());
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    int getSize() {
        return cards.size();
    }

    public void printDeck() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }
}
