package Reposistory;

import Dto.ExpenseDTO;
import ExceptionHandling.ExpenseTrackerException;
import ExpenseConstant.ExpenseConstant;
import FilesUtilles.FilesHelper;
import FilesUtilles.FilesHelperImpl;
import Model.Expense;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExpenseRepositoryImpl implements ExpenseRepository{

  private final   FilesHelper filesHelper = new FilesHelperImpl();


    //db
    private static final List<Expense> list = new ArrayList<>();

    @Override
    public void addExpense(Expense expense) {
        list.add(expense);
        filesHelper.uploadFileToLocal(list);
    }

    @Override
    public boolean editExpense(Integer id, ExpenseDTO expenseDTO) {

        Expense expenseToEdit = list.stream()
                .filter(exp -> exp.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (expenseToEdit != null) {

            expenseToEdit.setDate(expenseDTO.getDate());
            expenseToEdit.setDescription(expenseDTO.getDescription());
            expenseToEdit.setAmount(expenseDTO.getAmount());
            expenseToEdit.setCategory(expenseDTO.getCategory());


            filesHelper.uploadFileToLocal(list);
            return true;
        }

        return false;

    }

    @Override
    public boolean deleteExpense(Integer id) {
        boolean b = list.removeIf(expense -> expense.getId().equals(id));

        if (b) {
            filesHelper.uploadFileToLocal(list);
            return true;
        }
        return false;
    }

    @Override
    public List<Expense> getAllExpenses() {
        return list;
    }

    @Override
    public void getTotalExpenseForMonth(String month) {
        Double total = 0.0;
        System.out.println("+--------------------------------------------------------------+");
        System.out.println("|                  Monthly Report                              |");
        System.out.println("+--------------------------------------------------------------+");


        if (list.isEmpty()){
            System.out.println("Total Expense: 0 " + " , please add some expense !");
            System.out.println("Total Amount: 0.0");
            return;
        }

        // Print table headers
        System.out.printf("| %-10s | %-10s | %-23s | %-16s | %-16s |%n", "ID", "Date", "Description", "Amount", "Category");
        System.out.println("   +------------+------------+-------------------------+------------------+------------------+");



        for(Expense expense: list){
            if(expense.getDate().substring(3,5).equals(month)){

                System.out.printf("| %-10d | %-10s | %-23s | %-16.2f | %-16s |%n",
                        expense.getId(),
                        expense.getDate(),
                        expense.getDescription(),
                        expense.getAmount(),
                        expense.getCategory());
                total += expense.getAmount();


            }
        }
        System.out.println("Total Amount: "+total);

    }

    @Override
    public void viewMonthlyAndYearBasisReport(String month, String year) {
        Double total = 0.0;
        System.out.println("+--------------------------------------------------------------+");
        System.out.println("|             MonthlyAndYearBasis Report                        |");
        System.out.println("+--------------------------------------------------------------+");

        if (list.isEmpty()){
            System.out.println("Total Expense: 0 " + " , please add some expense !");
            System.out.println("Total Amount: 0.0");
        }

        // Print table headers
        System.out.printf("| %-10s | %-10s | %-23s | %-16s | %-16s |%n", "ID", "Date", "Description", "Amount", "Category");
        System.out.println("   +------------+------------+-------------------------+------------------+------------------+");



        for(Expense expense: list){
            if(expense.getDate().substring(3,5).equals(month)
            && expense.getDate().substring(6,10).equals(year)){
                System.out.printf("| %-10d | %-10s | %-23s | %-16.2f | %-16s |%n",
                        expense.getId(),
                        expense.getDate(),
                        expense.getDescription(),
                        expense.getAmount(),
                        expense.getCategory());
                total += expense.getAmount();


            }
        }
        System.out.println("Total Amount -->  "+total);
    }

    @Override
    public void viewCategoriesBasisReport(String categories) {

        System.out.println("+--------------------------------------------------------------+");
        System.out.println("|                Categories  Basis Report                      |");
        System.out.println("+--------------------------------------------------------------+");


        // Print table headers
        System.out.printf("| %-10s | %-10s | %-23s | %-16s | %-16s |%n", "ID", "Date", "Description", "Amount", "Category");
        System.out.println("+------------+------------+-------------------------+------------------+------------------+");


        list.stream()
                .filter(exe -> exe.getCategory().equalsIgnoreCase(categories))
                .forEach(expense -> {
                    System.out.printf("| %-10d | %-10s | %-23s | %-16.2f | %-16s |%n",
                            expense.getId(),
                            expense.getDate(),
                            expense.getDescription(),
                            expense.getAmount(),
                            expense.getCategory());
                });

         System.out.println();

        double totalExpenseAmount = list.stream()
                .filter(exe -> exe.getCategory().equalsIgnoreCase(categories))
                .mapToDouble(Expense::getAmount)
                .sum();

        System.out.println("Total Expense amount : " + totalExpenseAmount);


    }

    @Override
    public void viewAllCategories() {
        System.out.println("+--------------------------------------------------------------+");
        System.out.println("|                        All Categories                        |");
        System.out.println("+--------------------------------------------------------------+");


        list.stream()
                 .map(Expense::getCategory)
                 .forEach(cat -> {
                     System.out.println(" ==> " + cat);
                 });

    }

    @Override
    public void searchExpenseById(Integer id) {
        // Print table headers
        System.out.printf("| %-10s | %-10s | %-23s | %-16s | %-16s |%n", "ID", "Date", "Description", "Amount", "Category");
        System.out.println("+------------+------------+-------------------------+------------------+------------------+");

        list.stream()
               .filter(exe -> exe.getId().equals(id))
               .forEach(expense -> {
                   System.out.printf("| %-10d | %-10s | %-23s | %-16.2f | %-16s |%n",
                           expense.getId(),
                           expense.getDate(),
                           expense.getDescription(),
                           expense.getAmount(),
                           expense.getCategory());

               });
    }



    @Override
    public void uploadFileToLocal() {
        filesHelper.uploadFileToLocal(list);
        System.out.println("Data saved successfully !");
    }

    @Override
    public void getExpenseFromLocal() {
        filesHelper.getExpenseFromLocal();
    }

    public static void loadDataAtTimeOfProgramStart(){

        List<Expense> expenses = FilesHelperImpl.fetchExpensesFromFile(ExpenseConstant.LOCAL_PATH);
        list.addAll(expenses);
    }
}
