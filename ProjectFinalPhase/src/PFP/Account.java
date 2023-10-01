package PFP;

/**
 * Project Final Phase (Account Management System) for ABC Bank.
 * Team [
 * Omar Albatran : 1221344
 * Owais Malash  : 1220989
 * ]
 *
 * Section: 2
 * Lab Section: 3
 * **/

// Account class which will contain all the account's information and properties.
public class Account {
    // Declaring all possible information of an account as states(fields).
    private int id;
    private String name;
    private long phoneNumber;
    private char accountType;
    private double balance;
    // A constructor which will accept a bunch of arguments.
    public Account(int ID, String Name, long PhoneNumber, char AccountType, double Balance) {
        // Setting the Account states(fields) to the passed ones from the arguments.
        this.id = ID;
        this.name = Name;
        this.phoneNumber = PhoneNumber;
        this.accountType = AccountType;
        this.balance = Balance;
    }
    // This method will add a specific amount to the balance.
    public void addAmount(double amount) {
        this.setBalance(this.balance + amount); // Increment the account balance by the requested amount.
    }
    // This method will withdraw a specific amount from the balance.
    public boolean withdrawAmount(double amount) {
        if (this.accountType == 's' && amount > 500) return false; // failed to withdraw because saving accounts can't withdraw more than 500 at a time.
        if (this.balance < amount) return false; // failed to withdraw because the account doesn't have the requested amount.
        this.setBalance(this.balance - amount); // Decrement the account balance by the requested amount.
        return true;
    }

    // Setters and getter for the fields of the Account:
    public int getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public long getPhoneNumber() {
        return phoneNumber;
    }
    public char getAccountType() {
        return accountType;
    }
    public void setAccountType(char accountType) {
        this.accountType = accountType;
    }
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setName(String name) {
        this.name = name;
    }
    // This method will create a String of all the account information.
    public String toString() {
        return "Account[ ID=" + id + ", Name='" + name + "', PhoneNumber=" + phoneNumber + ", AccountType=" + accountType + ", Balance=" + balance + " ]";
    }
}
