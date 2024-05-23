package org.example;

import java.util.Date;

public class IncomeStream {

    // Use primitive data types for basic information like ID
    private long id;

    // Use java.util.Date for timestamps
    private Date insertDate;
    private Date updateDate;

    // Use double for monetary values
    private double estimatedEarningsPerYear;

    // Use String for text information
    private String source;
    private String name;
    private String description;

    public IncomeStream(double estimatedEarningsPerYear, String source, String name, String description) {
        this.estimatedEarningsPerYear = estimatedEarningsPerYear;
        this.source = source;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "IncomeStream{" +
                "estimatedEarningsPerYear=" + estimatedEarningsPerYear +
                ", source='" + source + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public double getEstimatedEarningsPerYear() {
        return estimatedEarningsPerYear;
    }

    public void setEstimatedEarningsPerYear(double estimatedEarningsPerYear) {
        this.estimatedEarningsPerYear = estimatedEarningsPerYear;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
