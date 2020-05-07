package com.mj147.controller.dto.cards;

import com.mj147.domain.cards.Rank;
import com.mj147.domain.cards.Suit;
import lombok.Data;

@Data
public class CardDto {
    private Long id;
    private final Rank rank;
    private final Suit suit;

    CardDto(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }
}
