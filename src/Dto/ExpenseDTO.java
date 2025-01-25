package Dto;

public class ExpenseDTO {

    private Double amount;
    private String description;
    private String date;
    private String category;


    public ExpenseDTO() {}


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = (description == null || description.isBlank()) ? "No description provided" : description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if (!isValidDate(date)) {
            throw new IllegalArgumentException("Invalid date format. Expected format: DD-MM-YYYY.");
        }
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category cannot be blank.");
        }
        this.category = category;
    }


    private boolean isValidDate(String date) {
        String dateRegex = "\\d{2}-\\d{2}-\\d{4}"; // Matches DD-MM-YYYY
        return date != null && date.matches(dateRegex);
    }
}
