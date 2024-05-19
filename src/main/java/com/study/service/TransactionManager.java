package com.study.service;

import com.study.domain.Expense;
import com.study.domain.Income;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface TransactionManager {
    void addExpense(Expense expense);

    void addIncome(Income income);

    List<Income> getIncomes() throws IOException;

    List<Expense> getExpenses() throws IOException;

    double calculateTotalExpenses();

    double calculateTotalIncomes();

    double calculateBalance();
}
