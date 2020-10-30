package com.mj147.controller.dto.cards;

import com.mj147.domain.cards.Card;
import com.mj147.domain.cards.Deck;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DeckDto {

    private Long id;
    private List<CardDto> cards = new ArrayList<>();

    public DeckDto(Deck deck) {
        this.id = deck.getId();
        for (Card card : deck.getCards()) {
            this.cards.add(new CardDto(card));
        }
    }
}
