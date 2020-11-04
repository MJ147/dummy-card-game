package com.mj147.controller.dto;

import com.mj147.controller.dto.cards.CardDto;
import com.mj147.controller.dto.cards.DeckDto;
import com.mj147.controller.dto.player.PlayerDto;
import com.mj147.domain.CardTable;
import com.mj147.domain.cards.Card;
import com.mj147.domain.cards.Deck;
import com.mj147.domain.player.Player;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CardTableDto {

    private Long id;
    private String name;
    private DeckDto deck;
    private List<PlayerDto> players = new ArrayList<>();
    private List<CardDto> cards = new ArrayList<>();

    public CardTableDto(CardTable cardTable) {
        this.id = cardTable.getId();
        this.name = cardTable.getName();
        this.deck = new DeckDto(cardTable.getDeck());
        for(Player player: cardTable.getPlayers()) {
            this.players.add(new PlayerDto(player));
        }
        for (Card card : cardTable.getCards()) {
            this.cards.add(new CardDto(card));
        }
    }
}