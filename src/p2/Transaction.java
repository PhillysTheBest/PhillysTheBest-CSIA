package p2;

import java.util.Date;
import java.util.List;

public class Transaction {
    private Date transactionDate;
    private double amount;
    private List<Transaction> transactionHistory; // List to store transaction history
    public Transaction(Date transactionDate, double amount) {
        this.transactionDate = transactionDate;
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
    public double getAmount() {
        return amount;
    }
}
