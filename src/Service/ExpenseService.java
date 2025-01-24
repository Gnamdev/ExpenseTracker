package Service;

import Model.Expense;

import java.util.List;

public interface ExpenseService {

    void addExpense(Double amount , String description , String date  ,String categories );
    void editExpense(Integer id , Double amount , String description , String date,String categories);
    void deleteExpense(Integer id);

    void viewAllExpenses();
    void viewMonthlyReport(String month);


    void viewMonthlyAndYearBasisReport(String month, String year);
    void viewCategoriesBasisReport(String categories );
    void viewAllCategories();
    void searchExpenseById(Integer id);






}
