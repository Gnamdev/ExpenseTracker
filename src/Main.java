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
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> controller.addExpense();
                case 2 -> controller.editExpense();
                case 3 -> controller.deleteExpense();
                case 4 -> controller.viewAllExpenses();
                case 5 -> controller.viewMonthlyReport();
                case 6 -> {
                    System.out.println("Exiting application. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
