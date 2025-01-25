package Controller;

import Dto.ExpenseDTO;
import Reposistory.ExpenseRepository;
import Reposistory.ExpenseRepositoryImpl;
import Service.ExpenseService;
import Service.Impl.ExpenseServiceImpl;

import java.util.Scanner;

public class ExpenseController {
    private final ExpenseService expenseService = new ExpenseServiceImpl();
    private final ExpenseRepository expenseRepository = new ExpenseRepositoryImpl();
    private final Scanner scanner = new Scanner(System.in);

    public void addExpense() {

        Scanner scanner = new Scanner(System.in);
        ExpenseDTO expenseDTO = new ExpenseDTO();
        boolean isValid;

        do {
            System.out.print("Enter category: ");
            String category = scanner.nextLine();
            try {
                expenseDTO.setCategory(category);
                isValid = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                isValid = false;
            }
        } while (!isValid);

        System.out.print("Enter description (optional): ");
        String description = scanner.nextLine();
        expenseDTO.setDescription(description);

        // validate the amount
        do {
            System.out.print("Enter amount: ");
            try {
                Double amount = Double.parseDouble(scanner.nextLine());
                expenseDTO.setAmount(amount);
                isValid = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Amount must be a valid number greater than 0.");
                isValid = false;
            }
        } while (!isValid);

        // validate the date
        do {
            System.out.print("Enter date (DD-MM-YYYY): ");
            String date = scanner.nextLine();
            try {
                expenseDTO.setDate(date);
                isValid = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                isValid = false;
            }
        } while (!isValid);

        expenseService.addExpense(expenseDTO);
        System.out.println(" ");
        System.out.println("Expense added successfully! 🎉");


    }

    public void editExpense() {

            Scanner scanner = new Scanner(System.in);
            ExpenseDTO expenseDTO = new ExpenseDTO();
            boolean isValid;
            Integer id = null;

          // validate expense ID
            do {
                System.out.print("Enter expense ID to edit: ");
                try {
                    id = Integer.parseInt(scanner.nextLine());
                    if (id != null && id <= 0) {
                        throw new IllegalArgumentException("Expense ID must be a positive integer.");
                    }
                    isValid = true;
                } catch (IllegalArgumentException e){
                    System.out.println("Expense ID must be a positive integer.");
                    isValid = false;
                }
            } while (!isValid);

            // validate description
            System.out.print("Enter new description (optional): ");
            String description = scanner.nextLine();
            expenseDTO.setDescription(description);

            //  validate amount
            do {
                System.out.print("Enter new amount: ");
                try {
                    String amountInput = scanner.nextLine();
                    if (!amountInput.isBlank()) {
                        Double amount = Double.parseDouble(amountInput);
                        expenseDTO.setAmount(amount);
                    }
                    isValid = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Amount must be a valid number greater than 0.");
                    isValid = false;
                }
            } while (!isValid);

            //  validate category
            do {
                System.out.print("Enter new category: ");
                String category = scanner.nextLine();
                try {
                    expenseDTO.setCategory(category);
                    isValid = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    isValid = false;
                }
            } while (!isValid);

            //  validate date
            do {
                System.out.print("Enter new date (DD-MM-YYYY): ");
                String date = scanner.nextLine();
                try {
                    if (!date.isBlank()) {
                        expenseDTO.setDate(date);
                    }
                    isValid = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    isValid = false;
                }
            } while (!isValid);

            expenseService.editExpense(id ,expenseDTO);
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

    public void viewMonthlyAndYearBasisReport() {
        System.out.print("Enter month and year (MM-YYYY): ");
        System.out.println();

        System.out.println("month : ");
        String m = scanner.next();
        System.out.println("Year : ");
        String y = scanner.next();

        expenseService.viewMonthlyAndYearBasisReport(m, y);

    }

    public void viewAllCategories() {
        expenseService.viewAllCategories();

    }

    public void searchExpenseById() {

        System.out.println("Enter Expense ID:");
        Integer id = scanner.nextInt();

        expenseService.searchExpenseById(id);

    }

    public void viewReportByCategories() {

        System.out.println("Enter Expense Category:");
        String category = scanner.nextLine();

        expenseService.viewCategoriesBasisReport(category);

    }

    public void viewAllSaveData() {
        expenseRepository.getExpenseFromLocal();
    }
    public void viewAllExpense() {
        expenseService.viewAllExpenses();
    }
}
