package com.mj147.controller.dto.cards;

import com.mj147.domain.cards.Card;
import com.mj147.domain.cards.Rank;
import com.mj147.domain.cards.Suit;
import lombok.Data;

@Data
public class CardDto {
    private Long id;
    private Rank rank;
    private Suit suit;

    public CardDto(Card card) {
        this.id = card.getId();
        this.rank = card.getRank();
        this.suit = card.getSuit();
    }
}
