package org.example;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseStream {

    private long id;
    private Date insertDate;
    private Date updateDate;
    private double amount;
    private String category;
    private String description;

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
                '}';
    }
}
