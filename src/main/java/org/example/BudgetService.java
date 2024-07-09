package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

    private final List<Budget> budgets = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private ExpenseStreamService expenseStreamService;

    @Autowired
    private IncomeStreamService incomeStreamService;

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

    public Optional<Budget> addIncomeStreamToBudget(Long budgetId, Long incomeStreamId) {
        Optional<Budget> budget = getBudgetById(budgetId);
        Optional<IncomeStream> incomeStream = incomeStreamService.getIncomeStreamById(incomeStreamId);

        if (budget.isPresent() && incomeStream.isPresent()) {
            budget.get().getIncomeStreams().add(incomeStream.get());
            return Optional.of(updateBudget(budgetId, budget.get()).get());
        } else {
            return Optional.empty();
        }
    }

    public Optional<Budget> addExpenseStreamToBudget(Long budgetId, Long expenseStreamId) {
        Optional<Budget> budget = getBudgetById(budgetId);
        Optional<ExpenseStream> expenseStream = expenseStreamService.getExpenseStreamById(expenseStreamId);

        if (budget.isPresent() && expenseStream.isPresent()) {
            budget.get().getExpenseStreams().add(expenseStream.get());
            return Optional.of(updateBudget(budgetId, budget.get()).get());
        } else {
            return Optional.empty();
        }
    }

    public List<IncomeStream> getIncomeStreamsByBudget(Long budgetId) {
        return getBudgetById(budgetId).map(Budget::getIncomeStreams).orElse(new ArrayList<>());
    }

    public List<ExpenseStream> getExpenseStreamsByBudget(Long budgetId) {
        return getBudgetById(budgetId).map(Budget::getExpenseStreams).orElse(new ArrayList<>());
    }

    public double getTotalAllocationByBudget(Long budgetId) {
        return getBudgetById(budgetId).map(budget -> budget.getIncomeStreams().stream()
                .mapToDouble(IncomeStream::getEstimatedEarningsPerYear).sum() +
                budget.getExpenseStreams().stream().mapToDouble(ExpenseStream::getAmount).sum()).orElse(0.0);
    }
}
