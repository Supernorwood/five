package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ExpenseStreamService {

    private final List<ExpenseStream> expenseStreams = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public List<ExpenseStream> getAllExpenseStreams() {
        return new ArrayList<>(expenseStreams);
    }

    public Optional<ExpenseStream> getExpenseStreamById(Long id) {
        return expenseStreams.stream().filter(stream -> stream.getId() == id).findFirst();
    }

    public ExpenseStream createExpenseStream(ExpenseStream expenseStream) {
        expenseStream.setId(counter.incrementAndGet());
        expenseStream.setInsertDate(new Date());
        expenseStream.setUpdateDate(new Date());
        expenseStreams.add(expenseStream);
        return expenseStream;
    }

    public Optional<ExpenseStream> updateExpenseStream(Long id, ExpenseStream expenseStreamDetails) {
        return getExpenseStreamById(id).map(expenseStream -> {
            expenseStream.setAmount(expenseStreamDetails.getAmount());
            expenseStream.setCategory(expenseStreamDetails.getCategory());
            expenseStream.setDescription(expenseStreamDetails.getDescription());
            expenseStream.setUpdateDate(new Date());
            expenseStream.setBudgetId(expenseStreamDetails.getBudgetId());
            return expenseStream;
        });
    }

    public boolean deleteExpenseStream(Long id) {
        return expenseStreams.removeIf(stream -> stream.getId() == id);
    }

    public List<ExpenseStream> getExpenseStreamsByMinAmount(double minAmount) {
        return expenseStreams.stream()
                .filter(stream -> stream.getAmount() >= minAmount)
                .collect(Collectors.toList());
    }

    public List<ExpenseStream> getExpenseStreamsByCategory(String category) {
        return expenseStreams.stream()
                .filter(stream -> stream.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public double getTotalExpenses() {
        return expenseStreams.stream().mapToDouble(ExpenseStream::getAmount).sum();
    }

}
