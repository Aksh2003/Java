import java.util.*;

public class Bank<T extends Account> {
    private Map<Integer, T> accounts;

    public Bank() {
        this.accounts = new HashMap<>();
    }

    public void add(T account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public void remove(int accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            accounts.remove(accountNumber);
        } else {
            System.out.println("Account not found: " + accountNumber);
        }
    }

    public void display(int accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            T account = accounts.get(accountNumber);
            System.out.println(account);
        } else {
            System.out.println("Account not found: " + accountNumber);
        }
    }

    public static void main(String[] args) {
        Savings s1 = new Savings(12, "person1", 2000.00, 0.05);
        Bank<Savings> b1 = new Bank<>();
        b1.add(s1);
        b1.display(12);
        b1.remove(12);
        b1.display(12);
    }
}

class Account {
    protected int accountNumber;
    protected String owner;
    protected double balance;

    public Account(int accountNumber, String owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public String toString() {
        return "Account : " + accountNumber + " " + owner + " " + balance;
    }
}

class Savings extends Account {
    private final double interest;

    public Savings(int accountNumber, String owner, double balance, double interest) {
        super(accountNumber, owner, balance);
        this.interest = interest;
    }

    public double getInterest() {
        return interest;
    }

    public String toString() {
        return super.toString() + " interest rate: " + interest;
    }
}
