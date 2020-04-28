package com.mj147.repository.cards;

import com.mj147.domain.cards.Card;
import com.mj147.exception.EntityDoesNotExistException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    default Card getById(Long cardId) {
        return findById(cardId).orElseThrow(
                () -> new EntityDoesNotExistException("Card id: " + cardId + " not found"));
    }
}