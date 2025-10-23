package model;

import static util.FormatUtil.*;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private static final int MAX_LOANS = 3;
    
    private int acct;
    private String name;
    private Savings savings = new Savings();
    private List<Loan> loans = new ArrayList<>();
    private List<String> activity = new ArrayList<>();
    private int nextLoanId = 1; 

    public Customer(int acct, String name) {
        this.acct = acct;
        this.name = name;
    }

    public void deposit(double amt) {
        savings.deposit(amt);
        activity.add("Deposited $" + fmt(amt));
    }

    public void withdraw(double amt) {
        if (savings.withdraw(amt))
            activity.add("Withdrew $" + fmt(amt));
        else
            activity.add("Failed withdrawal $" + fmt(amt));
    }

    public int openLoan(double amt, double rate) {
        if (loans.size() >= MAX_LOANS) {
            System.out.println("You already have " + MAX_LOANS + " loans!");
            return -1;
        }
        Loan loan = new Loan(nextLoanId++, amt, rate); 
        loans.add(loan);
        activity.add("Opened loan #" + loan.getId() + " for $" + fmt(amt));
        return loan.getId();
    }

    public void makeLoanPayment(int id, double amt) {
        Loan loan = null;
        for (Loan l : loans) {
            if (l.getId() == id) {
                loan = l;
                break;
            }
        }
        
        if (loan == null) {
            System.out.println("Loan not found.");
            return;
        }
        
        loan.makePayment(amt);
        activity.add("Paid $" + fmt(amt) + " on loan #" + id);
        
        if (loan.isClosed()) {
            activity.add("Loan #" + id + " closed.");
            loans.remove(loan);
        }
    }

    public void printLoans() {
        if (loans.isEmpty()) {
            System.out.println("No active loans.");
            return;
        }
        for (Loan l : loans) l.printSummary();
    }

    public void processMonthly(double savRate, double loanRate) {
        savings.applyMonthlyInterest(savRate);
        for (Loan l : loans) l.applyMonthlyInterest(loanRate);
    }

    public void printStatement() {
        System.out.println("\nCustomer: " + name + " (Account #" + acct + ")");
        savings.printSummary();
        if (loans.isEmpty()) {
            System.out.println("  No active loans.");
        } else {
            for (Loan l : loans) l.printSummary();
        }
        if (!activity.isEmpty()) {
            System.out.println("Recent Activity:");
            for (String s : activity) System.out.println("  " + s);
        }
        activity.clear();
    }

    // Getters for testing
    public int getLoanCount() {
        return loans.size();
    }

    public double getSavingsBalance() {
        return savings.getBalance();
    }

    public List<Loan> getLoans() {
        return new ArrayList<>(loans);
    }

    public String getName() {
        return name;
    }
}