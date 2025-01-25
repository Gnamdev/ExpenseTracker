package Service;

import Dto.ExpenseDTO;
import Model.Expense;

import java.util.List;

public interface ExpenseService {

    void addExpense(ExpenseDTO expenseDTO);
    void editExpense(Integer id , ExpenseDTO expenseDTO);
    void deleteExpense(Integer id);

    void viewAllExpenses();
    void viewMonthlyReport(String month);


    void viewMonthlyAndYearBasisReport(String month, String year);
    void viewCategoriesBasisReport(String categories );
    void viewAllCategories();
    void searchExpenseById(Integer id);






}
