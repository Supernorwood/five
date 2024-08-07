package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import jakarta.annotation.Nullable;

@Getter
@Setter
public class ExpenseStream {

    private long id;
    private Date insertDate;
    private Date updateDate;
    private double amount;
    private String category;
    private String description;

    @Nullable
    private Long budgetId;

    public ExpenseStream() {
    }

    public ExpenseStream(double amount, String category, String description) {
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ExpenseStream{" +
                "id=" + id +
                ", insertDate=" + insertDate +
                ", updateDate=" + updateDate +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", budgetId=" + budgetId +
                '}';
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }
}
