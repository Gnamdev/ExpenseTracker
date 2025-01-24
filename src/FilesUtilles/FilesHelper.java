package FilesUtilles;

import Model.Expense;

import java.util.List;

public interface FilesHelper {
    void uploadFileToLocal(List<Expense> list);
 void   getExpenseFromLocal();
}
