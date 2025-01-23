package Reposistory;

import Model.Expense;

import java.util.List;

public interface ExpenseRepository {
    void addExpense(Expense expense);
    boolean editExpense(Integer id, String description, Double amount, String date);
    boolean deleteExpense(Integer id);
    List<Expense> getAllExpenses();
    Double getTotalExpenseForMonth(String month);
}
