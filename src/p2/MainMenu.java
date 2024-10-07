package p2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;




public class MainMenu extends JFrame {

	private JPanel contentPane;
	private LinkedList<Card> cardsLinkedList = new LinkedList<>();
	private JLabel totalCashLabel;
    private JLabel lblReminderPayments;
    private MonitoringMenu MonitoringMenuFrame;
    private JLabel lblTransactionHistory;
    private User currentUser;
    private String[] currencies = {"£","€‎","$"};
    private JComboBox selectCurrency;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();	
					frame.setResizable(false);
					frame.setSize(580, 376);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();

		setContentPane(contentPane);
		
		JLabel CardManagerLabel = new JLabel("Card Manager");
		CardManagerLabel.setBounds(0, 0, 122, 51);
		CardManagerLabel.setOpaque(true);
		CardManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CardManagerLabel.setForeground(Color.WHITE);
		CardManagerLabel.setBackground(new Color(128, 0, 255));
		CardManagerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	            dispose();  
	            CardManager cardManagerFrame = new CardManager();
	            cardManagerFrame.setSize(580, 376);
	            cardManagerFrame.setLocationRelativeTo(null);
	            cardManagerFrame.setResizable(false);
	            cardManagerFrame.setVisible(true);
	        }
            public void mouseEntered(MouseEvent e) {
				CardManagerLabel.setBackground(new Color(211,211,211));
				CardManagerLabel.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	CardManagerLabel.setBackground(new Color(128, 0, 255));
            	CardManagerLabel.setOpaque(true);
            }
		});
		contentPane.setLayout(null);
		contentPane.add(CardManagerLabel);
		
		totalCashLabel = new JLabel("");
		totalCashLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		totalCashLabel.setBounds(145, 18, 254, 33);
		contentPane.add(totalCashLabel);

		Timer timer = new Timer(10000, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        updateTotalCashLabel();
		    }
		});
		timer.start();
		
		this.currentUser = new User("initialUsername", "initialPassword", "initialUserID","initialEmail");
		JLabel SettingsLabel = new JLabel("");
		SettingsLabel.setBounds(482, 11, 48, 51);
		SettingsLabel.setIcon(new ImageIcon("C:\\Users\\Win10\\Downloads\\Data_settings_theapplication_3364.png"));
		SettingsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();  
                SettingsMenu SettingsMenuFrame = new SettingsMenu(MainMenu.this.currentUser);
                SettingsMenuFrame.setSize(580, 376);
                SettingsMenuFrame.setLocationRelativeTo(null);
                SettingsMenuFrame.setResizable(false);
                SettingsMenuFrame.setVisible(true);
            }
        });
		contentPane.add(SettingsLabel);
		
		
		
		JPanel splitPanel = new JPanel();
		splitPanel.setBounds(0, 93, 562, 45);
		splitPanel.setBackground(new Color(128, 0, 255));
		contentPane.add(splitPanel);
		
		JLabel lblAdddeleteCards = new JLabel("Add/Delete cards");
		lblAdddeleteCards.setBounds(0, 177, 122, 51);
		lblAdddeleteCards.setOpaque(true);
		lblAdddeleteCards.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdddeleteCards.setForeground(Color.WHITE);
		lblAdddeleteCards.setBackground(new Color(128, 0, 255));
		contentPane.add(lblAdddeleteCards);
		
		JLabel CardLabel = new JLabel("");
		CardLabel.setBounds(36, 231, 48, 48);
		CardLabel.setIcon(new ImageIcon("C:\\Users\\Win10\\Downloads\\credit_card_icon_129105 (1).png"));
		contentPane.add(CardLabel);
		
		JLabel lblBudgetingMeter = new JLabel("Budgeting meter");
		lblBudgetingMeter.setBounds(293, 177, 122, 51);
		lblBudgetingMeter.setOpaque(true);
		lblBudgetingMeter.setHorizontalAlignment(SwingConstants.CENTER);
		lblBudgetingMeter.setForeground(Color.WHITE);
		lblBudgetingMeter.setBackground(new Color(128, 0, 255));
		contentPane.add(lblBudgetingMeter);
		
		JLabel DetailsLabel = new JLabel("Details");
		DetailsLabel.setBounds(0, 290, 122, 47);
		DetailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		DetailsLabel.setOpaque(true);
		DetailsLabel.setBackground(new Color(128, 0, 255));
		DetailsLabel.setForeground(new Color(255, 255, 255));
		DetailsLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	            dispose();  
	            DetailsMenu DetailsMenuFrame = new DetailsMenu();
	            DetailsMenuFrame.setSize(580, 376);
	            DetailsMenuFrame.setLocationRelativeTo(null);
	            DetailsMenuFrame.setResizable(false);
	            DetailsMenuFrame.setVisible(true);
	        }
			@Override
            public void mouseEntered(MouseEvent e) {
				DetailsLabel.setBackground(new Color(211,211,211));
				DetailsLabel.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	DetailsLabel.setBackground(new Color(128, 0, 255));
            	DetailsLabel.setOpaque(true);
            }
		});
		contentPane.add(DetailsLabel);
		
		JLabel HistoryLabel= new JLabel("History");
		HistoryLabel.setBounds(149, 290, 122, 47);
		HistoryLabel.setOpaque(true);
		HistoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HistoryLabel.setForeground(Color.WHITE);
		HistoryLabel.setBackground(new Color(128, 0, 255));
		HistoryLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	            dispose(); 
	            HistoryMenu HistoryMenuFrame = new HistoryMenu(cardsLinkedList);
	            HistoryMenuFrame.setSize(580, 376);
	            HistoryMenuFrame.setLocationRelativeTo(null);
	            HistoryMenuFrame.setResizable(false);
	            HistoryMenuFrame.setVisible(true);
	        }
            public void mouseEntered(MouseEvent e) {
				HistoryLabel.setBackground(new Color(211,211,211));
				HistoryLabel.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	HistoryLabel.setBackground(new Color(128, 0, 255));
            	HistoryLabel.setOpaque(true);
            }
		});
		contentPane.add(HistoryLabel);
		
		JLabel MonitoringLabel = new JLabel("Monitoring");
		MonitoringLabel.setBounds(440, 290, 122, 47);
		MonitoringLabel.setOpaque(true);
		MonitoringLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MonitoringLabel.setForeground(Color.WHITE);
		MonitoringLabel.setBackground(new Color(128, 0, 255));
		MonitoringLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	            dispose();  
	            MonitoringMenuFrame = new MonitoringMenu();
	            MonitoringMenuFrame.setSize(580, 376);
	            MonitoringMenuFrame.setLocationRelativeTo(null);
	            MonitoringMenuFrame.setResizable(false);
	            MonitoringMenuFrame.setVisible(true);
	        }
			@Override
            public void mouseEntered(MouseEvent e) {
				MonitoringLabel.setBackground(new Color(211,211,211));
				MonitoringLabel.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	MonitoringLabel.setBackground(new Color(128, 0, 255));
            	MonitoringLabel.setOpaque(true);
            }
		});
		
		JLabel HourglassLabel = new JLabel("");
		HourglassLabel.setBounds(186, 231, 48, 48);
		HourglassLabel.setIcon(new ImageIcon("C:\\Users\\Win10\\Downloads\\hourglass_3805.png"));
		contentPane.add(HourglassLabel);
		
		JLabel BudgetLabel = new JLabel("Budget");
		BudgetLabel.setBounds(293, 290, 122, 47);
		BudgetLabel.setOpaque(true);
		BudgetLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BudgetLabel.setForeground(Color.WHITE);
		BudgetLabel.setBackground(new Color(128, 0, 255));
		BudgetLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	            dispose(); 
	            BudgetMenu BudgetMenuFrame = new BudgetMenu();
	            BudgetMenuFrame.setSize(580, 376);
	            BudgetMenuFrame.setLocationRelativeTo(null);
	            BudgetMenuFrame.setResizable(false);
	            BudgetMenuFrame.setVisible(true);
	        }
            public void mouseEntered(MouseEvent e) {
				BudgetLabel.setBackground(new Color(211,211,211));
				BudgetLabel.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	BudgetLabel.setBackground(new Color(128, 0, 255));
            	BudgetLabel.setOpaque(true);
            }
		});
		contentPane.add(BudgetLabel);
		
		JLabel MeterLabel = new JLabel("");
		MeterLabel.setBounds(325, 231, 48, 48);
		MeterLabel.setIcon(new ImageIcon("C:\\Users\\Win10\\Downloads\\speed-meter_icon-icons.com_54251.png"));
		contentPane.add(MeterLabel);
		
		JLabel spyLabel = new JLabel("");
		spyLabel.setBounds(482, 231, 48, 48);
		spyLabel.setIcon(new ImageIcon("C:\\Users\\Win10\\Downloads\\spy_96975.png"));
		contentPane.add(spyLabel);
		contentPane.add(MonitoringLabel);
		
		lblTransactionHistory = new JLabel("");
		lblTransactionHistory.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblTransactionHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransactionHistory.setOpaque(true);
		lblTransactionHistory.setForeground(Color.WHITE);
		lblTransactionHistory.setBackground(new Color(128, 0, 255));
		lblTransactionHistory.setBounds(149, 177, 122, 51);
		contentPane.add(lblTransactionHistory);
		updateEarliestTransactionDate();
		
		lblReminderPayments = new JLabel("View Reminder payments");
		lblReminderPayments.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblReminderPayments.setOpaque(true);
		lblReminderPayments.setHorizontalAlignment(SwingConstants.CENTER);
		lblReminderPayments.setForeground(Color.WHITE);
		lblReminderPayments.setBackground(new Color(128, 0, 255));
		lblReminderPayments.setBounds(440, 177, 122, 51);
		contentPane.add(lblReminderPayments);
		
		selectCurrency = new JComboBox();
		selectCurrency.setForeground(new Color(255, 255, 255));
        selectCurrency.setBackground(new Color(128, 0, 255));
		selectCurrency.setBounds(397, 16, 39, 35);
		for (int i=0,count=0;i<currencies.length;i++) {
        	selectCurrency.addItem(currencies[count++]);
        }
		contentPane.add(selectCurrency);
		selectCurrency.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        updateTotalCashLabel();
		    }
		});	
		updateTotalCashLabel();
	}
	private void updateTotalCashLabel() {
	    String selectedCurrencySymbol = (String) selectCurrency.getSelectedItem();
	    List<Card> loadedCards = Card.loadCardsFromFile();
	    double totalCash = calculateTotalCash(loadedCards);
	    double convertedTotalCash = convertToSelectedCurrency(totalCash, selectedCurrencySymbol);
	    Formatter formatter = new Formatter();
        formatter.format("%.2f", convertedTotalCash);
	    totalCashLabel.setText("Total Cash: " + selectedCurrencySymbol + " " + formatter);
	}

	private double calculateTotalCash(List<Card> loadedCards) {
	    double totalCash = 0.0;
	    
	    for (Card card : loadedCards) {
	        for (Transaction transaction : card.getTransactionHistory()) {
	            totalCash += transaction.getAmount();
	        }
	    }	    
	    return totalCash;
	}
	public double convertToSelectedCurrency(double amount, String selectedCurrencySymbol) {
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

	private void updateEarliestTransactionDate() {
        List<Card> loadedCards = Card.loadCardsFromFile();
        Date earliestTransactionDate = findEarliestTransactionDate(loadedCards);

        if (earliestTransactionDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(earliestTransactionDate);
            lblTransactionHistory.setText("Last transaction:"+formattedDate);
        } else {
            lblTransactionHistory.setText("No transactions found");
        }
    }

    private Date findEarliestTransactionDate(List<Card> cards) {
        Date earliestDate = null;

        for (Card card : cards) {
            for (Transaction transaction : card.getTransactionHistory()) {
                Date transactionDate = transaction.getTransactionDate();
                if (earliestDate == null || transactionDate.before(earliestDate)) {
                    earliestDate = transactionDate;
                }
            }
        }

        return earliestDate;
    }
}
