package org.example;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BudgetService {

    private final List<Budget> budgets = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public List<Budget> getAllBudgets() {
        return new ArrayList<>(budgets);
    }

    public Optional<Budget> getBudgetById(Long id) {
        return budgets.stream().filter(budget -> budget.getId() == id).findFirst();
    }

    public Budget createBudget(Budget budget) {
        budget.setId(counter.incrementAndGet());
        budget.setInsertDate(new Date());
        budget.setUpdateDate(new Date());
        budgets.add(budget);
        return budget;
    }

    public Optional<Budget> updateBudget(Long id, Budget budgetDetails) {
        return getBudgetById(id).map(budget -> {
            budget.setAllocation(budgetDetails.getAllocation());
            budget.setCategory(budgetDetails.getCategory());
            budget.setDescription(budgetDetails.getDescription());
            budget.setIncomeStreams(budgetDetails.getIncomeStreams());
            budget.setExpenseStreams(budgetDetails.getExpenseStreams());
            budget.setUpdateDate(new Date());
            return budget;
        });
    }

    public boolean deleteBudget(Long id) {
        return budgets.removeIf(budget -> budget.getId() == id);
    }
}
