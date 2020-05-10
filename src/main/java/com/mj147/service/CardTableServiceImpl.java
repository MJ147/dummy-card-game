package com.mj147.service;

import com.mj147.common.MsgSource;
import com.mj147.controller.dto.CardTableDto;
import com.mj147.domain.CardTable;
import com.mj147.domain.cards.Card;
import com.mj147.domain.player.Player;
import com.mj147.exception.CommonBadRequestException;
import com.mj147.exception.CommonConflictException;
import com.mj147.repository.CardTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mj147.common.ValidationUtils.*;

@Service
public class CardTableServiceImpl extends AbstractCommonService implements CardTableService {

    private final CardTableRepository cardTableRepository;


    public CardTableServiceImpl(MsgSource msgSource, CardTableRepository cardTableRepository) {
        super(msgSource);
        this.cardTableRepository = cardTableRepository;
    }

    @Override
    public Long createCardTable(String name) {
        validateCreateCardTableRequest(name);
        CardTable cardTable = new CardTable(null, name);
        cardTableRepository.save(cardTable);

        return cardTable.getId();
    }

    private void validateCreateCardTableRequest(String name) {
        if (isNullOrEmpty(name)) {
            throw new CommonBadRequestException(msgSource.ERR001);
        }
        if (cardTableRepository.existsByName(name)) {
            throw new CommonConflictException(msgSource.ERR003);
        }
    }

    @Override
    public CardTable getCardTable(Long id) {
        checkCardTableId(id);
        return cardTableRepository.findById(id).get();
    }

    @Override
    public Iterable<CardTable> getAllCardTables() {
        return cardTableRepository.findAll();
    }

    @Override
    public void removeCardTable(Long id) {
        checkCardTableId(id);
        cardTableRepository.deleteById(id);
    }

    private void checkCardTableId(Long id) {
        if (!cardTableRepository.findById(id).isPresent()) {
            throw new CommonConflictException(msgSource.ERR002);
        }
    }

    @Override
    public void updateCardTable(CardTable cardTable) {
        checkCardTableId(cardTable.getId());
        cardTableRepository.save(cardTable);
    }

    @Override
    public void addCardToTable(CardTable table, Card card){
        List<Card> tempCards = table.getCards();
        tempCards.add(card);
        table.setCards(tempCards);
        updateCardTable(table);
    }
}
