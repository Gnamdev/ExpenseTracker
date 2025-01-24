package FilesUtilles;

import Model.Expense;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilesHelperImpl implements FilesHelper{


    private final String LOCAL_PATH = "./src/LocalDatabase/allExpense.txt";

    @Override
    public   void uploadFileToLocal(List<Expense> list) {

        File f1 = new File(LOCAL_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f1))) {
            for (Expense expense : list) {
                writer.write(expense.toString());
            }
            System.out.println("Data successfully written to " + f1.getPath());
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    @Override
    public void getExpenseFromLocal() {

        File file = new File(LOCAL_PATH);


        if (!file.exists()) {
            System.out.println("File not found: " + file);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("+--------------------------------------------------------------+");
            System.out.println("|                        Expense Report                       |");
            System.out.println("+--------------------------------------------------------------+");
            List<Object> arrayList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                System.out.println(line);

                arrayList.add(line);


            }

            System.out.println("Printing data by arrayList : ");

//            arrayList.forEach(System.out::println);
            for (var list :arrayList){


            }



        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

    }

}
