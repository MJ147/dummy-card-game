package com.mj147.repository.player;

import com.mj147.domain.player.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    boolean existsByName(String name);

}