package com.mj147.repository.player;

import com.mj147.domain.player.Hand;
import com.mj147.exception.EntityDoesNotExistException;
import org.springframework.data.repository.CrudRepository;

public interface HandRepository extends CrudRepository<Hand, Long> {

    default Hand getById(Long handId) {
        return findById(handId).orElseThrow(
                () -> new EntityDoesNotExistException("Hand id: " + handId + " not found"));
    }

}