package lib;

import java.util.ArrayList;
import java.util.List;

public class account {
    private List<pair<String, Double>> loans;
    private Integer loanCount;
    private Double balance;

    public account() {
        this.loans = new ArrayList<pair<String, Double>>();
        this.loanCount = 0;
        this.balance = 0.0;
    }

    boolean addLoan(String name){
        /* args: name of new loan
         * function: checks if there are less than three loans, creates new loan if true
         * return: false if 3 or more loans
        */
        name = name.toLowerCase();
        if (loanCount < 3){
            loans.add(new pair<String, Double>(name, 0.0));
            loanCount++;
            return true;
        }
        return false;
    }

    public Double getLoan(String name) {
        name = name.toLowerCase();
        for (pair<String, Double> loan : loans) {
            if (loan.getL().equals(name)) {
                return loan.getR();
            }
        }
        return null;
    }

    public Double getBalance() {
        return balance;
    }

    public void deposit(Double amount) {
        this.balance += amount;
    }

    public boolean withdraw(Double amount) {
        /* args: amount to withdraw
         * function: checks if enough money is in account
         * return: true if enough money in account, false if not.
         */
        if (amount > balance) {
            return false;
        }
        this.balance -= amount;
        return true;
    }
}
