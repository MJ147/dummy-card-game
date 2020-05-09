package com.mj147.service.player;

import com.mj147.controller.dto.player.PlayerDto;
import com.mj147.domain.cards.Card;
import com.mj147.domain.player.Player;

import java.util.List;

public interface PlayerService {
    Long createPlayer(PlayerDto playerDto);

    Player getPlayer(Long id);

    Iterable<Player> getAllPlayers();

    void removePlayer(Long id);

    void updatePlayer(Player player);

    void playCard(Long cardId, Long playerId, Long tableId);
}
