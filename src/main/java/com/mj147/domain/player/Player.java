package com.mj147.domain.player;

import com.mj147.domain.Table;

import javax.persistence.*;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long id;
    private String nick;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private Table table;
    @OneToOne(mappedBy = "player")
    private Hand hand;

}
