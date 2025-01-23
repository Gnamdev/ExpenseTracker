package Service;

public interface ExpenseService {

    void addExpense(Double amount , String description , String date);
    void editExpense(Integer id , Double amount , String description , String date);
    void deleteExpense(Integer id);

    void viewAllExpenses();
    void viewMonthlyReport(String month);


    void viewMonthlyAndYearBasisReport(String month, String year);
    void viewMonthlyAndYearAndCategoriesBasisReport(String categories ,String month, String year );


}
