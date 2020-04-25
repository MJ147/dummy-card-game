package com.mj147.domain.cards;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Card {

    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private final Rank rank;
    @Enumerated(EnumType.STRING)
    private final Suit suit;
    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;

    Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank &&
                suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    @Override
    public String toString() {
        return rank.getSymbol() + " of " + suit;
    }

}
