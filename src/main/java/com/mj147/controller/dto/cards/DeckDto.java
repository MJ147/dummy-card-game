package com.mj147.controller.dto.cards;

import com.mj147.domain.cards.Card;
import com.mj147.domain.cards.Deck;
import lombok.Data;

import java.util.List;

@Data
public class DeckDto {

    private Long id;
    private List<Card> cards;

    public DeckDto(Deck deck) {
        this.id = deck.getId();
        this.cards = deck.getCards();
    }
}
