package com.mj147.service;

import com.mj147.domain.CardTable;
import com.mj147.domain.player.Player;

public interface CardTableService {
    Long createTable(String name, Long playerId);

    CardTable getCardTable(Long id);

    Iterable<CardTable> getAllCardTables();

    void removeCardTable(Long id);

    void updateCardTable(CardTable card);

    Player addPlayer(Long cardTableId, Long playerId);
}