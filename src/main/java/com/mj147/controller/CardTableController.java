package com.mj147.controller;

import com.mj147.controller.dto.CardTableDto;
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

    private final CardTableService cardPlayerService;

    public CardTableController(CardTableService cardPlayerService) {
        this.cardPlayerService = cardPlayerService;
    }

    @PostMapping("/")
    public ResponseEntity<Long> createCardTable(@RequestParam String name) {
        Long cardPlayerId = cardPlayerService.createCardTable(name);
        return ResponseEntity.ok(cardPlayerId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardTableDto> getCardTable(@PathVariable Long id) {
        return ResponseEntity.ok(new CardTableDto(cardPlayerService.getCardTable(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CardTableDto>> getAllCardTables() {
        List<CardTableDto> cardPlayerDtoList = StreamSupport.stream(cardPlayerService.getAllCardTables().spliterator(), false)
                .map(p -> new CardTableDto(p))
                .collect(Collectors.toList());

        return ResponseEntity.ok(cardPlayerDtoList);
    }

    @DeleteMapping("/{id}")
    public HttpStatus removeCardTable(@PathVariable("id") Long id) {
        cardPlayerService.removeCardTable(id);

        return HttpStatus.OK;
    }

}

