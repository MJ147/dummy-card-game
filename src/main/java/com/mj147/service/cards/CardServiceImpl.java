package com.mj147.service.cards;

import com.mj147.common.MsgSource;
import com.mj147.domain.cards.Card;
import com.mj147.domain.cards.Rank;
import com.mj147.domain.cards.Suit;
import com.mj147.exception.CommonConflictException;
import com.mj147.repository.cards.CardRepository;
import com.mj147.service.AbstractCommonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Service
public class CardServiceImpl extends AbstractCommonService implements CardService {

    private final CardRepository cardRepository;

    public CardServiceImpl(MsgSource msgSource, CardRepository cardRepository) {
        super(msgSource);
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> createCardsForFullDeck() {
        List<Card> cards = new ArrayList<>();
        for (Rank rank : EnumSet.allOf(Rank.class)) {
            for (Suit suit : EnumSet.allOf(Suit.class)) {
                cards.add(new Card(null, rank, suit));
            }
        }
        cardRepository.saveAll(cards);
        return cards;
    }

    @Override
    public Card getCard(Long id) {
        checkCardId(id);
        return cardRepository.findById(id).get();
    }

    private void checkCardId(Long id) {
        if (!cardRepository.findById(id).isPresent()) {
            throw new CommonConflictException(msgSource.ERR002);
        }
    }
}
