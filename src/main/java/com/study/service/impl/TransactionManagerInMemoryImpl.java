package com.study.service.impl;

import com.study.Main;
import com.study.domain.Expense;
import com.study.domain.Income;
import com.study.service.TransactionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TransactionManagerInMemoryImpl implements TransactionManager {

    private final List<Income> incomes = new ArrayList<>();
    private final List<Expense> expenses = new ArrayList<>();

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    @Override
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    @Override
    public void addIncome(Income income) {
        incomes.add(income);
    }

    @Override
    public List<Income> getIncomes() {
        for (int i = 0; i < incomes.size(); i++){
            LOGGER.info(String.valueOf(incomes.get(i)));
        }

        return incomes;
    }

    @Override
    public List<Expense> getExpenses() {
        for (int i = 0; i < expenses.size(); i++){
            LOGGER.info(String.valueOf(expenses.get(i)));
        }

        return expenses;
    }

    @Override
    public double calculateTotalExpenses(){
        double totalExpenses = 0;
        for (int i = 0; i < expenses.size(); i++){
            totalExpenses += expenses.get(i).getAmount();
        }
        return totalExpenses;
    }

    @Override
    public double calculateTotalIncomes(){
        double totalIncome = 0;
        for (int i = 0; i < incomes.size(); i++){
            totalIncome += incomes.get(i).getAmount();
        }
        return totalIncome;
    }

    @Override
    public double calculateBalance(){
        return calculateTotalIncomes() - calculateTotalExpenses();
    }

}
