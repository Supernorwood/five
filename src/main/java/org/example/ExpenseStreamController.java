package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/expense-streams")
public class ExpenseStreamController {

    @Autowired
    private ExpenseStreamService service;

    @GetMapping
    public List<ExpenseStream> getAllExpenseStreams() {
        return service.getAllExpenseStreams();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseStream> getExpenseStreamById(@PathVariable Long id) {
        Optional<ExpenseStream> expenseStream = service.getExpenseStreamById(id);
        return expenseStream.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ExpenseStream createExpenseStream(@RequestBody ExpenseStream expenseStream) {
        return service.createExpenseStream(expenseStream);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseStream> updateExpenseStream(@PathVariable Long id,
            @RequestBody ExpenseStream expenseStreamDetails) {
        Optional<ExpenseStream> updatedExpenseStream = service.updateExpenseStream(id, expenseStreamDetails);
        return updatedExpenseStream.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpenseStream(@PathVariable Long id) {
        boolean isDeleted = service.deleteExpenseStream(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/filter")
    public List<ExpenseStream> getExpenseStreamsByMinAmount(@RequestParam double minAmount) {
        return service.getExpenseStreamsByMinAmount(minAmount);
    }

    @GetMapping("/category")
    public List<ExpenseStream> getExpenseStreamsByCategory(@RequestParam String category) {
        return service.getExpenseStreamsByCategory(category);
    }

    @GetMapping("/total-expenses")
    public ResponseEntity<Double> getTotalExpenses() {
        double totalExpenses = service.getTotalExpenses();
        return ResponseEntity.ok(totalExpenses);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<ExpenseStream>> getExpenseStreamsByDateRange(
            @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
        List<ExpenseStream> expenseStreams = service.getAllExpenseStreams().stream()
                .filter(stream -> stream.getInsertDate().after(startDate) && stream.getInsertDate().before(endDate))
                .collect(Collectors.toList());
        return ResponseEntity.ok(expenseStreams);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalExpenseCount() {
        long totalExpenseCount = service.getAllExpenseStreams().size();
        return ResponseEntity.ok(totalExpenseCount);
    }
}
