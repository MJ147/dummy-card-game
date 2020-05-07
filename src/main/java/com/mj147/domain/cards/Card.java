package com.mj147.domain.cards;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Rank rank;
    @Enumerated(EnumType.STRING)
    private Suit suit;

    public Card() {
    }

    public Card(Long id, Rank rank, Suit suit) {
        this.id = id;
        this.rank = rank;
        this.suit = suit;
    }
}
