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

import java.util.Scanner;

// The Driver class which will do the main execution.
public class Driver {
    public static void main(String[] args) {
        // Initialize the scanner to read from the keyboard.
        Scanner input = new Scanner(System.in);
        // Create a new Instance from the Bank class.
        Bank ABC_Bank = new Bank();
        int selectedOperation = 0; // Declaring a new variable which will handle the operation number.
        while(selectedOperation != 8) { // This loop will continue iterating until the user enter operation 8 (which will end the program)
            // Now using the 'displayMainMenu' method to display all operations.
            ABC_Bank.displayMainMenu();
            System.out.print("Please Select an Operation (1-8): "); // Ask the user to enter an operation number.
            selectedOperation = input.nextInt(); // update the selectedOperation to the new operation number.
            switch (selectedOperation) {  // This switch statement will handle all operation numbers.
                case 1: // This case is responsible for adding a new account to the bank.
                    System.out.println("Enter the account details: ");
                    System.out.print("Enter a new account id: "); // Asking the user to enter a new account id.
                    int ID = input.nextInt(); // reads from the user the account id.
                    while(ID > 9999 || ID < 1) { // This loop job is to keep asking the user to enter an id if and only if the id he/she entered is more the 4 digit length or less than 1
                        System.out.println("!! The id number cannot be more than 4 digit number or less than 1 !!");
                        System.out.print("Please enter the Account ID Again : ");
                        ID = input.nextInt();
                    }
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

                    Account newAccountData = new Account(ID, Name, PhoneNumber, AccountType , Balance); // Creating a new instance for the account.
                    boolean addingStatus = ABC_Bank.addAccount(newAccountData); // 'addAccount' method will handle the adding operation.

                    if (addingStatus) {
                        System.out.println("The account has been added successfully."); // Notify the user that this operation is done.
                    } else {
                        System.out.println("[Failed Addition]: The ID number you provided is already taken by another account!!"); // Notify the user that this operation failed.
                    }
                    break;
                case 2: // This case is responsible for viewing all current accounts in the bank.
                    ABC_Bank.displayAccounts(); // 'displayAccounts' method will print out all needed data.
                    break;
                case 3: // This case is responsible for adding an amount to an account.
                    System.out.print("Enter Account ID: "); // Asking the user to enter a target account id.
                    int targetAccountID = input.nextInt(); // reads from the user the account id.
                    Account targetAccount = ABC_Bank.getAccountById(targetAccountID); // Getting the account by using the getAccountById method.
                    if (targetAccount == null) { // Checking if the accounts doesn't exist.
                        System.out.println("Account is NOT FOUND!");
                        return;
                    }
                    System.out.print("Enter the amount you wanna add: "); // Asking the user to enter the wanted amount to add.
                    double amountToAdd = input.nextDouble(); // reads from the user the amount.
                    targetAccount.addAmount(amountToAdd); // Increment the account balance by the requested amount.
                    System.out.println("A [" + amountToAdd + "$] has been added to the account."); // Notify the user that this operation is done.
                    break;
                case 4: // This case is responsible for withdrawing an amount from an account.
                    System.out.print("Enter Account ID: "); // Asking the user to enter a target account id.
                    int tAccountID = input.nextInt(); // reads from the user the account id.
                    Account tAccount = ABC_Bank.getAccountById(tAccountID); // Getting the account by using the getAccountById method.
                    if (tAccount == null) { // Checking if the accounts doesn't exist.
                        System.out.println("Account is NOT FOUND!");
                        return;
                    }
                    System.out.print("Enter the amount you wanna withdraw from the account: "); // Asking the user to enter the wanted amount to add.
                    int amountToWithdraw = input.nextInt(); // reads from the user the amount.
                    if (tAccount.getBalance() < amountToWithdraw) {
                        System.out.println("[Withdrawal is not possible]: saving accounts can withdraw 500 or less at a time.the account doesn't have the requested amount.");
                        return; // failed to withdraw because the account doesn't have the requested amount.
                    }
                    boolean withdrawStatus = tAccount.withdrawAmount(amountToWithdraw); // withdraw the requested amount from the account balance.
                    if (withdrawStatus) {
                        System.out.println("A [" + amountToWithdraw + "$] has been withdraw from the account."); // Notify the user that this operation is done.
                    } else {
                        System.out.println("[Withdrawal is not possible]: saving accounts can withdraw 500 or less at a time."); // Notify the user that this operation failed.
                    }
                    break;
                case 5: // This case is responsible for viewing a single account information.
                    int findingChoice = 0; // A variable holds the choice of the user.
                    while (findingChoice == 0) { // for keep asking the user to enter a valid choice.
                        System.out.println("Searches for account details based on the given choice:\n" +
                                "1: ID\n" +
                                "2: Account holder name\n" +
                                "3: Part of the name"); // Displays all valid choices.
                        System.out.print("Enter your choice: "); // Asking the user to enter their choice.
                        int tempChoice = input.nextInt();
                        if (tempChoice == 1 || tempChoice == 2 || tempChoice == 3){
                            findingChoice = tempChoice;
                        } else {
                            System.out.println("Invalid Choice!");
                        }
                    }

                    // Using 'viewAccountDetails' the account information will be displayed.
                    Account selectedAccount = ABC_Bank.viewAccountDetails(findingChoice);

                    if (selectedAccount == null) System.out.println("Account is NOT FOUND!");
                    break;
                case 6: // This case is responsible for modifying an account.
                    int choice = 0; // A variable holds the choice of the user.
                    while (choice == 0) { // for keep asking the user to enter a valid choice.
                        System.out.println("Searches for account details based on the given choice:\n" +
                                "1: ID\n" +
                                "2: Account holder name\n" +
                                "3: Part of the name"); // Displays all valid choices.
                        System.out.print("Enter your choice: "); // Asking the user to enter their choice.
                        int tempChoice = input.nextInt();
                        if (tempChoice == 1 || tempChoice == 2 || tempChoice == 3){
                            choice = tempChoice;
                        } else {
                            System.out.println("Invalid Choice!");
                        }
                    }
                    // Using 'modifyAccountDetails' the account information will be modified.
                    ABC_Bank.modifyAccountDetails(choice);
                    break;
                case 7: // This case is responsible for closing an existing account.
                    System.out.print("Enter Account ID: "); // Asking the user to enter a target account id.
                    int accountID = input.nextInt(); // reads from the user the account id.
                    Account accountToClose = ABC_Bank.getAccountById(accountID); // Getting the account by using the getAccountById method.
                    boolean closingStatus =  ABC_Bank.closeAccount(accountToClose); // 'closeAccount' method will close the account.

                    if (closingStatus) {
                        System.out.println("The account with id [" + accountID + "] has been closed.");  // Notify the user that this operation is done.
                    } else {
                        System.out.println("Account is NOT FOUND!"); // Notify the user that this operation failed.
                    }
                    break;
                case 8: // This case is responsible for handling the exit of the service.
                    if (ABC_Bank.updateDataFile()) { // Using 'updateDataFile' to save all the changes in the accounts to the file. and returns true if completed without any errors.
                        System.out.println("All accounts data have been saved.");  // Notify the user that all data have been stored.
                    }
                    System.out.println("Service Done!");  // Notify the user that this operation is done.
                    break;
                default: // The default case will notify the user that an invalid operation number has been entered.
                    System.out.println("Invalid Operation Number, Try Again!");
            }
        }
    }
}
