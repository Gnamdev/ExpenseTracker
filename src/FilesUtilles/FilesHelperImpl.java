package FilesUtilles;


import ExpenseConstant.ExpenseConstant;
import Model.Expense;
import Reposistory.ExpenseRepositoryImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilesHelperImpl implements FilesHelper{



    @Override
    public   void uploadFileToLocal(List<Expense> list) {

        File f1 = new File(ExpenseConstant.LOCAL_PATH);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f1))) {
            writer.write("==========================================\n");
            writer.write("                  ALL EXPENSE             \n");
            writer.write("   =========================================\n");

            // Write header of the table
            writer.write("+------------+------------+-------------------------+------------------+------------------+\n");
            writer.write("| ID         | Date       | Description             | Amount           | Category         |\n");
            writer.write("+------------+------------+-------------------------+------------------+------------------+\n");

            // Write expense rows
            for (Expense expense : list) {
                writer.write(expense.toString() + "\n");
                writer.write("+------------+------------+-------------------------+------------------+------------------+\n");
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    @Override
    public void getExpenseFromLocal() {

        File file = new File(ExpenseConstant.LOCAL_PATH);

        if (!file.exists()) {
            System.out.println("File not found: " + file);
            return;
        }
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            System.out.println("+--------------------------------------------------------------+");
//            System.out.println("|                        Expense Report                       |");
//            System.out.println("+--------------------------------------------------------------+");
//
//            while ((line = reader.readLine()) != null) {
//                if (line.trim().isEmpty()) {
//                    continue;
//                }
//                System.out.println(line);
//            }
//
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found: " + file);
//        } catch (IOException e) {
//            System.out.println("An error occurred while reading the file: " + e.getMessage());
//        }

        System.out.println("+-----------------------------------------------------+");
        System.out.println("|                     All Expense                     |");
        System.out.println("+-----------------------------------------------------+");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
//                if (line.contains("ALL EXPENSE") || line.contains("ID")) {
//                    System.out.println(line);
//                    continue;
//                }

                if (line.contains("+------------+")) {
                    System.out.println(line);
                }
                if (line.startsWith("|")) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Expense> fetchExpensesFromFile(String filePath) {
        List<Expense> expenses = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("File not found: " + filePath);
            return expenses;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isExpenseData = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("+------------+") && isExpenseData) {
                    continue;
                } else if (line.startsWith("| ID")) {

                    isExpenseData = true;
                    continue;
                } else if (line.startsWith("|")) {

                    String[] parts = line.split("\\|");
                    if (parts.length == 6) {
                        try {
                            Integer id = Integer.parseInt(parts[1].trim());
                            String date = parts[2].trim();
                            String description = parts[3].trim();
                            Double amount = Double.parseDouble(parts[4].trim());
                            String category = parts[5].trim();

                            expenses.add(new Expense(id, date, description, amount, category));
                        } catch (NumberFormatException e) {
                            System.err.println("Error parsing line: " + line);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return expenses;
    }



}
