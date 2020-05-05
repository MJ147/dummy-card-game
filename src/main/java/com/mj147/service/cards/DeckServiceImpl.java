package com.mj147.service.cards;

import com.mj147.common.MsgSource;
import com.mj147.domain.cards.Deck;
import com.mj147.exception.CommonConflictException;
import com.mj147.repository.cards.DeckRepository;
import com.mj147.service.AbstractCommonService;
import org.springframework.stereotype.Service;

@Service
public class DeckServiceImpl extends AbstractCommonService implements DeckService {

    private final DeckRepository deckRepository;

    public DeckServiceImpl(MsgSource msgSource, DeckRepository deckRepository) {
        super(msgSource);
        this.deckRepository = deckRepository;
    }

    @Override
    public Long createDeck() {
        Deck deck = new Deck();
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
}
