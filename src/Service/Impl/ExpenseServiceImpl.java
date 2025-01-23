package Service.Impl;

import ExceptionHandling.ExpenseTrackerException;
import Reposistory.ExpenseRepository;
import Model.Expense;
import Reposistory.ExpenseRepositoryImpl;
import Service.ExpenseService;

import java.util.List;
import java.util.Random;

public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository = new ExpenseRepositoryImpl();

    @Override
    public void addExpense(Double amount, String description, String date) {


        Expense expense = new Expense();
        expense.setId(generateRandomId());
        expense.setAmount(amount);
        expense.setDate(date);
        expense.setDescription(description);

        //save

        expenseRepository.addExpense(expense);

        System.out.println("Expense added successfully!");

    }

    private Integer generateRandomId(){
        return  new Random().nextInt(1001);
    }
    @Override
    public void editExpense(Integer id, Double amount, String description, String date) {

        boolean editExpense = expenseRepository.editExpense(id, description, amount, date);
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

        Double totalExpenseForMonth =
                expenseRepository.getTotalExpenseForMonth(month);
    }

    @Override
    public void viewMonthlyAndYearBasisReport(String month, String year) {

    }

    @Override
    public void viewMonthlyAndYearAndCategoriesBasisReport(String categories, String month, String year) {

    }
}
