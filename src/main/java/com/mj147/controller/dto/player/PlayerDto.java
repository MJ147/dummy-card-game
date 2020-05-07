package com.mj147.controller.dto.player;

import com.mj147.domain.cards.Card;
import com.mj147.domain.player.Player;
import com.mj147.domain.player.Sex;
import lombok.Data;

import java.util.List;

@Data
public class PlayerDto {
    private Long id;
    private String name;
    private Sex sex;
    private Integer age;
    private List<Card> cards;

    public PlayerDto() {
    }

    public PlayerDto(Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.sex = player.getSex();
        this.age = player.getAge();
        this.cards = player.getCards();
    }
}