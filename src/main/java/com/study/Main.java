package com.study;


import com.study.domain.Expense;
import com.study.domain.Income;
import com.study.service.TransactionManager;
import com.study.service.impl.TransactionManagerCsvImpl;
import com.study.service.impl.TransactionManagerInMemoryImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.*;

/**
 *Ось загальна структура ідеї для "Системи управління фінансами" у Java:
 *
 * Клас Expense:
 * Цей клас представлятиме витрати користувача. У нього будуть наступні поля:
 *
 * date: Дата витрати.
 * description: Опис витрати.
 * amount: Сума витрати.
 * Клас Income:
 * Цей клас представлятиме доходи користувача. Він буде містити наступні поля:
 *
 * date: Дата отримання доходу.
 * source: Джерело доходу.
 * amount: Сума доходу.
 * Клас TransactionManager:
 * Цей клас буде відповідати за управління транзакціями (витратами та доходами). У ньому будуть наступні методи:
 *
 * addExpense(Expense expense): Додає нову витрату.
 * addIncome(Income income): Додає новий дохід.
 * getExpenses(): Повертає список усіх витрат.
 * getIncomes(): Повертає список усіх доходів.
 * calculateTotalExpenses(): Обчислює загальну суму витрат.
 * calculateTotalIncomes(): Обчислює загальну суму доходів.
 * calculateBalance(): Обчислює баланс (різницю між загальними доходами та витратами).
 * Клас FinanceManagerApplication:
 * Цей клас буде представляти використання системи управління фінансами.
 * Він може містити метод main для запуску програми, або може бути інтерфейсом користувача,
 * де користувач може вводити свої витрати та доходи, а потім переглядати звіти про свої фінанси.
 *
 * Це базова структура. Ти можеш розширити цей проект, додавши додаткові функції, такі як збереження даних у базі даних,
 * аналітичні звіти, нагадування про платежі тощо. Також можеш використати бібліотеки для роботи з датами та часом,
 * такі як java.time, або для взаємодії з користувачем, такі як JavaFX або Spring Boot для створення веб-додатку.
 * Насолоджуйся розробкою!
 * */


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
//        try {
//            // Створюємо FileHandler, який пише логи у файл "application.log"
//            FileHandler fileHandler = new FileHandler("application.log");
//            // Встановлюємо простий формат для логів
//            fileHandler.setFormatter(new SimpleFormatter());
//            // Додаємо FileHandler до нашого логгера
//            LOGGER.addHandler(fileHandler);
//        } catch (IOException e) {
//            LOGGER.info("Exception IOException e");
//        }

//        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(FILE_PATH_EXPENSE, true), CSVFormat.DEFAULT))

        LOGGER.info("Starting application");

//        TransactionManager csvTransactionManager = new TransactionManagerCsvImpl();
        TransactionManager inMemoryTransactionManager = new TransactionManagerInMemoryImpl();



        Main main = new Main();

        //List<Expense> hh = csvTransactionManager.getExpenses();


//        try {
//            main.save(csvTransactionManager);
//        } catch (NullPointerException e) {
//            LOGGER.warning(e.getMessage());
//        }

        try {
            main.save(inMemoryTransactionManager);
        } catch (NullPointerException e) {
            LOGGER.warning(e.getMessage());
        }

        LOGGER.info("Application finished");
    }

    private boolean save(TransactionManager transactionManager) throws IOException {
        Expense expense1 = new Expense(LocalDate.now(), "На покупки", 1200);
          Income income1 = new Income(LocalDate.now(), "salary",15000);
        Expense expense2 = new Expense("На покупки", 15200);
          Income income2 = new Income("salary",15000);
        Expense expense3 = new Expense("На поесть", 31200);

//        LOGGER.info("Save: " + expense1);
        transactionManager.addExpense(expense2);
//        LOGGER.info("Save: " + expense2);
        transactionManager.addExpense(expense3);
//        LOGGER.info("Save: " + expense3);
        transactionManager.addIncome(income1);
//        LOGGER.info("Save: " + income1);
        transactionManager.addIncome(income2);
//        LOGGER.info("Save: " + income2);



        TransactionManager csvTransactionManager = new TransactionManagerCsvImpl();
//        List<Expense> hh = csvTransactionManager.getExpenses();
//        List<Income> ss = csvTransactionManager.getIncomes();

        LOGGER.info("Save: " + csvTransactionManager.calculateTotalExpenses());
        LOGGER.info("Save: " + csvTransactionManager.calculateTotalIncomes());
        LOGGER.info("Save: " + csvTransactionManager.calculateBalance());


        TransactionManager inMemoryTransactionManager = new TransactionManagerInMemoryImpl();

        TransactionManagerCsvImpl transactionManagerCsvImpl = new TransactionManagerCsvImpl();
        transactionManagerCsvImpl.creatingHeaders();

        List<Expense> hhh = new ArrayList<>();
        List<Income> sss = new ArrayList<>();

//        inMemoryTransactionManager.addExpense(expense1);
//        inMemoryTransactionManager.addIncome(income1);
//        inMemoryTransactionManager.addExpense(expense2);
//        inMemoryTransactionManager.addExpense(expense3);
//        inMemoryTransactionManager.addIncome(income2);

        csvTransactionManager.addExpense(expense1);
        csvTransactionManager.addExpense(expense2);
        csvTransactionManager.addExpense(expense3);
        csvTransactionManager.addIncome(income1);
        csvTransactionManager.addIncome(income2);

        sss = inMemoryTransactionManager.getIncomes();
        hhh = inMemoryTransactionManager.getExpenses();
//
//
//        hh.forEach(System.out::println);
//        ss.forEach(System.out::println);

//        for (int i = 0; i < hh.size(); i++){
//            System.out.println(hh.get(i));
//        }

        return true;
    }
}