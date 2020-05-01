package com.mj147.service;

import com.mj147.common.MsgSource;
import com.mj147.domain.player.Sex;
import com.mj147.domain.player.Player;
import com.mj147.exception.CommonConflictException;
import com.mj147.repository.player.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl extends AbstractCommonService implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(MsgSource msgSource, PlayerRepository playerRepository) {
        super(msgSource);
        this.playerRepository = playerRepository;
    }

    @Override
    public Long createPlayer(String nick, Sex sex, int age) {
        if(playerRepository.existsByName(nick)) {
            throw new CommonConflictException(msgSource.ERR002);
        }
        Player player = new Player(null, nick, sex, age);
        playerRepository.save(player);

        return player.getId();
    }

    @Override
    public Player getPlayer(Long id) {
        return playerRepository.findById(id).orElseThrow(
                () -> new CommonConflictException(msgSource.ERR001));
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
