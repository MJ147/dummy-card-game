package com.mj147.repository.cards;

import com.mj147.domain.cards.Deck;
import com.mj147.exception.EntityDoesNotExistException;
import org.springframework.data.repository.CrudRepository;

public interface DeckRepository extends CrudRepository<Deck, Long> {

    default Deck getById(Long deckId) {
        return findById(deckId).orElseThrow(
                () -> new EntityDoesNotExistException("Deck id: " + deckId + " not found"));
    }

}