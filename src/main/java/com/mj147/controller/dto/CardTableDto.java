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
    private List<PlayerDto> players;
    private List<CardDto> cards;

    public CardTableDto(CardTable cardTable) {
        this.id = cardTable.getId();
        this.name = cardTable.getName();
        this.deck = createDeckDto(cardTable.getDeck());
        this.players = createPlayerDtoList(cardTable.getPlayers());
        this.cards = createCardDtoList(cardTable.getCards());
    }

    private DeckDto createDeckDto(Deck deck){
        if (deck != null) {
            return new DeckDto(deck);
        }
        return null;
    }

    private List<PlayerDto> createPlayerDtoList(List<Player> players){
        List<PlayerDto> playerDtoList = new ArrayList<>();
        for (Player player : players) {
            PlayerDto playerDto = new PlayerDto(player);
            playerDtoList.add(playerDto);
        }
        return playerDtoList;
    }

    private List<CardDto> createCardDtoList(List<Card> cards){
        List<CardDto> cardDtoList = new ArrayList<>();
        for (Card card : cards) {
            CardDto cardDto = new CardDto(card);
            cardDtoList.add(cardDto);
        }
        return cardDtoList;
    }
}
