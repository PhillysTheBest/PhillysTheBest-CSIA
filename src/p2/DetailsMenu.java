package p2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DetailsMenu extends JFrame {

    private JPanel contentPane;
    private LinkedList<Card> cards;// Declare a LinkedList to store Card objects
    private int currentCardIndex;
    private JLabel CardTypeLabel;
    private JLabel BalanceLabel;
    private JLabel CardIssuerLabel;
    private JLabel CreditScoreLabel;
    private JLabel creditRangeLabel;
    private JLabel CardDetailsLabel;
    private JLabel CardNumberLabel;
    private JLabel PlusIcon;
    private JComboBox selectCurrency;
    private String[] currencies = {"£","€‎","$"};

    public DetailsMenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 580, 376);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);      
        cards = loadCardsFromFile();// Initialise the LinkedList of cards from file
        currentCardIndex = 0; //Initialise the index starting point to 0 for iteration

        JLabel MainMenuLabel = new JLabel("Main Menu");
        MainMenuLabel.setOpaque(true);
        MainMenuLabel.setForeground(new Color(255, 255, 255));
        MainMenuLabel.setBackground(new Color(128, 0, 255));
        MainMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        MainMenuLabel.setBounds(0, 0, 129, 54);
        MainMenuLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                MainMenu MainMenuFrame = new MainMenu();
                MainMenuFrame.setSize(580, 376);
                MainMenuFrame.setLocationRelativeTo(null);
                MainMenuFrame.setResizable(false);
                MainMenuFrame.setVisible(true);
            }

            public void mouseEntered(MouseEvent e) {
                MainMenuLabel.setBackground(new Color(211, 211, 211));
                MainMenuLabel.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MainMenuLabel.setBackground(new Color(128, 0, 255));
                MainMenuLabel.setOpaque(true);
            }
        });
        contentPane.add(MainMenuLabel);

        JLabel CreditCardLabel = new JLabel("");
        CreditCardLabel.setIcon(new ImageIcon("C:\\Users\\Win10\\Downloads\\credit_card_icon_129105 (10).png"));
        CreditCardLabel.setBounds(237, 95, 77, 120);
        contentPane.add(CreditCardLabel);

        CreditScoreLabel = new JLabel("");
        CreditScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        CreditScoreLabel.setBounds(412, 205, 142, 34);
        contentPane.add(CreditScoreLabel);

        CardTypeLabel = new JLabel("");
        CardTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        CardTypeLabel.setBounds(179, 20, 194, 54);
        contentPane.add(CardTypeLabel);

        BalanceLabel = new JLabel("");
        BalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        BalanceLabel.setBounds(10, 138, 119, 34);
        contentPane.add(BalanceLabel);

        CardIssuerLabel = new JLabel("");
        CardIssuerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        CardIssuerLabel.setBounds(0, 205, 143, 54);
        contentPane.add(CardIssuerLabel);

        PlusIcon = new JLabel("");
        PlusIcon.setIcon(new ImageIcon("C:\\Users\\Win10\\Downloads\\add_insert_new_plus_button_icon_142943.png"));
        PlusIcon.setBounds(361, 95, 98, 99);
        PlusIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                UpdateProcess UpdateProcessFrame = new UpdateProcess();
                UpdateProcessFrame.setSize(580, 376);
                UpdateProcessFrame.setLocationRelativeTo(null);
                UpdateProcessFrame.setResizable(false);
                UpdateProcessFrame.setVisible(true);
            }
        });
        contentPane.add(PlusIcon);

        JButton nextCardButton = new JButton("Next Card");
        nextCardButton.setForeground(new Color(255, 255, 255));
        nextCardButton.setBackground(new Color(128, 0, 255));
        nextCardButton.setBounds(10, 81, 119, 23);
        contentPane.add(nextCardButton);

        creditRangeLabel = new JLabel("");
        creditRangeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        creditRangeLabel.setBounds(412, 250, 119, 54);
        creditRangeLabel.setOpaque(true);
        contentPane.add(creditRangeLabel);
        
        CardDetailsLabel = new JLabel("View Card Details");
        CardDetailsLabel.setOpaque(true);
        CardDetailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        CardDetailsLabel.setForeground(Color.WHITE);
        CardDetailsLabel.setBackground(new Color(128, 0, 255));
        CardDetailsLabel.setBounds(179, 226, 194, 78);
        CardDetailsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	if (!cards.isEmpty()) {
                    Card currentCard = cards.get(currentCardIndex);
                    //iterates through every card to get the current card and pin number
                    dispose(); 
                    CardDetails cardDetailsFrame = new CardDetails(currentCard.getCardNumber(), currentCard.getPinNumber());
                    cardDetailsFrame.setSize(580, 376);
                    cardDetailsFrame.setLocationRelativeTo(null);
                    cardDetailsFrame.setResizable(false);
                    cardDetailsFrame.setVisible(true);
                }
            }

            public void mouseEntered(MouseEvent e) {
            	CardDetailsLabel.setBackground(new Color(211, 211, 211));
            	CardDetailsLabel.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	CardDetailsLabel.setBackground(new Color(128, 0, 255));
            	CardDetailsLabel.setOpaque(true);
            }
        });
        contentPane.add(CardDetailsLabel);
        
        CardNumberLabel = new JLabel("");
        CardNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        CardNumberLabel.setBounds(0, 270, 135, 34);
        contentPane.add(CardNumberLabel);
        
        selectCurrency = new JComboBox();
        selectCurrency.setForeground(new Color(255, 255, 255));
        selectCurrency.setBackground(new Color(128, 0, 255));
        selectCurrency.setBounds(127, 137, 39, 35);
        for (int i=0,count=0;i<currencies.length;i++) {
        	selectCurrency.addItem(currencies[count++]);
        }
		contentPane.add(selectCurrency);
		selectCurrency.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	 updateCardDetails();
		    }
		});	

       
        nextCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {           
                currentCardIndex = (currentCardIndex + 1) % cards.size();// Move to the next card in the LinkedList
                updateCardDetails(); 
            }
        });     
        updateCardDetails();
    }

    private LinkedList<Card> loadCardsFromFile() {// Load Card objects from a file and return them as a LinkedList
    	LinkedList<Card> loadedCards = new LinkedList<>();// Create a LinkedList to store loaded Card objects
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
                    String transactionDateStr = parts[6];
                    double amount = Double.parseDouble(parts[7]);
                    String freezeStatus = parts[8];
                    String reminderPaymentStr = parts[9];               
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date transactionDate = dateFormat.parse(transactionDateStr);
                    Date reminderPayment = dateFormat.parse(reminderPaymentStr);                  
                    Card card = new Card(cardName, cardIssuer, cardNumber, cardType, creditScore, pinNumber,
                            transactionDate, amount, freezeStatus, reminderPayment);
                    loadedCards.add(card);// Add the card to the LinkedList
                }
            }
            reader.close();
        } catch (IOException | java.text.ParseException e) {
            e.printStackTrace();
        }
        return loadedCards;// Return the LinkedList containing Card objects
    }

    private void updateCardDetails() {
        if (!cards.isEmpty()) {
            Card currentCard = cards.get(currentCardIndex);
            CardTypeLabel.setText("Card Type: " + currentCard.getCardType());
            String selectedCurrencySymbol = getSelectedCurrencySymbol();                
            double balance = currentCard.getAmount();// Convert the balance to the selected currency
            double convertedBalance = convertToSelectedCurrency(balance, selectedCurrencySymbol);
            Formatter formatter = new Formatter();
            formatter.format("%.2f", convertedBalance);
            
            BalanceLabel.setText("Balance: " + selectedCurrencySymbol + " " + formatter);
            CardNumberLabel.setText(currentCard.getCardNumber());
            CardIssuerLabel.setText(currentCard.getCardIssuer());
            String creditScore = currentCard.getCreditScore();
            if ("N/A".equals(creditScore)) {
                CreditScoreLabel.setText(""); 
                CreditScoreLabel.setVisible(false);
                creditRangeLabel.setVisible(false); 
            } else {
                CreditScoreLabel.setText("Credit Score: " + creditScore);
                CreditScoreLabel.setVisible(true);         
                int score = Integer.parseInt(creditScore);
                if (score >= 300 && score < 630) {
                    creditRangeLabel.setText("BAD");
                    creditRangeLabel.setForeground(Color.WHITE);
                    creditRangeLabel.setBackground(Color.RED);
                } else if (score >= 630 && score < 670) {
                    creditRangeLabel.setText("Fair");
                    creditRangeLabel.setForeground(Color.WHITE);
                    creditRangeLabel.setBackground(Color.ORANGE);
                } else if (score >= 670 && score < 720) {
                    creditRangeLabel.setText("Good");
                    creditRangeLabel.setForeground(Color.WHITE);
                    creditRangeLabel.setBackground(Color.YELLOW);
                } else if (score >= 720 && score <= 850) {
                    creditRangeLabel.setText("Excellent");
                    creditRangeLabel.setForeground(Color.WHITE);
                    creditRangeLabel.setBackground(Color.GREEN);
                } else {
                    creditRangeLabel.setText("");
                }
                creditRangeLabel.setVisible(true); 
            }
        } else {            
            CardTypeLabel.setText("No cards found.");
            BalanceLabel.setText("");
            CardIssuerLabel.setText("");
            CreditScoreLabel.setText("");
            creditRangeLabel.setText("");
        }
    }
    private String getSelectedCurrencySymbol() {
        return (String) selectCurrency.getSelectedItem();
    }
    private double convertToSelectedCurrency(double amount, String selectedCurrencySymbol) {
    	double poundToEuroRate = 0.0;
	    double poundToDollarRate = 0.0;

	    try {
	        BufferedReader reader = new BufferedReader(new FileReader("Currencies.txt"));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(",");
	            if (parts.length == 3) {
	                String currency = parts[1].trim();
	                double rate = Double.parseDouble(parts[2].trim());

	                if (currency.equals("€")) {
	                    poundToEuroRate = rate;
	                } else if (currency.equals("$")) {
	                    poundToDollarRate = rate;
	                }
	            }
	        }

	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    switch (selectedCurrencySymbol) {
	        case "£":
	            return amount;
	        case "€":
	            return amount * poundToEuroRate;
	        case "$":
	            return amount * poundToDollarRate;
	        default:
	            return amount;
	    }
    }
    






}
