package com.mj147.repository.player;

import com.mj147.domain.player.Hand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandRepository extends CrudRepository<Hand, Long> {

}