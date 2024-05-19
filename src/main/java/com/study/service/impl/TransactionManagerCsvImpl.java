package com.study.service.impl;

import com.study.domain.Expense;
import com.study.domain.Income;
import com.study.service.TransactionManager;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TransactionManagerCsvImpl implements TransactionManager {

    private static final Logger LOGGER = Logger.getLogger(TransactionManagerCsvImpl.class.getName());
    private static final String FILE_PATH_EXPENSE = "myExpense.csv";
    private static final String FILE_PATH_INCOME = "myIncome.csv";
//    private static int amountOfExpenses = 0;
//    private static int amountOfIncome = 0;

    public void creatingHeaders(){
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(FILE_PATH_EXPENSE, true), CSVFormat.DEFAULT)) {
            csvPrinter.printRecord(("Date" + "," + "Description" + "," + "Amount").replaceAll("\"", ""));
        } catch (IOException e) {
            LOGGER.warning("IOException");
        }

        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(FILE_PATH_INCOME, true), CSVFormat.DEFAULT)) {
                csvPrinter.printRecord(("Date" + "," + "Source" + "," + "Amount").replaceAll("\"", ""));
        } catch (IOException e) {
                LOGGER.warning("IOException");
        }
    }

    /**
     * Додає нову витрату
     */
    @Override
    public void addExpense(Expense expense) {
        LOGGER.info("Method addExpense start");

        //amountOfExpenses++;
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(FILE_PATH_EXPENSE, true), CSVFormat.DEFAULT)) {
            csvPrinter.printRecord((expense.getDate() + "," + expense.getDescription() + "," + expense.getAmount())/*.replaceAll("\"", "")*/);
        } catch (IOException e) {
            LOGGER.warning("IOException");
        }

        LOGGER.info("Method addExpense end");
    }


    /**
     * Додає новий дохід
     */
    @Override
    public void addIncome(Income income) {
        LOGGER.info("Method addIncome start");

        //amountOfIncome++;
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(FILE_PATH_INCOME, true), CSVFormat.DEFAULT)) {
            csvPrinter.printRecord(income.getDate() + "," + income.getSource() + "," + income.getAmount()/*.replaceAll("\"", "")*/);
        } catch (IOException e) {
            LOGGER.warning("Runtime Exception");
        }
        LOGGER.info("Method addIncome end");
    }

    /**
     * Повертає список усіх витрат
     */

    @Override
    public List<Expense> getExpenses() throws IOException {

        List<Expense> expenses = new ArrayList<>();
        String[] HEADERS = {"Date", "Description", "Amount"};

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        try (Reader in = new FileReader(FILE_PATH_EXPENSE);
             CSVParser parser = new CSVParser(in, csvFormat)) {

            for (CSVRecord record : parser) {
                LocalDate date = LocalDate.parse(record.get(0));
                String description = record.get(1);
                double amount = Double.parseDouble(record.get(2));

                Expense expense = new Expense(date, description, amount);
                expenses.add(expense);
            }

        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
        }

        LOGGER.info("Method getExpenses end");
        return expenses;
    }

    @Override
    public List<Income> getIncomes() {

        List<Income> incomes = new ArrayList<>();
        String[] HEADERS = {"Date", "Source", "Amount"};

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        try (Reader in = new FileReader(FILE_PATH_INCOME);
             CSVParser parser = new CSVParser(in, csvFormat)) {

            for (CSVRecord record : parser) {
                LocalDate date = LocalDate.parse(record.get(0));
                String source = record.get(1);
                double amount = Double.parseDouble(record.get(2));

                Income income = new Income(date, source, amount);
                incomes.add(income);
            }

        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
        }

        LOGGER.info("Method getIncomes end");
        return incomes;
    }

    @Override
    public double calculateTotalExpenses() {
        String HEADERS = "Amount";
        double totalExpenses = 0;

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        try (Reader in = new FileReader(FILE_PATH_EXPENSE);
             CSVParser parser = new CSVParser(in, csvFormat)) {

            for (CSVRecord record : parser) {
                totalExpenses += Double.parseDouble(record.get(2));
            }

        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
        }

        LOGGER.info("Method getExpenses end");
        return totalExpenses;
    }

    @Override
    public double calculateTotalIncomes() {
        String HEADERS = "Amount";
        double totalIncomes = 0;

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        try (Reader in = new FileReader(FILE_PATH_INCOME);
             CSVParser parser = new CSVParser(in, csvFormat)) {

            for (CSVRecord record : parser) {
                totalIncomes += Double.parseDouble(record.get(2));
            }

        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
        }

        LOGGER.info("Method getExpenses end");
        return totalIncomes;
    }

    @Override
    public double calculateBalance() {
        return calculateTotalExpenses() - calculateTotalIncomes();
    }
}


