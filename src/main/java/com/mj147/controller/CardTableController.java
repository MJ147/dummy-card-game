package com.mj147.controller;

import com.mj147.controller.dto.CardTableDto;
import com.mj147.controller.dto.player.PlayerDto;
import com.mj147.domain.player.Player;
import com.mj147.service.CardTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/table")
public class CardTableController {

    @Autowired
    CardTableService cardTableService;
    final static String corsUrl = "http://localhost:4200";

    @CrossOrigin(origins = corsUrl)
    @PostMapping("/")
    public ResponseEntity<Long> createCardTableWithPlayer(@RequestParam String tableName, @RequestParam Long playerId) {
        Long cardTableId = cardTableService.createTable(tableName, playerId);
        return ResponseEntity.ok(cardTableId);
    }

    @CrossOrigin(origins = corsUrl)
    @GetMapping("/{id}")
    public ResponseEntity<CardTableDto> getCardTable(@PathVariable Long id) {
        return ResponseEntity.ok(new CardTableDto(cardTableService.getCardTable(id)));
    }
    @CrossOrigin(origins = corsUrl)
    @GetMapping("/all")
    public ResponseEntity<List<CardTableDto>> getAllCardTables() {
        List<CardTableDto> cardPlayerDtoList = StreamSupport.stream(cardTableService.getAllCardTables().spliterator(), false)
                .map(p -> new CardTableDto(p))
                .collect(Collectors.toList());

        return ResponseEntity.ok(cardPlayerDtoList);
    }
    @CrossOrigin(origins = corsUrl)
    @DeleteMapping("/{id}")
    public HttpStatus removeCardTable(@PathVariable("id") Long id) {
        cardTableService.removeCardTable(id);

        return HttpStatus.OK;
    }
    @CrossOrigin(origins = corsUrl)
    @PatchMapping("/add-player")
    public ResponseEntity<PlayerDto> addPlayer(@RequestParam Long tableId, @RequestParam Long playerId){
        PlayerDto playerDto = new PlayerDto(cardTableService.addPlayer(tableId, playerId));

        return  ResponseEntity.ok(playerDto);
    }

}

