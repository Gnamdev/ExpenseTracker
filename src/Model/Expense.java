package Model;

public class Expense {

    private Integer id;
    private String date;
    private String description;

    private Double amount;

    private String category;


   public Expense(){

   }

    public Expense(Integer id, String date, String description, Double amount, String category) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }



//    @Override
//    public String toString() {
//        return "Expense -> id=" + id +
//                ", date='" + date + '\'' +
//                ", description='" + description + '\'' +
//                ", amount=" + amount +
//                ", category='" + category + '\n';
//    }

    @Override
    public String toString() {
        // Column formatting for table-like structure
        String format = "| %-10s | %-10s | %-23s | %-16s | %-16s |";

        return String.format(format, id, date, description, amount, category);
    }


}
