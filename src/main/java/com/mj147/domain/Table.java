package com.mj147.domain;

import com.mj147.domain.cards.Deck;
import com.mj147.domain.player.Player;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Table {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(mappedBy = "table", cascade = CascadeType.ALL)
    private Deck deck;
    @OneToMany(mappedBy = "table")
    private List<Player> players;

    public List<Player> getPlayers() {
        if (players == null) {
            players = new ArrayList<>();
        }
        return players;
    }
}
