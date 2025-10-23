package bank.model;

import static bank.util.FormatUtil.fmt;

public class Savings {
    private double balance = 0.0;

    public void deposit(double amt) {
        balance += amt;
    }

    public boolean withdraw(double amt) {
        if (amt > balance) return false;
        balance -= amt;
        return true;
    }

    public void applyMonthlyInterest(double monthlyRate) {
        balance += balance * monthlyRate;
        balance = Math.round(balance * 100) / 100.0;
    }

    public void printSummary() {
        System.out.println("  Savings balance: $" + fmt(balance));
    }

    // Getter for testing
    public double getBalance() {
        return balance;
    }
}