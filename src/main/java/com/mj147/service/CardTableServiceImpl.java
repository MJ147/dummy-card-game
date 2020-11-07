package com.mj147.service;

import com.mj147.common.MsgSource;
import com.mj147.domain.CardTable;
import com.mj147.domain.cards.Card;
import com.mj147.domain.cards.Deck;
import com.mj147.domain.player.Player;
import com.mj147.exception.CommonBadRequestException;
import com.mj147.exception.CommonConflictException;
import com.mj147.repository.CardTableRepository;
import com.mj147.service.cards.DeckService;
import com.mj147.service.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mj147.common.ValidationUtils.*;

@Service
public class CardTableServiceImpl extends AbstractCommonService implements CardTableService {

    @Autowired
    CardTableRepository cardTableRepository;
    @Autowired
    DeckService deckService;
    @Autowired
    PlayerService playerService;

    public CardTableServiceImpl(MsgSource msgSource) {
        super(msgSource);
    }

    @Override
    public Long createTable(String tableName, Long playerId) {
        validateCreateCardTableRequest(tableName);
        Deck deck = deckService.createDeck();
        Player player = playerService.getPlayer(playerId);
        CardTable cardTable = new CardTable(null, tableName, deck, player);
        cardTable.setPlayer(player);
        player.setCardTable(cardTable);
        deck.setCardTable(cardTable);
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
    public Player addPlayer(Long tableId, Long playerId) {
        CardTable cardTable = this.getCardTable(tableId);
        checkNumberOfPlayers(cardTable);
        Player player = playerService.getPlayer(playerId);
        cardTable.setPlayer(player);
        player.setCardTable(cardTable);
        updateCardTable(cardTable);
        System.out.println(cardTable);
        return player;
    }

    private void checkNumberOfPlayers(CardTable cardTable) {
        if(cardTable.getPlayers().size() >= 4) {
            throw new CommonConflictException(msgSource.ERR006);
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
}
