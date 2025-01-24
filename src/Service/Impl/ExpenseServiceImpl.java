package Service.Impl;

import ExceptionHandling.ExpenseTrackerException;
import FilesUtilles.FilesHelper;
import FilesUtilles.FilesHelperImpl;
import Reposistory.ExpenseRepository;
import Model.Expense;
import Reposistory.ExpenseRepositoryImpl;
import Service.ExpenseService;

import java.util.List;
import java.util.Random;

public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository = new ExpenseRepositoryImpl();

    @Override
    public void addExpense(Double amount, String description, String date ,String categories) {


        Expense expense = new Expense();
        expense.setId(generateRandomId());
        expense.setAmount(amount);
        expense.setDate(date);
        expense.setDescription(description);
        expense.setCategory(categories);

        //save

        expenseRepository.addExpense(expense);

        System.out.println("Expense added successfully!");

    }

    private Integer generateRandomId(){
        return  new Random().nextInt(1001);
    }
    @Override
    public void editExpense(Integer id, Double amount, String description, String date,String categories) {

        boolean editExpense = expenseRepository.editExpense(id, description, amount, date, categories);
        if (editExpense){
            System.out.println("Expense updated successfully!");
        } else {
            throw new ExpenseTrackerException("Expense not found for this id : " + id);
        }
    }

    @Override
    public void deleteExpense(Integer id) {
        boolean deleteExpense = expenseRepository.deleteExpense(id);
        if (deleteExpense){
            System.out.println("Expense deleted successfully!");
        } else {
            throw new ExpenseTrackerException("Expense can't deleted . cause Expense  not found for this id : " + id);
        }
    }

    @Override
    public void viewAllExpenses() {

        List<Expense> allExpenses = expenseRepository.getAllExpenses();
        System.out.println("=== All Expenses ===");

        allExpenses.forEach(System.out::print);
        System.out.println();

    }

    @Override
    public void viewMonthlyReport(String month) {
        expenseRepository.getTotalExpenseForMonth(month);
    }

    @Override
    public void viewMonthlyAndYearBasisReport(String month, String year) {
        expenseRepository.viewMonthlyAndYearBasisReport(month,year);
    }

    @Override
    public void viewCategoriesBasisReport(String categories) {
        expenseRepository.viewCategoriesBasisReport(categories);
    }

    @Override
    public void viewAllCategories() {
        expenseRepository.viewAllCategories();
    }

    @Override
    public void searchExpenseById(Integer id) {
        expenseRepository.searchExpenseById(id);
    }




}
