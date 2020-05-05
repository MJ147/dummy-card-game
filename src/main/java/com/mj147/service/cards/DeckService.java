package com.mj147.service.cards;

import com.mj147.domain.cards.Deck;

public interface DeckService {
    Long createDeck();

    Deck getDeck(Long id);

    Iterable<Deck> getAllDecks();

    void removeDeck(Long id);

}
