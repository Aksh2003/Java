import java.util.*;
abstract class Account {
    protected String name;
    protected String accountNumber;
    protected double balance;
    protected List<Transaction> transaction_history;

    public Account(String name, String accountNumber, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transaction_history = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transaction_history.add(new Transaction(new Date(), "Deposit", amount));
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transaction_history.add(new Transaction(new Date(), "Withdrawal", amount));
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void printTransactionHistory() {
        for (Transaction t : transaction_history) {
            System.out.println(t);
        }
    }

    public abstract void displayAccountDetails();
}

class SavingsAccount extends Account {
    private final double interest_rate = 0.05;

    public SavingsAccount(String name, String accountNumber, double balance) {
        super(name, accountNumber, balance);
    }

    public void calculateInterest() {
        balance += 50; 
        System.out.println(balance);
    }

    @Override
    public void displayAccountDetails() {
        System.out.println("Savings Account Details:");
        System.out.println("Name: " + name);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
        System.out.println("Interest Rate: " + interest_rate);
    }
}

class CurrentAccount extends Account {
    private final double min_balance = 1000.00;

    public CurrentAccount(String name, String accountNumber, double balance) {
        super(name, accountNumber, balance);
    }


    public void withdraw(double amount) {
        if (balance - amount >= min_balance ) {
            super.withdraw(amount);
        } else {
            System.out.println("Minimum balance requirement not met.");
        }
    }

   
    public void displayAccountDetails() {
        System.out.println("Current Account Details:");
        System.out.println("Name: " + name);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
        System.out.println("Minimum Balance Requirement: " + min_balance );
    }
}

class Transaction {
    private Date date;
    private String type;
    private double amount;

    public Transaction(Date date, String type, double amount) {
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

   
    public String toString() {
        return "Date: " + date + ", Type: " + type + ", Amount: " + amount;
    }
}

public class Banking {
    public static void main(String[] args) {
        SavingsAccount savingsAccount = new SavingsAccount("Person1", "12345", 7000.00);
        CurrentAccount currentAccount = new CurrentAccount("Person2", "56890", 8000.00);

       
        savingsAccount.deposit(5000);
        savingsAccount.withdraw(100);
        savingsAccount.calculateInterest();
        savingsAccount.displayAccountDetails();
        savingsAccount.printTransactionHistory();

        System.out.println();

       
        currentAccount.deposit(1000);
        currentAccount.withdraw(1500);
        currentAccount.withdraw(3000); 
        currentAccount.displayAccountDetails();
        currentAccount.printTransactionHistory();
    }
}