package Reposistory;

import Model.Expense;

import java.util.List;

public interface ExpenseRepository {
    void addExpense(Expense expense);
    boolean editExpense(Integer id, String description, Double amount, String date,String categories);
    boolean deleteExpense(Integer id);
    List<Expense> getAllExpenses();
    void getTotalExpenseForMonth(String month);

    void viewMonthlyAndYearBasisReport(String month,String year);

    void viewCategoriesBasisReport(String categories);
    void viewAllCategories();
    void searchExpenseById(Integer id);
     void getExpenseFromLocal() ;
     void uploadFileToLocal();
}
