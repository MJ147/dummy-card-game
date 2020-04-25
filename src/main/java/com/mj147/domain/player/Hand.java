package com.mj147.domain.player;

import com.mj147.domain.cards.Card;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hand {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;
    private int numberOfCards;
    @OneToMany(mappedBy = "hand", cascade = CascadeType.ALL)
    private List<Card> cards;

    public List<Card> getCards() {
        if (cards == null) {
            cards = new ArrayList<>();
        }
        return cards;
    }

}
