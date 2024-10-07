package p2;

import java.awt.Color;
import java.awt.Font;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class BudgetMenu extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterAmountOfMoney;
	private JTextField txtEnterBudgetName;
	private JTextField RemoveBudgettxtField;
	private int currentBudgetIndex = 0;
	private JLabel RemainingDaysLabel;
	private String[] currencies = {"£","€‎","$"};
	private JComboBox selectCurrency;
	private JLabel DisplayMoneyLabel;
	
	public BudgetMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JLabel MonthlyBudgetLabel = new JLabel("Monthly Budget");
		MonthlyBudgetLabel.setOpaque(true);
		MonthlyBudgetLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MonthlyBudgetLabel.setBackground(new Color(128, 0, 255));
		MonthlyBudgetLabel.setForeground(new Color(255, 255, 255));
		MonthlyBudgetLabel.setBounds(205, 42, 162, 47);
		contentPane.add(MonthlyBudgetLabel);
		
		JButton AddBudgetButton = new JButton("New Budget");
		AddBudgetButton.setForeground(new Color(255, 255, 255));
		AddBudgetButton.setBackground(new Color(128, 0, 255));
		AddBudgetButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String budgetName = txtEnterBudgetName.getText();
		        String amountStr = txtEnterAmountOfMoney.getText();
		        
		        if (!budgetName.isEmpty() && !amountStr.isEmpty()) {
		            double amount = Double.parseDouble(amountStr);
		            String currentDate = getCurrentDate();

		            try {
		                BufferedWriter writer = new BufferedWriter(new FileWriter("Budgets.txt", true));
		                writer.write(budgetName + "," + amount + "," + currentDate + "\n");
		                writer.close();		             
		                txtEnterBudgetName.setText("Enter Budget name");
		                txtEnterAmountOfMoney.setText("Enter amount of money");
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		        updateRemainingDaysLabel();
		    }
		});
		AddBudgetButton.setBounds(423, 42, 141, 47);
		contentPane.add(AddBudgetButton);
		
		txtEnterAmountOfMoney = new JTextField();
		txtEnterAmountOfMoney.setBackground(new Color(128, 128, 128));
		txtEnterAmountOfMoney.setForeground(new Color(0, 0, 0));
		txtEnterAmountOfMoney.setText("Enter amount of money");
		txtEnterAmountOfMoney.setBounds(420, 136, 144, 25);
		contentPane.add(txtEnterAmountOfMoney);
		txtEnterAmountOfMoney.setColumns(10);
		
		JLabel NameOfBudgetLabel = new JLabel();
		NameOfBudgetLabel.setOpaque(true);
		NameOfBudgetLabel.setForeground(new Color(255, 255, 255));
		NameOfBudgetLabel.setBackground(new Color(128, 0, 255));
		NameOfBudgetLabel.setHorizontalAlignment(SwingConstants.CENTER);
		NameOfBudgetLabel.setBounds(205, 248, 162, 31);
		contentPane.add(NameOfBudgetLabel);

		try {
		    BufferedReader reader = new BufferedReader(new FileReader("Budgets.txt"));
		    String line;
		    if ((line = reader.readLine()) != null) {
		        String[] parts = line.split(",");
		        if (parts.length >= 1) {
		            String budgetName = parts[0];
		            NameOfBudgetLabel.setText(budgetName);
		        }
		    }
		    reader.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}

		
		JLabel ClockIcon = new JLabel("");
		ClockIcon.setIcon(new ImageIcon("C:\\Users\\Win10\\Downloads\\clock-with-white-face_icon-icons.com_72804.png"));
		ClockIcon.setBounds(48, 108, 64, 118);
		contentPane.add(ClockIcon);
		
		JButton DeleteBudgetButton = new JButton("Delete Budget");
		DeleteBudgetButton.setForeground(Color.WHITE);
		DeleteBudgetButton.setBackground(new Color(128, 0, 255));
		DeleteBudgetButton.setBounds(423, 195, 141, 47);
		DeleteBudgetButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String budgetToRemove = RemoveBudgettxtField.getText();

		        if (!budgetToRemove.isEmpty()) {
		            try {
		                File inputFile = new File("Budgets.txt");
		                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		                String currentLine;
		                StringBuilder newContent = new StringBuilder();

		                while ((currentLine = reader.readLine()) != null) {
		                    if (!currentLine.startsWith(budgetToRemove + ",")) {
		                        newContent.append(currentLine).append(System.getProperty("line.separator"));
		                    }
		                }

		                reader.close();
		                BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		                writer.write(newContent.toString());
		                writer.close();

		                RemoveBudgettxtField.setText("Enter Budget name");
		                System.out.println("Budget deleted successfully.");
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		        updateRemainingDaysLabel();
		    }
		    
		});
		contentPane.add(DeleteBudgetButton);
		
		
		
		txtEnterBudgetName = new JTextField();
		txtEnterBudgetName.setText("Enter Budget name");
		txtEnterBudgetName.setForeground(Color.BLACK);
		txtEnterBudgetName.setColumns(10);
		txtEnterBudgetName.setBackground(Color.GRAY);
		txtEnterBudgetName.setBounds(420, 100, 144, 25);
		contentPane.add(txtEnterBudgetName);
		
		RemoveBudgettxtField = new JTextField();
		RemoveBudgettxtField.setText("Enter Budget name");
		RemoveBudgettxtField.setForeground(Color.BLACK);
		RemoveBudgettxtField.setColumns(10);
		RemoveBudgettxtField.setBackground(Color.GRAY);
		RemoveBudgettxtField.setBounds(420, 265, 144, 25);
		contentPane.add(RemoveBudgettxtField);
		
		DisplayMoneyLabel = new JLabel("");
		DisplayMoneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		DisplayMoneyLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		DisplayMoneyLabel.setBounds(169, 111, 198, 99);
		contentPane.add(DisplayMoneyLabel);		
		
		JButton btnNextBudget = new JButton("Next Budget");
		btnNextBudget.setBackground(new Color(128, 0, 255));
		btnNextBudget.setForeground(new Color(255, 255, 255));
		btnNextBudget.setBounds(205, 290, 162, 23);
		contentPane.add(btnNextBudget);
		btnNextBudget.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        List<String> budgets = new ArrayList<>();
		        try {
		            BufferedReader reader = new BufferedReader(new FileReader("Budgets.txt"));
		            String line;
		            while ((line = reader.readLine()) != null) {
		                String[] parts = line.split(",");
		                if (parts.length >= 1) {
		                    budgets.add(parts[0]);
		                }
		            }
		            reader.close();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }

		        if (!budgets.isEmpty()) {
		            currentBudgetIndex = (currentBudgetIndex + 1) % budgets.size();
		            String selectedBudget = budgets.get(currentBudgetIndex);
		            NameOfBudgetLabel.setText(selectedBudget);

		            try {
		                BufferedReader reader = new BufferedReader(new FileReader("Budgets.txt"));
		                String line;
		                while ((line = reader.readLine()) != null) {
		                    String[] parts = line.split(",");
		                    if (parts.length >= 1 && parts[0].equals(selectedBudget)) {
		                        if (parts.length >= 2) {
		                            String money = parts[1];
		                            String selectedCurrencySymbol = (String) selectCurrency.getSelectedItem();
		        		            double amount = Double.parseDouble(money);
		        		            double convertedAmount = convertToSelectedCurrency(amount, selectedCurrencySymbol);
		        		            DisplayMoneyLabel.setText(selectedCurrencySymbol + " " + convertedAmount);	                     
		                        }
		                        break;
		                    }
		                }
		                reader.close();
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		RemainingDaysLabel = new JLabel("");
		RemainingDaysLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RemainingDaysLabel.setBounds(0, 211, 181, 31);
		contentPane.add(RemainingDaysLabel);
		
		selectCurrency = new JComboBox();
		selectCurrency.setForeground(new Color(255, 255, 255));
        selectCurrency.setBackground(new Color(128, 0, 255));
		selectCurrency.setBounds(342, 148, 39, 35);
		for (int i=0,count=0;i<currencies.length;i++) {
        	selectCurrency.addItem(currencies[count++]);
        }
		selectCurrency.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {		       
		    	updateDisplayMoneyLabel();
		    }
		});
		contentPane.add(selectCurrency);
		updateDisplayMoneyLabel();
		updateRemainingDaysLabel();
	}
	private String getCurrentDate() {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date currentDate = new Date();
	    return dateFormat.format(currentDate);
	}

	private void updateRemainingDaysLabel() {
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader("Budgets.txt"));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(",");
	            if (parts.length >= 3) {
	                String budgetName = parts[0];
	                String startDateStr = parts[2];
	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                Date startDate = dateFormat.parse(startDateStr);

	                long remainingDays = calculateRemainingDays(startDate);
	                RemainingDaysLabel.setText("Remaining Days: " + remainingDays + " days");
	            }
	        }
	        reader.close();
	    } catch (IOException | ParseException e) {
	        e.printStackTrace();
	    }
	}

	private long calculateRemainingDays(Date startDate) {
	    Calendar endDate = Calendar.getInstance();
	    endDate.setTime(startDate);
	    endDate.add(Calendar.DATE, 30);

	    Calendar today = Calendar.getInstance();
	    today.set(Calendar.HOUR_OF_DAY, 0);
	    today.set(Calendar.MINUTE, 0);
	    today.set(Calendar.SECOND, 0);
	    today.set(Calendar.MILLISECOND, 0);

	    long diffMillis = endDate.getTimeInMillis() - today.getTimeInMillis();
	    long remainingDays = TimeUnit.MILLISECONDS.toDays(diffMillis);
	    return Math.max(0, remainingDays);
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
	private void updateDisplayMoneyLabel() {
		String selectedCurrencySymbol = (String) selectCurrency.getSelectedItem();
	    double budgetAmount = readBudgetAmountFromDataSource();
	    double convertedAmount = convertToSelectedCurrency(budgetAmount, selectedCurrencySymbol);
	    Formatter formatter = new Formatter();
        formatter.format("%.2f", convertedAmount);
	    DisplayMoneyLabel.setText(selectedCurrencySymbol + " " + formatter);
	} 
	private double readBudgetAmountFromDataSource() {
	    double budgetAmount = 0.0;
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader("Budgets.txt"));
	        String line;
	        if ((line = reader.readLine()) != null) {
	            String[] parts = line.split(",");
	            if (parts.length >= 2) {
	                String money = parts[1];
	                budgetAmount = Double.parseDouble(money);
	            }
	        }
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return budgetAmount;
	}
}



