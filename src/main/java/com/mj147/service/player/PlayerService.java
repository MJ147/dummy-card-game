package com.mj147.service.player;

import com.mj147.domain.player.Player;

public interface PlayerService {
    Long createPlayer(Player player);

    Long updatePlayer(Player player);

    Player getPlayer(Long id);

    Iterable<Player> getAllPlayers();

    void removePlayer(Long id);

    void playCard(Long cardId, Long playerId, Long tableId);
}
