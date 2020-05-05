package com.mj147.controller.cards;

import com.mj147.controller.dto.cards.DeckDto;
import com.mj147.service.cards.DeckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/deck")
public class DeckController {

    private final DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createDeck() {
        Long deckId = deckService.createDeck();
        return ResponseEntity.ok(deckId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeckDto> getDeck(@PathVariable Long id) {
        return ResponseEntity.ok(new DeckDto(deckService.getDeck(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DeckDto>> getAllDecks() {
        List<DeckDto> deckDtoList = StreamSupport.stream(deckService.getAllDecks().spliterator(), false)
                .map(p -> new DeckDto(p))
                .collect(Collectors.toList());

        return ResponseEntity.ok(deckDtoList);
    }

    @DeleteMapping("/{id}")
    public HttpStatus removeDeck(@PathVariable("id") Long id) {
        deckService.removeDeck(id);
        return HttpStatus.OK;
    }
}

