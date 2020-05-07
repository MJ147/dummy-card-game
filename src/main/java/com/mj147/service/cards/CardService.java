package com.mj147.service.cards;

import com.mj147.domain.cards.Card;

import java.util.List;

public interface CardService {

    List<Card> createCardsForFullDeck();

    Card getCard(Long id);

}
