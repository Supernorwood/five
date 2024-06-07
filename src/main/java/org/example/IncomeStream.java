package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class IncomeStream {

    private long id;
    private Date insertDate;
    private Date updateDate;
    private double estimatedEarningsPerYear;
    private String source;
    private String name;
    private String description;
    private Long categoryId;

    public IncomeStream() {
    }

    public IncomeStream(double estimatedEarningsPerYear, String source, String name, String description) {
        this.estimatedEarningsPerYear = estimatedEarningsPerYear;
        this.source = source;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "IncomeStream{" +
                "id=" + id +
                ", insertDate=" + insertDate +
                ", updateDate=" + updateDate +
                ", estimatedEarningsPerYear=" + estimatedEarningsPerYear +
                ", source='" + source + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
