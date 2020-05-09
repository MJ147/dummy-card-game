package com.mj147.controller.dto;

import com.mj147.domain.CardTable;
import com.mj147.domain.cards.Deck;
import com.mj147.domain.player.Player;
import lombok.Data;

import java.util.List;

@Data
public class CardTableDto {

    private Long id;
    private String name;
    private Deck deck;
    private List<Player> players;

    public CardTableDto(CardTable cardTable) {
        this.id = cardTable.getId();
        this.name = cardTable.getName();
        this.deck = cardTable.getDeck();
        this.players = cardTable.getPlayers();
    }
}
