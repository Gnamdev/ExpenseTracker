package Controller;

import Service.ExpenseService;
import Service.Impl.ExpenseServiceImpl;

import java.util.Scanner;

public class ExpenseController {
    private final ExpenseService expenseService = new ExpenseServiceImpl();
    private final Scanner scanner = new Scanner(System.in);

    public void addExpense() {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter amount: ");
        Double amount = scanner.nextDouble();

        System.out.print("Enter date (DD-MM-YYYY): ");
        String date = scanner.next();
        scanner.nextLine();

        expenseService.addExpense(amount , description , date );
    }

    public void editExpense() {
        System.out.print("Enter expense ID to edit: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new description: ");
        String description = scanner.nextLine();

        System.out.print("Enter new amount: ");
        Double amount = scanner.nextDouble();
        System.out.print("Enter new date (DD-MM-YYYY): ");
        String date = scanner.next();
        scanner.nextLine();

        expenseService.editExpense(id , amount, description , date);
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
    public void viewMonthlyAndYearBasisReport(String month , String year ){
        System.out.print("Enter month and year (YYYY-MM): ");
        System.out.println("month : ");
        String m = scanner.next();
        System.out.println("Year : ");
        String y = scanner.nextLine();

        expenseService.viewMonthlyAndYearBasisReport(m , y);

    }
}
