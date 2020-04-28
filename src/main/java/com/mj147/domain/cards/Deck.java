package com.mj147.domain.cards;

import com.mj147.domain.CardTable;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class Deck {
    @Id
    @GeneratedValue
    private Long id;
    private static final int NUMBERS_OF_CARDS_IN_FULL_DECK = 52;
    @OneToOne
    @JoinTable(name = "card_table_id")
    private CardTable cardTable;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Card> cards;

    public Deck() {
        cards = createFreshDeck();
    }

    public List<Card> getCards() {
        if (cards == null) {
            cards = new ArrayList<>();
        }
        return cards;
    }

    private List<Card> createFreshDeck() {
        List<Card> freshDeck = new ArrayList<>(NUMBERS_OF_CARDS_IN_FULL_DECK);
        for (Rank rank : EnumSet.allOf(Rank.class)) {
            for (Suit suit : EnumSet.allOf(Suit.class)) {
                freshDeck.add(new Card(null, rank, suit));
            }
        }
        return freshDeck;
    }



}
