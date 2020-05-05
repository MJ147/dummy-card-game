package com.mj147.service.player;

import com.mj147.common.MsgSource;
import com.mj147.controller.dto.player.PlayerDto;
import com.mj147.domain.player.Player;
import com.mj147.exception.CommonBadRequestException;
import com.mj147.exception.CommonConflictException;
import com.mj147.repository.player.PlayerRepository;
import com.mj147.service.AbstractCommonService;
import org.springframework.stereotype.Service;

import static com.mj147.common.ValidationUtils.*;

@Service
public class PlayerServiceImpl extends AbstractCommonService implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(MsgSource msgSource, PlayerRepository playerRepository) {
        super(msgSource);
        this.playerRepository = playerRepository;
    }

    @Override
    public Long createPlayer(PlayerDto playerDto) {
        validateCreatePlayerRequest(playerDto);
        Player player = new Player(null, playerDto.getName(), playerDto.getSex(), playerDto.getAge());
        playerRepository.save(player);

        return player.getId();
    }

    private void validateCreatePlayerRequest(PlayerDto playerDto) {
        if (isNullOrEmpty(playerDto.getName())
                || isNull(playerDto.getSex())
                || isNull(playerDto.getAge())) {
            throw new CommonBadRequestException(msgSource.ERR001);
        }
        if (playerRepository.existsByName(playerDto.getName())) {
            throw new CommonConflictException(msgSource.ERR003);
        }
        if (isZero(playerDto.getAge())) {
            throw new CommonConflictException((msgSource.ERR004));
        }
    }

    @Override
    public Player getPlayer(Long id) {
        checkPlayerId(id);
        return playerRepository.findById(id).get();
    }

    @Override
    public Iterable<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public void removePlayer(Long id) {
        checkPlayerId(id);
        playerRepository.deleteById(id);
    }

    private void checkPlayerId(Long id) {
        if (!playerRepository.findById(id).isPresent()) {
            throw new CommonConflictException(msgSource.ERR002);
        }
    }
}
