package com.mj147.service;

import com.mj147.domain.player.Sex;
import com.mj147.domain.player.Player;
import com.mj147.repository.player.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Long createPlayer(String nick, Sex sex, int age) {
        Player player = new Player(null, nick, sex, age);
        playerRepository.save(player);

        return player.getId();
    }

    @Override
    public Player getPlayer(Long id) {
        return playerRepository.getById(id);
    }

    @Override
    public Iterable<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public void removePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}
