import Controller.ExpenseController;

public class Main {
    public static void main(String[] args) {

        ExpenseController controller = new ExpenseController();
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        while (true) {
            System.out.println("\n=== Expense Tracker ===");
            System.out.println("1. Add Expense");
            System.out.println("2. Edit Expense");
            System.out.println("3. Delete Expense");
            System.out.println("4. View All Expenses");
            System.out.println("5. View Monthly Report");
            System.out.println("6. View Monthly and Yearly basis Report");
            System.out.println("7. View  All categories");
            System.out.println("8. View Report based on categories");
            System.out.println("9. Save your Expenses, Permanently");
            System.out.println("10. Search Expenses by ID");
            System.out.println("11. View all save data ");
            System.out.println("12. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> controller.addExpense();
                case 2 -> controller.editExpense();
                case 3 -> controller.deleteExpense();
                case 4 -> controller.viewAllExpenses();
                case 5 -> controller.viewMonthlyReport();
                case 6 -> controller.viewMonthlyAndYearBasisReport();
                case 7 -> controller.viewAllCategories();
                case 8 -> controller.viewReportByCategories();
                case 9 -> controller.saveExpenseToLocal();
                case 10 -> controller.searchExpenseById();
                case 11 -> controller.viewAllSaveData();


                case 12 -> {
                    System.out.println("Exiting application. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
