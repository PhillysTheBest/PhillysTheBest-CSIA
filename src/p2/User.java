package p2;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class User {
	// Private instance variables (fields) for encapsulation
	private String username;
    private String password;
    private String userID;
    private String email;
    private static int failedAttempts=0;
    private static User currentUser;
    // Constructor initialises the private fields
    public User(String username, String password, String userID,String email) {
        this.username = username;
        this.password = password;
        this.userID = userID;
        this.email=email;
    }
    // Getter methods for controlled access to private fields
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserID() {
        return userID;
    }
    public String getEmail() {
    	return email;
    }
    // Setter methods for controlled modification of fields
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
    public static int getFailedAttempts() {
    	//returns static attribute failedAttempts
        return failedAttempts;
    }
    public boolean isValidUser() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Accounts.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {  
                    String storedUsername = parts[0];
                    String storedPassword = parts[1];
                    String storedUserID = parts[2];     
                    // Check if the stored user credentials match the provided ones.
                    if (storedUsername.equals(username) && storedPassword.equals(password) && storedUserID.equals(userID)) {
                        reader.close();
                        return true;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void incrementFailedAttempts() {
        failedAttempts++;
    }

    
    public static void createAndWriteNewUser(String username, String password, String email) {
    	
        String userID = generateUserID();                
        String accountDetails = username + "," + password + "," + userID + "," + email;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Accounts.txt", true));
            writer.write(accountDetails);
            writer.newLine(); 
            writer.close();
            System.out.println("Account created successfully!");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Failed to create account.");
        }
    }

    private static String generateUserID() {
        Random random = new Random();
        int userID = random.nextInt(900000) + 100000; 
        return String.valueOf(userID);
    }
    public static User loadSavedLoginDetails(String username) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Accounts.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {  
                    String storedUsername = parts[0];
                    String storedPassword = parts[1];
                    String storedUserID = parts[2];
                    String storedEmail = parts[3];

                    if (storedUsername.equals(username)) {
                        reader.close();
                        return new User(storedUsername, storedPassword, storedUserID, storedEmail);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean updateAccountInfo(String oldUsername, String oldPassword, String newUsername, String newPassword) {
        List<String> updatedLines = new ArrayList<>();
        boolean accountUpdated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("Accounts.txt"))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split(",");
                String username = parts[0].trim();
                String password = parts[1].trim();

                if (username.equals(oldUsername) && password.equals(oldPassword)) {  
                    updatedLines.add(newUsername + "," + newPassword + "," + parts[2] + "," + parts[3]);
                    accountUpdated = true; 
                } else {       
                    updatedLines.add(currentLine);
                }
            }
      
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Accounts.txt"))) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();            
            accountUpdated = false; 
        }

        return accountUpdated;
    }

    public boolean deleteAccount(String filePath) {
        List<String> updatedLines = new ArrayList<>();
        boolean accountDeleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split(",");
                String username = parts[0].trim();
                String password = parts[1].trim();

                if (!username.equals(this.username) || !password.equals(this.password)) {
                    updatedLines.add(currentLine);
                } else {
                    accountDeleted = true; 
                }
            }
  
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
           
            accountDeleted = false;
        }

        return accountDeleted;
    }
}

