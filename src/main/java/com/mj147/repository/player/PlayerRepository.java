package com.mj147.repository.player;

import com.mj147.domain.player.Player;
import com.mj147.exception.EntityDoesNotExistException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    default Player getById(Long playerId) {
        return findById(playerId).orElseThrow(
                () -> new EntityDoesNotExistException("Player id: " + playerId + " not found"));
    }
}