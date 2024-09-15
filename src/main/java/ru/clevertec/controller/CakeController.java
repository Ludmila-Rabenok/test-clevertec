package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.common.CakeType;
import ru.clevertec.domain.Cake;
import ru.clevertec.exception.CakeNotFoundException;
import ru.clevertec.service.CakeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CakeController {
    private final CakeService cakeService;

    @GetMapping("/api/v1/cakes")
    public ResponseEntity<List<Cake>> findAll() {
        List<Cake> cakes = cakeService.getCakes();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cakes);
    }

    @GetMapping("/api/v1/cakes/{cakeId}")
    public ResponseEntity<Cake> findCakeById(@PathVariable UUID cakeId) {
        Cake cake = cakeService.getCakeById(cakeId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cake);
    }

    @GetMapping("/api/v1/cakes/byType/{type}")
    public ResponseEntity<Cake> findCakeByType(@PathVariable CakeType type) {
        Cake cake = cakeService.getCakeByType(type);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cake);
    }

    @PostMapping("/api/v1/cakes")
    public ResponseEntity<Cake> createNewCake(@RequestBody Cake cake) {
        Cake createdCake = cakeService.create(cake);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(createdCake);
    }

    @PutMapping("/api/v1/cakes")
    public ResponseEntity<Cake> updateCake(@RequestBody Cake cake) {
        UUID cakeId = cake.getId();
        Cake updatedCake = cakeService.update(cakeId, cake);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(updatedCake);
    }

    @DeleteMapping("/api/v1/cakes/{cakeId}")
    public ResponseEntity<String> deleteById(@PathVariable UUID cakeId) {
        cakeService.deleteById(cakeId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("Cake with id = " + cakeId + "was delete");
    }

    @ExceptionHandler(CakeNotFoundException.class)
    public ResponseEntity<String> handleException(CakeNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
