import Controller.ExpenseController;
import Reposistory.ExpenseRepositoryImpl;

import java.util.Scanner;

public class Main {

    static {
        ExpenseRepositoryImpl.loadDataAtTimeOfProgramStart();

    }
    public static void main(String[] args) {


         simulateLoading();
         ExpenseController controller = new ExpenseController();
         Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        while (true) {

            printMainMenu();

            System.out.print("👉 Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> controller.addExpense();
                case 2 -> controller.editExpense();
                case 3 -> controller.deleteExpense();
                case 4 -> controller.viewMonthlyReport();
                case 5 -> controller.viewMonthlyAndYearBasisReport();
                case 6 -> controller.viewAllCategories();
                case 7 -> controller.viewReportByCategories();
                case 8 -> controller.viewAllExpense();
                case 9 -> controller.searchExpenseById();
                case 10 -> controller.viewAllSaveData();
                case 11 -> {
                    System.out.println("\n💡 Thank you for using Expense Tracker. Goodbye!");
                    return;
                }
                default -> System.out.println("\n⚠️ Invalid option. Please try again.");
            }


            System.out.println("\nPress Enter to return to the main menu...");
            scanner.nextLine(); // Clear input buffer
            scanner.nextLine();
        }
    }



    private static void printWelcomeMessage() {
        System.out.println("\n");
        System.out.println("                ==========================================");
        System.out.println("                🤑 WELCOME TO THE EXPENSE TRACKER APP 🤑");
        System.out.println("                ==========================================");

        System.out.println("            Track your expenses effortlessly and stay on budget!");
        System.out.println("         💼 Manage your finances like a pro. Let's get started!\n");
    }


    private static void printMainMenu() {
        System.out.println("\n\n                ------------------------------------------");
        System.out.println("                          ✨ Main Menu ✨");
        System.out.println("                ------------------------------------------");
        System.out.println("                1️⃣  Add Expense");
        System.out.println("                2️⃣  Edit Expense");
        System.out.println("                3️⃣  Delete Expense");
        System.out.println("                4️⃣  View Monthly Report");
        System.out.println("                5️⃣  View Monthly and Yearly Basis Report");
        System.out.println("                6️⃣  View All Categories");
        System.out.println("                7️⃣  View Report Based on Categories");
        System.out.println("                8️⃣  View All Expense ");
        System.out.println("                9️⃣  Search Expenses by ID");
        System.out.println("                🔟  View All Saved Data");
        System.out.println("                1️⃣1️⃣  Exit  ");
//        System.out.println("                1️⃣1️⃣ ");
        System.out.println("                ------------------------------------------");
    }


private static void simulateLoading() {
    String[] loadingSymbols = {"🔄", "⏳", "🚀", "💡"};

    System.out.println("\n\n");
    String centeredText = String.format("%" + (40 + loadingSymbols[0].length()) + "s", "Loading...");
    System.out.print(centeredText);

    try {

        for (int i = 0; i < 5; i++) {
            System.out.print(" " + loadingSymbols[i % loadingSymbols.length]);
            Thread.sleep(500);

            System.out.print("\r" + centeredText);
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    System.out.print("\r" + " ".repeat(centeredText.length())); // Clear the previous loading text


}

}
