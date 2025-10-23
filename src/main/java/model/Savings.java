package model;

import static util.FormatUtil.*;

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

    public StringBuilder printSummary() {
        StringBuilder output = new StringBuilder();
        output.append("  Savings balance: $" + fmt(balance) + "\n");
        return output;
    }

    // Getter for testing
    public double getBalance() {
        return balance;
    }
}