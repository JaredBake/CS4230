import java.util.HashMap;
import java.util.Map;

import model.Customer;

public class Bank {
    private Map<Integer, Customer> customers = new HashMap<>();
    private double loanRate;
    private double savingsRate;
    private int nextAcct = 1000;
    private int currentMonth = 1;

    public Bank(double loanRate) {
        this.loanRate = loanRate / 100.0;
        this.savingsRate = this.loanRate / 4;
    }

    public int createCustomer(String name) {
        int acct = nextAcct++;
        customers.put(acct, new Customer(acct, name));
        return acct;
    }

    public Customer getCustomer(int acct) {
        return customers.get(acct);
    }

    public double getLoanRate() {
        return loanRate;
    }

    public double getSavingsRate() {
        return savingsRate;
    }

    public void advanceMonth() {
        System.out.println("\n>>> Advancing to next month...\n");
        for (Customer c : customers.values()) {
            c.processMonthly(savingsRate / 12, loanRate / 12);
        }
        currentMonth++;
    }

    public StringBuilder printStatements() {
        StringBuilder output = new StringBuilder();
        output.append("\n--- Month " + currentMonth + " Statements ---\n");
        for (Customer c : customers.values()) {
            output.append(c.printStatement());
        }
        return output;
    }
}