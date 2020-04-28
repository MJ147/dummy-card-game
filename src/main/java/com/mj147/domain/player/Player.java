package com.mj147.domain.player;

import com.mj147.domain.CardTable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Player {
    @Id
    @GeneratedValue
    private Long id;
    private String nick;
    private Sex sex;
    private int age;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private CardTable cardTable;
    @OneToOne(mappedBy = "player")
    private Hand hand;

    public Player() {
    }

    public Player(Long id, String nick, Sex sex, int age) {
        this.id = id;
        this.nick = nick;
        this.sex = sex;
        this.age = age;
    }
}
