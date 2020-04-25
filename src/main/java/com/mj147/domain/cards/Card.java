package com.mj147.domain.cards;

import com.mj147.domain.player.Hand;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Card {

    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private final Rank rank;
    @Enumerated(EnumType.STRING)
    private final Suit suit;
    private Deck deck;
    private Hand hand;

    Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

}
