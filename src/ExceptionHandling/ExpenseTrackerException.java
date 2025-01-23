package ExceptionHandling;

public class ExpenseTrackerException extends RuntimeException {
    public ExpenseTrackerException(String message) {
        super(message);
    }
}
