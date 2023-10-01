package PFP;

/**
 * Project Phase 2 (Account Management System) for ABC Bank.
 * Team [
 * Omar Albatran : 1221344
 * Owais Malash  : 1220989
 * ]
 *
 * Section: 2
 * Lab Section: 3
 * **/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

// Bank class, which will contain all the methods and will handle all services execution.
public class Bank {
    private final String databaseName = "Account.txt";
    private Account[] accounts; // declaring an array of accounts
    private int accountsExistsNumber; // integer for counting the accounts exists
    private Scanner input = new Scanner(System.in); // Initialize the scanner to read from the keyboard.
    public Bank() { // This constructor will initialize the accounts array with 100 account size.
        this.accounts = new Account[100];
        this.accountsExistsNumber = 0; // initializing the account number to zero
        if (this.uploadDataFile()) {
            System.out.println("Imported all accounts successfully.");
        }
    }
    // This method will print out the main menu of operations to the screen.
    public void displayMainMenu() {
        System.out.println("****** Main Menu ******");
        System.out.println("1. Add Account");
        System.out.println("2. View All Accounts");
        System.out.println("3. Add Amount");
        System.out.println("4. Withdraw Amount");
        System.out.println("5. View Account Details");
        System.out.println("6. Modify Account");
        System.out.println("7. Close an Account");
        System.out.println("8. Exit");
    }
    // This method will be handling the operation of displaying all the accounts existing in the bank.
    public void displayAccounts() {
        if(this.accountsExistsNumber == 0) { // Checks if there is no accounts and alert the user if so.
            System.out.println("There is no accounts in the bank currently.");
        } else {
            for(int i = 0; i < this.accountsExistsNumber; i++) { // iterate through all the accounts and display them.
                System.out.println(this.accounts[i].toString());
            }
        }
    }
    // This method will be handling the operation of adding a new account to the bank.
    public boolean addAccount(Account account) {
        if (this.accounts.length == this.accountsExistsNumber) { // Checking if the array is full.
            Account[] newAccounts = new Account[this.accounts.length*2]; // creating new accounts array with sized doubled.
            for (int i = 0; i < this.accountsExistsNumber; i++) { // copy the accounts from the old array to the new one.
                newAccounts[i] = this.accounts[i];
            }
            this.accounts = newAccounts; // Replace the old array with the new one.
        }
        if (this.isTheIDTaken(account.getID())) return false; // The id is already been taking.
        this.accounts[this.accountsExistsNumber] = account; // Adding the requested account to the array.
        // since the new account has been added. the number of existing account will increase.
        this.accountsExistsNumber++;
        return true;
    }
    // This method will handle the operation of showing the details of a certain account.
    public Account viewAccountDetails(int choice) {
        // 1: ID
        // 2: Account holder name
        // 3: Part of the name
        Account account = null;
        switch (choice) { // This switch statement will handle all choices numbers.
            case 1:
                System.out.print("Enter the ID: "); // Asking the user to enter the account id.
                int id = this.input.nextInt(); // reads from the user the account id.
                account = this.getAccountById(id); // Getting the account by using the getAccountById method.
                break;
            case 2:
                System.out.print("Enter the name: "); // Asking the user to enter the account name.
                String name = this.input.next(); // reads from the user the account name.
                account = this.getAccountByName(name, false); // Getting the account by using the getAccountByName method.
                break;
            case 3:
                System.out.print("Enter part of the name: "); // Asking the user to enter a part of the account name.
                String ptName = this.input.next(); // reads from the user the account name.
                account = this.getAccountByName(ptName, true); // Getting the account by using the getAccountByName method.
                break;
        }
        if (account == null) return null; // Return null if the account is not found.

        // Printing all the account's information.
        System.out.println("***Account Details : ");
        System.out.println("Account Owner Name : " + account.getName());
        System.out.println("Account ID : " + account.getID());
        System.out.println("Account owner Number : " + account.getPhoneNumber());
        System.out.println("Account Type : " + (account.getAccountType() == 's' ? "Saving" : "Others"));
        System.out.println("Account Balance : " + account.getBalance()+"$");

        return account; // Return the selected account.
    }
    // This method will handle the operation of changing the data for an existing account.
    public void modifyAccountDetails(int choice){
        // 1: ID
        // 2: Account holder name
        // 3: Part of the name
        Account account = null;
        switch (choice) { // This switch statement will handle all choices numbers.
            case 1:
                System.out.print("Enter the ID: "); // Asking the user to enter the account id.
                int id = this.input.nextInt(); // reads from the user the account id.
                account = this.getAccountById(id); // Getting the account by using the getAccountById method.
                break;
            case 2:
                System.out.print("Enter the name: "); // Asking the user to enter the account name.
                String name = this.input.next(); // reads from the user the account name.
                account = this.getAccountByName(name, false); // Getting the account by using the getAccountByName method.
                break;
            case 3:
                System.out.print("Enter part of the name: "); // Asking the user to enter a part of the account name.
                String ptName = this.input.next(); // reads from the user the account name.
                account = this.getAccountByName(ptName, true); // Getting the account by using the getAccountByName method.
                break;
        }
        if(account == null) {
            System.out.println("Account is NOT FOUND!");
            return;
        }

        System.out.println("Enter new modified account information: ");
        System.out.print("Enter account owner name: ");
        String Name = input.next();
        System.out.print("Enter account owner phone number: ");
        long PhoneNumber = input.nextLong();
        System.out.print("Enter account type ( Saving(s) or Others(o) ): ");
        char AccountType = input.next().charAt(0); // here user enters the Type of the account
        // here we are checking if the type is valid
        while(true) {
            if(AccountType == 'o' || AccountType == 's') { // Valid type and it is Others Or Saving.
                break; // Breaking out from the Checking loop.
            } else { // The type that user enters was not valid
                System.out.println("[Non Valid Input]: Try Again!");
                System.out.print("Enter account type ( Saving(s) or Others(o) ): "); // asking the user to enter the value again.
                AccountType = input.next().charAt(0); // There is no break here so the loop will check the value again.
            }
        }
        System.out.print("Enter the initial account balance in [ $ ]: ");
        double Balance = input.nextDouble();

        account.setName(Name);
        account.setPhoneNumber(PhoneNumber);
        account.setAccountType(AccountType);
        account.setBalance(Balance);

        System.out.println("The account has been modified.");
    }
    // This method will handle the operation of closing(deleting) an account from the bank.
    public boolean closeAccount(Account account) {
        if (account == null) return false;

        for(int i = 0; i < this.accountsExistsNumber; i++) { // iterate through all the accounts.
            Account iThAccount = this.accounts[i]; // A variable which will contain each iteration account.
            if(iThAccount.getID() == account.getID()) { // Checking if the i-th account is the requested account by checking the ids.
                // Delete the account from the bank array by changing the i-th position with 'null'.
                this.accounts[i] = null;

                for (int j = i; j < this.accountsExistsNumber; j++) { // iterate through all the accounts from the current position to the end of the accounts.
                    // In this line we are shifting all the accounts to replace the i-th position which were deleted.
                    this.accounts[j] = this.accounts[j+1];
                }
                // decrementing the accounts count since one has been deleted.
                this.accountsExistsNumber--;
                return true; // Return true to notify the operation was successful.
            }
        }

        return false;
    }
    // This method will handle the operation of importing the accounts from the data file.
    public boolean uploadDataFile() {
        File dataFile = new File(this.databaseName); // declaring the file instance for the data file.
        if (!dataFile.exists()) { // Checking if the file exists, and behave accordingly.
            System.out.println("[ CAUTION ]: The data file doesn't exist."); // Notify the user that the data file doesn't exist.
            return false;
        }
        try (Scanner fs = new Scanner(dataFile)) { // declaring a file scanner inside the try statement for automatically closing.
            int count = 0; // for the count of the account in the data file.
            Account[] accountsFromDataFile = new Account[100]; // initialize the accounts array with 100 account size.
            while (fs.hasNext()) { // keep fetching line after line until there is no more lines.
                String record = fs.nextLine(); // get the record (line) for the account
                Scanner stepper = new Scanner(record); // declaring a scanner for the record
                stepper.useDelimiter(" "); // using ' ' as delimiter for the record.
                int ID = stepper.nextInt(); // getting the id
                String Name = stepper.next(); // getting the name
                long PhoneNumber = stepper.nextLong(); // getting the phone number
                char AccountType = stepper.next().charAt(0); // getting the account type
                double Balance = stepper.nextDouble(); // getting the balance
                if (count == accountsFromDataFile.length) { // Checking if the array is full.
                    Account[] newAccounts = new Account[accountsFromDataFile.length*2]; // creating new accounts array with sized doubled.
                    for (int i = 0; i < count; i++) { // copy the accounts from the old array to the new one.
                        newAccounts[i] = accountsFromDataFile[i];
                    }
                    accountsFromDataFile = newAccounts; // Replace the old array with the new one.
                }
                // Creating a new instance for the account record in the memory and assign it to the account array.
                accountsFromDataFile[count] = new Account(ID, Name, PhoneNumber, AccountType, Balance);
                count++; // since the new account has been added. the number of existing account will increase.
                stepper.close(); // closing the record scanner.
            }
            this.accountsExistsNumber = count; // set the number of the accounts to that read from the data file.
            this.accounts = accountsFromDataFile; // set the accounts array to that created via the information from the data file.
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("[ CAUTION ]: Couldn't open the data file.");
            return false;
        } catch (InputMismatchException e) {
            System.out.println("[ Error ]: There is a bad record in the data file.");
            return false;
        }
    }
    // This method will handle the operation of storing the accounts to the data file.
    public boolean updateDataFile() {
        File dataFile = new File(this.databaseName); // declaring the file instance for the data file.
        if (!dataFile.exists()) { // Checking if the file exists, and behave accordingly.
            System.out.println("[ CAUTION ]: The data file doesn't exist.");
            return false;
        }
        try (PrintWriter fw = new PrintWriter(dataFile)) { // declaring a file writer inside the try statement for automatically closing.
            for (int i = 0; i < this.accountsExistsNumber; i++) { // iterate through all the accounts.
                Account iThAccount = this.accounts[i]; // getting the account at index [i]
                fw.println(
                        iThAccount.getID() + " " + iThAccount.getName() + " " + iThAccount.getPhoneNumber() + " " + iThAccount.getAccountType() + " " + iThAccount.getBalance()
                ); // writing account data in its own line separated by ' '
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("[ CAUTION ]: Couldn't open the data file.");
            return false;
        }
    }
    // This private method job is to check if specific id is taken or not.
    private boolean isTheIDTaken(int id) {
        for (int i = 0; i < this.accountsExistsNumber; i++) {
            if (this.accounts[i].getID() == id) {
                return true;
            }
        }
        return false;
    }
    // This method job is to get an account by id if the account doesn't exist it will return null.
    public Account getAccountById(int accountID) {
        Account account = null; // declaring a variable to check if the account is found or not.
        for(int i = 0; i < this.accountsExistsNumber; i++) { // iterate through all the accounts.
            Account iThAccount = this.accounts[i]; // A variable which will contain each iteration account.
            if(iThAccount.getID() == accountID) { // Checking if the i-th account is the requested account by checking the ids.
                account = iThAccount; // Change the account to return it back to the user.
                break;
            }
        }

        return account;
    }
    // This method job is to get an account by name or part of the name. if the account doesn't exist it will return null.
    public Account getAccountByName(String name, boolean checkParts) {
        for (int i = 0; i < this.accountsExistsNumber; i++) {
            Account iThAccount = this.accounts[i];
            if (iThAccount.getName().equalsIgnoreCase(name)) {
                return iThAccount;
            } else if (checkParts) {
                // Checking if the account name is the same as the requested one or not.
                if (
                        iThAccount.getName().toLowerCase().indexOf(name.toLowerCase()) != -1 // this condition will check if the account name have a substring of the requested part name if not will return -1 and that indicates that there is no match.
                ) {
                    return iThAccount;
                }
            }
        }
        return null;
    }
    // This method will convert the bank object to a string.
    public String toString() {
        return "Bank [Accounts=" + Arrays.toString(this.accounts) + "]";
    }
}