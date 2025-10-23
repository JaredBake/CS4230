package model;

import static util.FormatUtil.*;

public class Loan {
    private static final double LATE_FEE = 50.0;
    private static final double MIN_PAYMENT_FLOOR = 10.0;
    private static final double PRINCIPAL_PERCENTAGE = 0.01;
    
    private int id;
    private double principal;
    private double balance;
    private double annualRate;
    private boolean delinquent = false;
    private double lastPayment = 0.0;
    private boolean isFirstMonth = true;  // Track first month to avoid incorrect late fee

    public Loan(int id, double amount, double annualRate) {
        this.id = id;
        this.principal = amount;
        this.balance = amount;
        this.annualRate = annualRate;
    }

    public int getId() { 
        return id; 
    }

    public void makePayment(double amt) {
        lastPayment = amt;
        if (amt > balance) amt = balance;
        balance -= amt;
        if (balance < 0.005) balance = 0;
    }

    public void applyMonthlyInterest(double monthlyRate) {
        if (balance <= 0) return;

        double interest = balance * monthlyRate;
        balance += interest;
        double minPay = getMinimumPayment(interest);
        
        // Only check for late payment after the first month
        if (!isFirstMonth) {
            if (lastPayment < minPay) {
                balance += LATE_FEE;
                delinquent = true;
            } else {
                delinquent = false;
            }
        }
        
        isFirstMonth = false;  // After first month, start checking payments
        balance = Math.round(balance * 100) / 100.0;
        lastPayment = 0;
    }

    private double getMinimumPayment(double interest) {
        double baseMin = Math.max(interest + PRINCIPAL_PERCENTAGE * principal, MIN_PAYMENT_FLOOR);
        return delinquent ? baseMin + LATE_FEE : baseMin;
    }

    public boolean isClosed() { 
        return balance <= 0; 
    }

    public void printSummary() {
        System.out.println("  Loan #" + id + " balance: $" + fmt(balance)
                + (delinquent ? " (Delinquent)" : ""));
        
        // Show minimum payment due (requirement #7)
        if (balance > 0) {
            double nextInterest = balance * (annualRate / 12);
            double minPaymentDue = getMinimumPayment(nextInterest);
            System.out.println("    Minimum payment due: $" + fmt(minPaymentDue));
        }
    }

    // Getters for testing
    public double getBalance() {
        return balance;
    }

    public boolean isDelinquent() {
        return delinquent;
    }

    public double getPrincipal() {
        return principal;
    }
    
    public double getAnnualRate() {
        return annualRate;
    }
    
    // Useful for testing - get the minimum payment that will be due
    public double calculateMinimumPaymentDue() {
        if (balance <= 0) return 0;
        double nextInterest = balance * (annualRate / 12);
        return getMinimumPayment(nextInterest);
    }
}