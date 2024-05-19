package com.study.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Logger;


// Date,Description,Amount

public class Expense {

    private static final  Logger LOGGER = Logger.getLogger(Expense.class.getName());

    private LocalDate date = LocalDate.now();
    private String description;
    private double amount;

    public Expense() {
    }

    public Expense(String description, double amount){
        this.setDescription(description);
        this.setAmount(amount);
    }

    public Expense(LocalDate date, String description, double amount){
        this.setDate(date);
        this.setDescription(description);
       this.setAmount(amount);
   }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
       if (description == null){
           throw new NullPointerException();
       }
       if (description.length() > 50){
           throw new IllegalArgumentException();
       }
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
       if (amount < 0){
           throw new IllegalArgumentException();
       }
       this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Expense{" +
                "date=" + date +
                ", description='" + description +
                ", amount=" + amount +
                '}' + '\n';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Double.compare(amount, expense.amount) == 0 && Objects.equals(date, expense.date) && Objects.equals(description, expense.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, description, amount);
    }
}
