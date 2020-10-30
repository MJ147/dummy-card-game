package com.mj147.domain.cards;

import com.mj147.domain.CardTable;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private static final Integer NUMBERS_OF_CARDS_IN_FULL_DECK = 52;
    @OneToOne
    @JoinTable(name = "card_table_id")
    @ToString.Exclude
    private CardTable cardTable;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Card> cards;

    public List<Card> getCards() {
        if (cards == null) {
            cards = new ArrayList<>();
        }
        return cards;
    }

}
