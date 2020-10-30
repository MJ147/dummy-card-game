package com.mj147.controller;

import com.mj147.controller.dto.CardTableDto;
import com.mj147.controller.dto.player.PlayerDto;
import com.mj147.domain.player.Player;
import com.mj147.service.CardTableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/table")
public class CardTableController {

    private final CardTableService cardTableService;

    public CardTableController(CardTableService cardTableService) {
        this.cardTableService = cardTableService;
    }

    @PostMapping("/")
    public ResponseEntity<CardTableDto> createCardTable(@RequestParam String name) {
        CardTableDto cardTableDto = new CardTableDto(cardTableService.createCardTable(name));
        return ResponseEntity.ok(cardTableDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardTableDto> getCardTable(@PathVariable Long id) {
        return ResponseEntity.ok(new CardTableDto(cardTableService.getCardTable(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CardTableDto>> getAllCardTables() {
        List<CardTableDto> cardPlayerDtoList = StreamSupport.stream(cardTableService.getAllCardTables().spliterator(), false)
                .map(p -> new CardTableDto(p))
                .collect(Collectors.toList());

        return ResponseEntity.ok(cardPlayerDtoList);
    }

    @DeleteMapping("/{id}")
    public HttpStatus removeCardTable(@PathVariable("id") Long id) {
        cardTableService.removeCardTable(id);

        return HttpStatus.OK;
    }

    @PutMapping("/add-player/")
    public ResponseEntity<PlayerDto> addPlayer(@RequestParam Long tableId, @RequestBody Player player){
        PlayerDto playerDto = new PlayerDto(cardTableService.addPlayer(tableId, player));

        return  ResponseEntity.ok(playerDto);
    }

}

