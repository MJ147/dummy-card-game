package com.mj147.service.player;

import com.mj147.common.MsgSource;
import com.mj147.controller.dto.player.PlayerDto;
import com.mj147.domain.CardTable;
import com.mj147.domain.cards.Card;
import com.mj147.domain.player.Player;
import com.mj147.exception.CommonBadRequestException;
import com.mj147.exception.CommonConflictException;
import com.mj147.repository.player.PlayerRepository;
import com.mj147.service.AbstractCommonService;
import com.mj147.service.CardTableService;
import com.mj147.service.cards.CardService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.mj147.common.ValidationUtils.*;

@Service
public class PlayerServiceImpl extends AbstractCommonService implements PlayerService {

    private final PlayerRepository playerRepository;
    private final CardService cardService;
    private final CardTableService cardTableService;

    public PlayerServiceImpl(MsgSource msgSource, PlayerRepository playerRepository, CardService cardService, CardTableService cardTableService) {
        super(msgSource);
        this.playerRepository = playerRepository;
        this.cardService = cardService;
        this.cardTableService = cardTableService;
    }

    @Override
    public Long createPlayer(PlayerDto playerDto) {
        validateCreatePlayerRequest(playerDto);
        Player player = new Player(null, playerDto.getName(), playerDto.getSex(), playerDto.getAge());
        playerRepository.save(player);

        return player.getId();
    }

    private void validateCreatePlayerRequest(PlayerDto playerDto) {
        if (isNullOrEmpty(playerDto.getName())
                || isNull(playerDto.getSex())
                || isNull(playerDto.getAge())) {
            throw new CommonBadRequestException(msgSource.ERR001);
        }
        if (playerRepository.existsByName(playerDto.getName())) {
            throw new CommonConflictException(msgSource.ERR003);
        }
        if (isZero(playerDto.getAge())) {
            throw new CommonConflictException((msgSource.ERR004));
        }
    }

    @Override
    public Player getPlayer(Long id) {
        checkPlayerId(id);
        return playerRepository.findById(id).get();
    }

    @Override
    public Iterable<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public void removePlayer(Long id) {
        checkPlayerId(id);
        playerRepository.deleteById(id);
    }

    private void checkPlayerId(Long id) {
        if (!playerRepository.findById(id).isPresent()) {
            throw new CommonConflictException(msgSource.ERR002);
        }
    }

    @Override
    public void updatePlayer(Player player) {
        checkPlayerId(player.getId());
        playerRepository.save(player);
    }

    @Transactional
    @Override
    public void playCard(Long cardId, Long playerId, Long cardTableId) {
        Player player = getPlayer(playerId);
        Card card = cardService.getCard(cardId);
        CardTable cardTable = cardTableService.getCardTable(cardTableId);
        removeCardFromPlayer(player, card);
        addCardToCardTable(cardTable, card);
    }

    private void removeCardFromPlayer(Player player, Card card){
        List<Card> tempCards = player.getCards();
        tempCards.remove(card);
        player.setCards(tempCards);
        updatePlayer(player);
    }

    private void addCardToCardTable(CardTable cardTable, Card card){
        List<Card> tempCards = cardTable.getCards();
        tempCards.add(card);
        cardTable.setCards(tempCards);
        cardTableService.updateCardTable(cardTable);
    }
}
