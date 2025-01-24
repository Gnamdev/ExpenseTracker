package Reposistory;

import ExceptionHandling.ExpenseTrackerException;
import FilesUtilles.FilesHelper;
import FilesUtilles.FilesHelperImpl;
import Model.Expense;

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
    }

    @Override
    public boolean editExpense(Integer id, String description, Double amount, String date , String categories) {

        return list.stream()
                .filter(exp -> exp.getId().equals(id))
                .findFirst()
                .map(exp -> {
                    exp.setDate(date);
                    exp.setDescription(description);
                    exp.setAmount(amount);
                    exp.setCategory(categories);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public boolean deleteExpense(Integer id) {
        return list.removeIf(expense -> expense.getId().equals(id));
    }

    @Override
    public List<Expense> getAllExpenses() {
        return list;
    }

    @Override
    public void getTotalExpenseForMonth(String month) {
        Double total = 0.0;

        if (list.isEmpty()){
            System.out.println("Total Expense: 0 " + " , please add some expense !");
            System.out.println("Total Amount: 0.0");
        }
        for(Expense expense: list){
            if(expense.getDate().substring(3,5).equals(month)){
                System.out.println("Total Expense: "+expense);
                total += expense.getAmount();
                System.out.println("Total Amount: "+total);
            }
        }

    }

    @Override
    public void viewMonthlyAndYearBasisReport(String month, String year) {
        Double total = 0.0;

        if (list.isEmpty()){
            System.out.println("Total Expense: 0 " + " , please add some expense !");
            System.out.println("Total Amount: 0.0");
        }


        for(Expense expense: list){
            if(expense.getDate().substring(3,5).equals(month)
            && expense.getDate().substring(6,10).equals(year)){
                System.out.println("Total Expense: "+expense);
                total += expense.getAmount();
                System.out.println("Total Amount: "+total);
            }
        }
    }

    @Override
    public void viewCategoriesBasisReport(String categories) {



         list.stream()
                .filter(exe -> exe.getCategory().equals(categories))
                .forEach(System.out::println);

         System.out.println();

        double totalExpenseAmount = list.stream()
                .filter(exe -> exe.getCategory().equals(categories))
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
                 .forEach(System.out::println);

    }

    @Override
    public void searchExpenseById(Integer id) {
       list.stream()
               .filter(exe -> exe.getId().equals(id))
               .forEach(System.out::println);
    }

    public void uploadDataToLocal(){
        filesHelper.uploadFileToLocal(list);
    }
    public void getAllExpenseToFile(){
        filesHelper.getExpenseFromLocal();
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
}
