package com.mj147.controller.dto.player;

import com.mj147.controller.dto.cards.CardDto;
import com.mj147.domain.cards.Card;
import com.mj147.domain.player.Player;
import com.mj147.domain.player.Sex;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PlayerDto {
    private Long id;
    private String name;
    private Sex sex;
    private Integer age;
    private List<CardDto> cards = new ArrayList<>();

    public PlayerDto(Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.sex = player.getSex();
        this.age = player.getAge();
        for (Card card : player.getCards()) {
            this.cards.add(new CardDto(card));
        }
    }
}