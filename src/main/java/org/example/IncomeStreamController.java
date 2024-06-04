package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/income-streams")
public class IncomeStreamController {

    @Autowired
    private IncomeStreamService service;

    @GetMapping
    public List<IncomeStream> getAllIncomeStreams() {
        return service.getAllIncomeStreams();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomeStream> getIncomeStreamById(@PathVariable Long id) {
        Optional<IncomeStream> incomeStream = service.getIncomeStreamById(id);
        return incomeStream.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/filter")
    public List<IncomeStream> getIncomeStreamsByMinEarnings(@RequestParam double minEarnings) {
        return service.getIncomeStreamsByMinEarnings(minEarnings);
    }

    @PostMapping
    public IncomeStream createIncomeStream(@RequestBody IncomeStream incomeStream) {
        return service.createIncomeStream(incomeStream);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomeStream> updateIncomeStream(@PathVariable Long id,
            @RequestBody IncomeStream incomeStreamDetails) {
        Optional<IncomeStream> updatedIncomeStream = service.updateIncomeStream(id, incomeStreamDetails);
        return updatedIncomeStream.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncomeStream(@PathVariable Long id) {
        boolean isDeleted = service.deleteIncomeStream(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}