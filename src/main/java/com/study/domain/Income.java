package com.study.domain;

import java.time.LocalDate;
import java.util.Objects;

//Date,Source,Amount

public class Income {
    private LocalDate date = LocalDate.now();
    private String source;
    private double amount;

    public Income(){
    }

    public Income(String source, double amount){
        this.setSource(source);
        this.setAmount(amount);
    }

    public Income(LocalDate date, String source, double amount){
        this.setSource(source);
        this.setAmount(amount);
        this.setDate(date);
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        if (source == null){
            throw new NullPointerException();
        }
        if (source.length() > 10){
            throw new IllegalArgumentException();
        }
        this.source = source;
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
        return "Income{" +
                "date=" + date +
                ", source='" + source +
                ", amount=" + amount +
                '}' + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Income income = (Income) o;
        return Double.compare(amount, income.amount) == 0 && Objects.equals(date, income.date) && Objects.equals(source, income.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, source, amount);
    }
}
