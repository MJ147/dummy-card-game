package com.mj147.controller.dto.player;

import com.mj147.domain.player.Player;
import com.mj147.domain.player.Sex;
import lombok.Data;

@Data
public class PlayerDto {
    private Long id;
    private String name;
    private Sex sex;
    private Integer age;

    public PlayerDto() {
    }

    public PlayerDto(Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.sex = player.getSex();
        this.age = player.getAge();
    }
}