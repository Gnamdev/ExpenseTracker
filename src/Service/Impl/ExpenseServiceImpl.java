package Service.Impl;

import Dto.ExpenseDTO;
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
    public void addExpense(ExpenseDTO expenseDTO) {


        Expense expense = new Expense();
        expense.setId(generateRandomId());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());
        expense.setDescription(expenseDTO.getDescription());
        expense.setCategory(expenseDTO.getCategory());

        //save

        expenseRepository.addExpense(expense);
//        System.out.println("Expense added successfully!");

    }

    private Integer generateRandomId(){
        return  new Random().nextInt(1001);
    }
    @Override
    public void editExpense(Integer id, ExpenseDTO expenseDTO) {

        boolean editExpense = expenseRepository.editExpense(id, expenseDTO);
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

            throw new ExpenseTrackerException("Expense can't deleted . Causes By : Expense  not found for this id : " + id);
        }
    }

    @Override
    public void viewAllExpenses() {

            List<Expense> allExpenses = expenseRepository.getAllExpenses();
            System.out.println("   ==========================================");
            System.out.println("                  ALL EXPENSE ");
            System.out.println("   ==========================================");

            // Check if the list is empty
            if (allExpenses.isEmpty()) {
                System.out.println("Expense is empty, please add some expense!");
                return;
            }

            // Print table headers
            System.out.printf("| %-10s | %-10s | %-23s | %-16s | %-16s |%n", "ID", "Date", "Description", "Amount", "Category");
            System.out.println("   +------------+------------+-------------------------+------------------+------------------+");

            // Print all expenses
            for (Expense expense : allExpenses) {
                // Print each expense in a row
                System.out.printf("| %-10d | %-10s | %-23s | %-16.2f | %-16s |%n",
                        expense.getId(),
                        expense.getDate(),
                        expense.getDescription(),
                        expense.getAmount(),
                        expense.getCategory());
            }

            System.out.println("   ==========================================");
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
