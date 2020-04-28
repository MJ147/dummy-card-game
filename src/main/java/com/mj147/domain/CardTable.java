package com.mj147.domain;

import com.mj147.domain.cards.Deck;
import com.mj147.domain.player.Player;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "card_table")
public class CardTable {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(mappedBy = "cardTable", cascade = CascadeType.ALL)
    private Deck deck;
    @OneToMany(mappedBy = "cardTable")
    private List<Player> players;

    public List<Player> getPlayers() {
        if (players == null) {
            players = new ArrayList<>();
        }
        return players;
    }
}
