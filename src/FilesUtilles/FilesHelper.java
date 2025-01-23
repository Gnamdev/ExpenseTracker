package FilesUtilles;

import Model.Expense;

public interface FilesHelper {
    void uploadFileToLocalFile(Expense expense);
    void getExpenseFromLocalFile();
}
