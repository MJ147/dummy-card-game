package com.mj147.service.player;

import com.mj147.controller.dto.player.PlayerDto;
import com.mj147.domain.player.Player;

public interface PlayerService {
    Long createPlayer(PlayerDto playerDto);

    Player getPlayer(Long id);

    Iterable<Player> getAllPlayers();

    void removePlayer(Long id);

}
