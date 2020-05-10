package com.mj147.service.cards;

import com.mj147.common.MsgSource;

import com.mj147.domain.CardTable;
import com.mj147.domain.cards.Card;
import com.mj147.domain.cards.Deck;
import com.mj147.domain.player.Player;
import com.mj147.exception.CommonConflictException;
import com.mj147.repository.cards.DeckRepository;
import com.mj147.service.AbstractCommonService;
import com.mj147.service.CardTableService;
import com.mj147.service.player.PlayerService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;

@Service
public class DeckServiceImpl extends AbstractCommonService implements DeckService {

    private final DeckRepository deckRepository;
    private final CardService cardService;
    private final PlayerService playerService;
    private final CardTableService tableService;

    public DeckServiceImpl(MsgSource msgSource, DeckRepository deckRepository, CardService cardService, PlayerService playerService, CardTableService tableService) {
        super(msgSource);
        this.deckRepository = deckRepository;
        this.cardService = cardService;
        this.playerService = playerService;
        this.tableService = tableService;
    }

    @Override
    public Long createDeck() {
        Deck deck = new Deck();
        List<Card> cards = cardService.createCardsForFullDeck();
        deck.setCards(cards);
        deckRepository.save(deck);

        return deck.getId();
    }

    @Override
    public Deck getDeck(Long id) {
        checkDeckId(id);
        return deckRepository.findById(id).get();
    }

    @Override
    public Iterable<Deck> getAllDecks() {
        return deckRepository.findAll();
    }

    @Override
    public void removeDeck(Long id) {
        checkDeckId(id);
        deckRepository.deleteById(id);
    }

    private void checkDeckId(Long id) {
        if (!deckRepository.findById(id).isPresent()) {
            throw new CommonConflictException(msgSource.ERR002);
        }
    }

    @Override
    public void shuffleDeck(Long id) {
        Deck deck = getDeck(id);
        List<Card> cards = deck.getCards();
        Collections.shuffle(cards, new SecureRandom());
        deck.setCards(cards);
        deckRepository.save(deck);
    }

    @Transactional
    @Override
    public void dealCard(Long deckId, Long cardId, Long playerId) {
        Deck deck = getDeck(deckId);
        Card card = cardService.getCard(cardId);
        Player player = playerService.getPlayer(playerId);
        removeCardFromDeck(deck, card);
        playerService.addCardToPlayer(player, card);
    }

    private void removeCardFromDeck(Deck deck, Card card){
        List<Card> tempCards = deck.getCards();
        tempCards.remove(card);
        deck.setCards(tempCards);
        updateDeck(deck);
    }

    @Override
    public void addToTable(Long deckId, Long tableId) {
        CardTable table = tableService.getCardTable(tableId);
        Deck deck = getDeck(deckId);
        deck.setCardTable(table);
        table.setDeck(deck);
        updateDeck(deck);
        tableService.updateCardTable(table);
    }

    @Override
    public void updateDeck(Deck deck) {
        checkDeckId(deck.getId());
        deckRepository.save(deck);
    }
}
