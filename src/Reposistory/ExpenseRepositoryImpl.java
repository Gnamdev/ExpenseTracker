package Reposistory;

import ExceptionHandling.ExpenseTrackerException;
import Model.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseRepositoryImpl implements ExpenseRepository{

    //db
    private static final List<Expense> list = new ArrayList<>();

    @Override
    public void addExpense(Expense expense) {
        list.add(expense);
    }

    @Override
    public boolean editExpense(Integer id, String description, Double amount, String date) {

        return list.stream()
                .filter(exp -> exp.getId().equals(id))
                .findFirst()
                .map(exp -> {
                    exp.setDate(date);
                    exp.setDescription(description);
                    exp.setAmount(amount);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public boolean deleteExpense(Integer id) {
        return list.removeIf(expense -> expense.getId().equals(id));
    }

    @Override
    public List<Expense> getAllExpenses() {
        return list;
    }

    @Override
    public Double getTotalExpenseForMonth(String month) {
//        dd-MM-yyyy
        return list.stream()
                .filter(expense -> expense.getDate().substring(3 , 5).startsWith(month))
                .mapToDouble(Expense::getAmount)
                .sum();
    }


}
