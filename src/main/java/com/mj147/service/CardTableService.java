package com.mj147.service;

import com.mj147.controller.dto.CardTableDto;
import com.mj147.domain.CardTable;

public interface CardTableService {
    Long createCardTable(String name);

    CardTable getCardTable(Long id);

    Iterable<CardTable> getAllCardTables();

    void removeCardTable(Long id);

    void updateCardTable(CardTable card);
}
