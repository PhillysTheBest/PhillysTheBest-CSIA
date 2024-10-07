package p2;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.text.SimpleDateFormat;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MonitoringMenu extends JFrame {

	private JPanel contentPane;
	private JLabel CardNameLabel;
    private JButton NextCardButton;
    private List<String> cardNames;
    private List<String> cardNumbers;
    private int currentCardIndex;
    private JButton FreezeButton;
    private JLabel ReminderPaymentLabel;
	private JLabel FraudulentActivityLabel;
	private boolean suspicious = false;
	private JLabel CardNumberLabel;



	public MonitoringMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 580, 376);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
     
        Random random = new Random();
        suspicious = random.nextInt(100) < 5; 


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
                MainMenuLabel.setBackground(new Color(211,211,211));
                MainMenuLabel.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MainMenuLabel.setBackground(new Color(128, 0, 255));
                MainMenuLabel.setOpaque(true);
            }
        });
        contentPane.add(MainMenuLabel);

        cardNames = loadCardNamesFromDetailsFile();
        cardNumbers= loadCardNumbersFromDetailsFile();
        currentCardIndex = 0;
        CardNameLabel = new JLabel("");
        CardNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        CardNameLabel.setBounds(6, 168, 134, 28);
        updateCardNameLabel(); 
        contentPane.add(CardNameLabel);

        NextCardButton = new JButton("Next card");
        NextCardButton.setForeground(new Color(255, 255, 255));
        NextCardButton.setBackground(new Color(128, 0, 255));
        NextCardButton.setBounds(22, 246, 118, 41);
        NextCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	currentCardIndex = (currentCardIndex + 1) % cardNames.size();
                updateCardNameLabel();
                updateCardNumberLabel();
                updateFreezeButtonText(); 
                updateReminderPaymentLabel(getCurrentCardName());
            }
        });
        contentPane.add(NextCardButton);

        ReminderPaymentLabel = new JLabel("");
        ReminderPaymentLabel.setBounds(342, 75, 242, 54);
        contentPane.add(ReminderPaymentLabel);
        String reminderPaymentDate = ""; 
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Details.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 10) {
                    reminderPaymentDate = parts[9]; 
                    break; 
                }
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        
        ReminderPaymentLabel.setText("Reminder Payment due at: " + reminderPaymentDate);

        FraudulentActivityLabel = new JLabel("");
        FraudulentActivityLabel.setBounds(185, 275, 190, 28);
        FraudulentActivityLabel.setText("No fraudulent activity detected");
        contentPane.add(FraudulentActivityLabel);

        JLabel CreditCardIcon = new JLabel("");
        CreditCardIcon.setIcon(new ImageIcon("C:\\Users\\Win10\\Downloads\\credit_card_icon_129105 (9).png"));
        CreditCardIcon.setBounds(44, 104, 85, 79);
        contentPane.add(CreditCardIcon);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Win10\\Downloads\\openlock_120634.png"));
        lblNewLabel.setBounds(240, 166, 170, 121);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Win10\\Downloads\\spy_96975.png"));
        lblNewLabel_1.setBounds(251, 65, 61, 122);
        contentPane.add(lblNewLabel_1);

        FreezeButton = new JButton("");
        FreezeButton.setForeground(new Color(255, 255, 255));
        FreezeButton.setBackground(new Color(128, 0, 255));
        FreezeButton.setBounds(185, 11, 175, 43);
        contentPane.add(FreezeButton);
        FreezeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleFreeze(); 
            }
        });
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Win10\\Downloads\\alarm_bell_icon_135988.png"));
        lblNewLabel_2.setBounds(419, 122, 85, 109);
        contentPane.add(lblNewLabel_2);
        
        CardNumberLabel = new JLabel("");
        CardNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        CardNumberLabel.setBounds(10, 207, 130, 28);
        updateCardNumberLabel(); 
        contentPane.add(CardNumberLabel);
        
        
        updateFreezeButtonText();		
	}
	private void toggleFreeze() {
		String currentCardName = getCurrentCardName();
	    boolean isFrozen = isCardFrozen(currentCardName);
	    if (isFrozen) {
	        setCardUnfrozen(currentCardName);
	    } else {
	        setCardFrozen(currentCardName);
	        if (isSuspiciousActivity(currentCardName)) {
	            suspicious = true; 
	            generateFreezePDF(currentCardName);
	        }
	    }
	    if (isSuspiciousActivity(currentCardName)) {
	        suspicious = true;
	    }
	    updateFreezeButtonText();
    }
	public boolean isSuspiciousActivity(String cardName) {
		
	    List<Transaction> transactions = loadTransactionHistory(cardName);

	   
	    int consecutiveLargeTransactionsThreshold = 3; 
	    double largeTransactionThreshold = 1000.0; 	    
	    int consecutiveLargeTransactions = 0;
	    for (Transaction transaction : transactions) {
	        if (transaction.getAmount() >= largeTransactionThreshold) {
	            consecutiveLargeTransactions++;
	            if (consecutiveLargeTransactions >= consecutiveLargeTransactionsThreshold) {
	                return true; 
	            }
	        } else {
	            consecutiveLargeTransactions = 0;
	        }
	    }	    
	    return false;
	}

	
	private List<Transaction> loadTransactionHistory(String cardName) {
	   	    return new ArrayList<>(); 
	}
	private void updateFreezeButtonText() {
		String currentCardName = getCurrentCardName();
        boolean isFrozen = isCardFrozen(currentCardName);
        FreezeButton.setText("Freeze: " + (isFrozen ? "On" : "Off"));
    }

    private boolean isCardFrozen(String cardName) {
    	try {
            BufferedReader reader = new BufferedReader(new FileReader("Details.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6 && parts[0].equals(cardName)) { 
                    String freezeStatus = parts[8]; 
                    return freezeStatus.equals("On");
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; 
    }

    
    private void setCardFrozen(String cardName) {
        updateFreezeStatus(cardName, "On");
    }

    
    private void setCardUnfrozen(String cardName) {
        updateFreezeStatus(cardName, "Off");
    }
    private void updateFreezeStatus(String cardName, String newStatus) {
        try {
            File file = new File("Details.txt");           
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();           
            for (int i = 0; i < lines.size(); i++) {
                String currentLine = lines.get(i);
                String[] parts = currentLine.split(",");
                if (parts.length >= 6 && parts[0].equals(cardName)) {
                    parts[8] = newStatus; 
                    lines.set(i, String.join(",", parts));
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();

            System.out.println("Freeze status updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to update freeze status.");
        }
    }


	private List<String> loadCardNamesFromDetailsFile() {
        List<String> names = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Details.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    names.add(parts[0]); 
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }
	private List<String> loadCardNumbersFromDetailsFile(){
		List<String> numbers = new ArrayList<>();
		try {
            BufferedReader reader = new BufferedReader(new FileReader("Details.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    numbers.add(parts[2]); 
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
	}

    private void updateCardNameLabel() {
    	if (!cardNames.isEmpty()) {
            String currentCardName = cardNames.get(currentCardIndex);
            CardNameLabel.setText(currentCardName);
        } else {
            CardNameLabel.setText("No cards found.");
        }
    }
    private void updateCardNumberLabel() {
    	if (!cardNumbers.isEmpty()) {
            String currentCardNumber = cardNumbers.get(currentCardIndex);
            CardNumberLabel.setText(currentCardNumber);
        } else {
            CardNameLabel.setText("No cards found.");
        }
    }
    
    public String getCurrentCardName() {
        if (!cardNames.isEmpty()) {
            return cardNames.get(currentCardIndex);
        } else {
            return "";
        }
    }
    private void updateReminderPaymentLabel(String cardName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Details.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6 && parts[0].equals(cardName)) {
                    String reminderPaymentDate = parts[9]; 
                    ReminderPaymentLabel.setText("Reminder payment due at: " + reminderPaymentDate);
                    reader.close();
                    return; 
                }
            }
            reader.close();          
            ReminderPaymentLabel.setText("No reminder payment found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void generateFreezePDF(String cardName) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(cardName + "_freeze_report.pdf"));
            document.open();
            Paragraph title = new Paragraph("Card Freeze Report");
            document.add(title);
            Paragraph content = new Paragraph("Card Name: " + cardName + "\nStatus: Frozen\nDate: " + getCurrentDate());
            document.add(content);
            document.close();
            System.out.println("Freeze report PDF generated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to generate freeze report PDF.");
        }
    }
    private String getCurrentDate() {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    
    
}
