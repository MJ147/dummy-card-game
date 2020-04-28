package com.mj147.service;

import com.mj147.domain.player.Player;
import com.mj147.domain.player.Sex;

public interface PlayerService {
    Long createPlayer(String name, Sex sex, int age);

    Player getPlayer(Long id);

    Iterable<Player> getAllPlayers();

    void removePlayer(Long id);

}
