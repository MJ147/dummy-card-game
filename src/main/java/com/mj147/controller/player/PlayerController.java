package com.mj147.controller.player;

import com.mj147.controller.dto.player.PlayerDto;
import com.mj147.service.player.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/")
    public ResponseEntity<Long> createPlayer(@RequestBody PlayerDto playerDto) {
        Long playerId = playerService.createPlayer(playerDto);
        return ResponseEntity.ok(playerId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getPlayer(@PathVariable Long id) {
        return ResponseEntity.ok(new PlayerDto(playerService.getPlayer(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        List<PlayerDto> playerDtoList = StreamSupport.stream(playerService.getAllPlayers().spliterator(), false)
                .map(p -> new PlayerDto(p))
                .collect(Collectors.toList());

        return ResponseEntity.ok(playerDtoList);
    }

    @DeleteMapping("/{id}")
    public HttpStatus removePlayer(@PathVariable("id") Long id) {
        playerService.removePlayer(id);
        return HttpStatus.OK;
    }
}

