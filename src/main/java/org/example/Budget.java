package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Budget {

    private long id;
    private Date insertDate;
    private Date updateDate;
    private double allocation;
    private String category;
    private String description;
    private List<IncomeStream> incomeStreams = new ArrayList<>();
    private List<ExpenseStream> expenseStreams = new ArrayList<>();

    public Budget() {
    }

    public Budget(double allocation, String category, String description) {
        this.allocation = allocation;
        this.category = category;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "id=" + id +
                ", insertDate=" + insertDate +
                ", updateDate=" + updateDate +
                ", allocation=" + allocation +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", incomeStreams=" + incomeStreams +
                ", expenseStreams=" + expenseStreams +
                '}';
    }
}
