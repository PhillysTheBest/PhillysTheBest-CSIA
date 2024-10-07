package p2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Card {
	// Private instance variables (fields) for encapsulation
    private String cardName;
    private String cardIssuer;
    private String cardType;
    private double amount;
    private String creditScore;
    private String cardNumber;
    private String pinNumber;
    private List<Transaction> transactionHistory; 
    // Constructor initialises the private fields
    public Card(String cardName, String cardIssuer, String cardNumber, String cardType, String creditScore, String pinNumber, Date transactionDate, double amount, String freezeStatus, Date reminderPayment) {
        this.cardName = cardName;
        this.cardIssuer = cardIssuer;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.pinNumber = pinNumber;
        this.amount = amount;
        this.creditScore=creditScore;
        this.transactionHistory = new ArrayList<>(); 
    }
    // Getter methods for controlled access to private fields
    public String getCardName() {
        return cardName;
    }
    public double getAmount() {
    	return amount;
    }
    public String getPinNumber() {
    	return pinNumber;
    }
    public String getCardNumber() {
    	return cardNumber;
    }
    public String getCardIssuer() {
        return cardIssuer;
    }
    public String getCardType() {
    	return cardType;
    }
    public String getCreditScore() {
    	return creditScore;
    }
    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
    
    public double getMoney() {
        double totalMoney = 0.0;
        
        List<Card> cards = loadCardsFromFile();
        for (Card card : cards) {
            for (Transaction transaction : card.getTransactionHistory()) {
                totalMoney += transaction.getAmount();
            }
        }
        
        return totalMoney;
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }
    public static List<Card> loadCardsFromFile() {
        List<Card> cards = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Details.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 10) {  
                    String cardName = parts[0];
                    String cardIssuer = parts[1];
                    String cardNumber = parts[2];
                    String cardType = parts[3];
                    String creditScore = parts[4];
                    String pinNumber = parts[5];
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date transactionDate = dateFormat.parse(parts[6]);
                    double amount = Double.parseDouble(parts[7]);
                    String freezeStatus = parts[8];
                    Date reminderPayment = dateFormat.parse(parts[9]);
                    Card card = new Card(cardName, cardIssuer, cardNumber, cardType, creditScore, pinNumber, transactionDate, amount, freezeStatus, reminderPayment); // Updated field name
                    card.addTransaction(new Transaction(transactionDate, amount));
                    card.addTransaction(new Transaction(reminderPayment, 0.0)); // Assuming you want to include reminder payment as a transaction
                    cards.add(card);
                }
            }
            reader.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return cards;
    }

}
