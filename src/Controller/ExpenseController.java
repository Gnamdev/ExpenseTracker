package Controller;

import Reposistory.ExpenseRepository;
import Reposistory.ExpenseRepositoryImpl;
import Service.ExpenseService;
import Service.Impl.ExpenseServiceImpl;

import java.util.Scanner;

public class ExpenseController {
    private final ExpenseService expenseService = new ExpenseServiceImpl();
    private final ExpenseRepository  expenseRepository = new ExpenseRepositoryImpl();
    private final Scanner scanner = new Scanner(System.in);

    public void addExpense() {
        Scanner sc = new Scanner(System.in);
    boolean b =false;
        String categories = null;
        do {
            System.out.print("Enter categories: ");
            categories = sc.nextLine();
            if(!categories.isBlank()) b = true;
        }while (!b);
        System.out.println("add new expense ");

        System.out.println("Enter description: ");
        String description = scanner.nextLine(); //optional

        System.out.println("Enter amount: ");
        Double amount = scanner.nextDouble();


        System.out.println("Enter date (DD-MM-YYYY): ");
        String date = scanner.next();


        expenseService.addExpense(amount , description , date , categories );
    }

    public void editExpense() {
        System.out.print("Enter expense ID to edit: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new description: ");
        String description = scanner.nextLine();

        System.out.print("Enter new amount: ");
        Double amount = scanner.nextDouble();

        System.out.print("Enter categories: ");

        String  categories = scanner.nextLine();
        System.out.print("Enter new date (DD-MM-YYYY): ");
        String date = scanner.next();
        scanner.nextLine();

        expenseService.editExpense(id , amount, description , date, categories);
    }

    public void deleteExpense() {
        System.out.print("Enter expense ID to delete: ");
        Integer id = scanner.nextInt();
        expenseService.deleteExpense(id);
    }

    public void viewAllExpenses() {
        expenseService.viewAllExpenses();
    }

    public void viewMonthlyReport() {
        System.out.print("Enter month  (MM): ");
        String month = scanner.next();
        expenseService.viewMonthlyReport(month);
    }
    public void viewMonthlyAndYearBasisReport( ){
        System.out.print("Enter month and year (MM-YYYY): ");
        System.out.println();

        System.out.println("month : ");
        String m = scanner.next();
        System.out.println("Year : ");
        String y = scanner.next();

        expenseService.viewMonthlyAndYearBasisReport(m , y);

    }

    public void viewAllCategories(){
      expenseService.viewAllCategories();

    }
    public void searchExpenseById(){

        System.out.println("Enter Expense ID:");
        Integer id = scanner.nextInt();

      expenseService.searchExpenseById(id);

    }
    public void viewReportByCategories(){

        System.out.println("Enter Expense Category:");
        String category = scanner.nextLine();

      expenseService.viewCategoriesBasisReport(category);

    }

    public void saveExpenseToLocal(){
        expenseRepository.uploadFileToLocal();
    }
    public void viewAllSaveData(){
        expenseRepository.getExpenseFromLocal();
    }
}
