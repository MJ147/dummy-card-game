package com.mj147.domain;

import com.mj147.domain.cards.Card;
import com.mj147.domain.cards.Deck;
import com.mj147.domain.player.Player;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class CardTable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne(mappedBy = "cardTable", cascade = CascadeType.ALL)
    private Deck deck;
    @OneToMany(mappedBy = "cardTable")
    private List<Player> players;
    @OneToMany
    private List<Card> cards;

    public CardTable() {
    }

    public CardTable(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CardTable(Long id, String name, Deck deck) {
        this.id = id;
        this.name = name;
        this.deck = deck;
    }

    public List<Card> getCards() {
        if (cards == null) {
            cards = new ArrayList<>();
        }
        return cards;
    }
    public List<Player> getPlayers() {
        if (players == null) {
            players = new ArrayList<>();
        }
        return players;
    }

    public void setPlayer(Player player) {
        if (players == null) {
            players = new ArrayList<>();
        }
        players.add(player);
    }

}
