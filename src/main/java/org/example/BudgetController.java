package org.example;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private IncomeStreamService incomeStreamService;

    @Autowired
    private ExpenseStreamService expenseStreamService;

    @GetMapping
    public List<Budget> getAllBudgets() {
        return budgetService.getAllBudgets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Long id) {
        Optional<Budget> budget = budgetService.getBudgetById(id);
        return budget.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Budget createBudget(@RequestBody Budget budget) {
        return budgetService.createBudget(budget);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget budgetDetails) {
        Optional<Budget> updatedBudget = budgetService.updateBudget(id, budgetDetails);
        return updatedBudget.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        boolean isDeleted = budgetService.deleteBudget(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{budgetId}/income-streams/{incomeStreamId}")
    public ResponseEntity<Budget> addIncomeStreamToBudget(@PathVariable Long budgetId,
            @PathVariable Long incomeStreamId) {
        Optional<Budget> budget = budgetService.getBudgetById(budgetId);
        Optional<IncomeStream> incomeStream = incomeStreamService.getIncomeStreamById(incomeStreamId);

        if (budget.isPresent() && incomeStream.isPresent()) {
            budget.get().getIncomeStreams().add(incomeStream.get());
            budgetService.updateBudget(budgetId, budget.get());
            return ResponseEntity.ok(budget.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{budgetId}/expense-streams/{expenseStreamId}")
    public ResponseEntity<Budget> addExpenseStreamToBudget(@PathVariable Long budgetId,
            @PathVariable Long expenseStreamId) {
        Optional<Budget> budget = budgetService.getBudgetById(budgetId);
        Optional<ExpenseStream> expenseStream = expenseStreamService.getExpenseStreamById(expenseStreamId);

        if (budget.isPresent() && expenseStream.isPresent()) {
            budget.get().getExpenseStreams().add(expenseStream.get());
            budgetService.updateBudget(budgetId, budget.get());
            return ResponseEntity.ok(budget.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{budgetId}/income-streams")
    public ResponseEntity<List<IncomeStream>> getIncomeStreamsByBudget(@PathVariable Long budgetId) {
        Optional<Budget> budget = budgetService.getBudgetById(budgetId);
        if (budget.isPresent()) {
            return ResponseEntity.ok(budget.get().getIncomeStreams());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{budgetId}/expense-streams")
    public ResponseEntity<List<ExpenseStream>> getExpenseStreamsByBudget(@PathVariable Long budgetId) {
        Optional<Budget> budget = budgetService.getBudgetById(budgetId);
        if (budget.isPresent()) {
            return ResponseEntity.ok(budget.get().getExpenseStreams());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{budgetId}/total-allocation")
    public Double getTotalAllocationByBudget(@PathVariable Long budgetId) {
        Optional<Budget> budget = budgetService.getBudgetById(budgetId);
        if (budget.isPresent()) {
            double totalAllocation = budget.get().getIncomeStreams().stream()
                    .mapToDouble(IncomeStream::getEstimatedEarningsPerYear).sum()
                    + budget.get().getExpenseStreams().stream()
                            .mapToDouble(ExpenseStream::getAmount).sum();
            return totalAllocation;
        } else {

            return (double) 0;
        }
    }
}
