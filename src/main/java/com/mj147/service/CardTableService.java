package com.mj147.service;

import com.mj147.controller.dto.CardTableDto;
import com.mj147.domain.CardTable;
import com.mj147.domain.cards.Card;
import com.mj147.domain.player.Player;

public interface CardTableService {
    Long createCardTable(String name);

    CardTable getCardTable(Long id);

    Iterable<CardTable> getAllCardTables();

    void removeCardTable(Long id);

    void updateCardTable(CardTable card);

    void addCardToTable(CardTable table, Card card);
}
