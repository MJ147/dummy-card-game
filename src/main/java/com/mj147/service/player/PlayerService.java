package com.mj147.service.player;

import com.mj147.domain.player.Player;

public interface PlayerService {
    Player createPlayer(Player player);

    Player getPlayer(Long id);

    Iterable<Player> getAllPlayers();

    void removePlayer(Long id);

    void updatePlayer(Player player);

    void playCard(Long cardId, Long playerId, Long tableId);
}
