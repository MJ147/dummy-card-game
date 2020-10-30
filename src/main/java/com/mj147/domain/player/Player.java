package com.mj147.domain.player;

import com.mj147.domain.CardTable;
import com.mj147.domain.cards.Card;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Player {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Sex sex;
    private Integer age;
    @ManyToOne
    @JoinColumn(name = "table_id")
    @ToString.Exclude
    private CardTable cardTable;
    @OneToMany
    private List<Card> cards;

    public List<Card> getCards() {
        if (cards == null) {
            cards = new ArrayList<>();
        }
        return cards;
    }

    public Player() {
    }

    public Player(Long id, String name, Sex sex, int age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}
