import java.util.*;
import java.text.DecimalFormat;

public class MainBank {
    private static final Scanner in = new Scanner(System.in);
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {
        System.out.println("--- Welcome to WeCheatEm Bank ---");
        // I just have the program asking the user to enter the loan interest rates cuz it seems pretty vague on how we get that
        System.out.print("Enter loan annual interest rate (6.0 - 18.0): ");
        double loanRate = readDouble(6.0, 18.0);

        Bank bank = new Bank(loanRate);

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Create new customer");
            System.out.println("2. Deposit to savings");
            System.out.println("3. Withdraw from savings");
            System.out.println("4. Initiate loan");
            System.out.println("5. Make loan payment");
            System.out.println("6. Advance to next month");
            System.out.println("7. Print all statements");
            System.out.println("0. Exit");
            System.out.print("Select: ");
            int choice = readInt(0, 7);

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter customer name: ");
                    String name = in.nextLine().trim();
                    int acct = bank.createCustomer(name);
                    System.out.println("Created customer #" + acct);
                }
                case 2 -> {
                    Customer c = pickCustomer(bank);
                    if (c != null) {
                        System.out.print("Deposit amount: ");
                        double amt = readDouble(0, Double.MAX_VALUE);
                        c.deposit(amt);
                    }
                }
                case 3 -> {
                    Customer c = pickCustomer(bank);
                    if (c != null) {
                        System.out.print("Withdraw amount: ");
                        double amt = readDouble(0, Double.MAX_VALUE);
                        c.withdraw(amt);
                    }
                }
                case 4 -> {
                    Customer c = pickCustomer(bank);
                    if (c != null) {
                        System.out.print("Loan amount (500 - 50000): ");
                        double amt = readDouble(500, 50000);
                        c.openLoan(amt, bank.getLoanRate());
                    }
                }
                case 5 -> {
                    Customer c = pickCustomer(bank);
                    if (c != null) {
                        c.printLoans();
                        System.out.print("Loan ID to pay: ");
                        int id = in.nextInt(); in.nextLine();
                        System.out.print("Payment amount: ");
                        double amt = readDouble(0, Double.MAX_VALUE);
                        c.makeLoanPayment(id, amt);
                    }
                }
                case 6 -> bank.advanceMonth();
                case 7 -> bank.printStatements();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
            }
        }
    }

    private static Customer pickCustomer(Bank bank) {
        System.out.print("Enter account number: ");
        int acct = in.nextInt(); in.nextLine();
        Customer c = bank.getCustomer(acct);
        if (c == null) {
            System.out.println("No such customer.");
        }
        return c;
    }

    private static double readDouble(double min, double max) {
        while (true) {
            try {
                double d = Double.parseDouble(in.nextLine());
                if (d >= min && d <= max) return d;
            } catch (Exception ignored) {}
            System.out.print("Enter a value between " + min + " and " + max + ": ");
        }
    }

    private static int readInt(int min, int max) {
        while (true) {
            try {
                int i = Integer.parseInt(in.nextLine());
                if (i >= min && i <= max) return i;
            } catch (Exception ignored) {}
            System.out.print("Enter a number between " + min + " and " + max + ": ");
        }
    }
}

class Bank {
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

    public void advanceMonth() {
        System.out.println("\n>>> Advancing to next month...");
        for (Customer c : customers.values()) {
            c.processMonthly(savingsRate / 12, loanRate / 12);
        }
        currentMonth++;
    }

    public void printStatements() {
        System.out.println("\n--- Month " + currentMonth + " Statements ---");
        for (Customer c : customers.values()) {
            c.printStatement();
        }
    }
}

class Customer {
    private int acct;
    private String name;
    private Savings savings = new Savings();
    private List<Loan> loans = new ArrayList<>();
    private List<String> activity = new ArrayList<>();

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

    public void openLoan(double amt, double rate) {
        if (loans.size() >= 3) {
            System.out.println("You already have 3 loans!");
            return;
        }
        Loan loan = new Loan(loans.size() + 1, amt, rate);
        loans.add(loan);
        activity.add("Opened loan #" + loan.getId() + " for $" + fmt(amt));
    }

    public void makeLoanPayment(int id, double amt) {
        for (Loan l : loans) {
            if (l.getId() == id) {
                l.makePayment(amt);
                activity.add("Paid $" + fmt(amt) + " on loan #" + id);
                if (l.isClosed()) {
                    activity.add("Loan #" + id + " closed.");
                    loans.remove(l);
                }
                return;
            }
        }
        System.out.println("Loan not found.");
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

    private String fmt(double d) {
        return String.format("%.2f", d);
    }
}

class Savings {
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

    private String fmt(double d) {
        return String.format("%.2f", d);
    }
}

class Loan {
    private int id;
    private double principal;
    private double balance;
    private double annualRate;
    private boolean delinquent = false;
    private double lastPayment = 0.0;

    public Loan(int id, double amount, double annualRate) {
        this.id = id;
        this.principal = amount;
        this.balance = amount;
        this.annualRate = annualRate;
    }

    public int getId() { return id; }

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
        if (lastPayment < minPay) {
            balance += 50;
            delinquent = true;
        } else {
            delinquent = false;
        }
        balance = Math.round(balance * 100) / 100.0;
        lastPayment = 0;
    }

    private double getMinimumPayment(double interest) {
        double min = Math.max(interest + 0.01 * principal, 10.0);
        if (delinquent) min += 50.0;
        return min;
    }

    public boolean isClosed() { return balance <= 0; }

    public void printSummary() {
        System.out.println("  Loan #" + id + " balance: $" + fmt(balance)
                + (delinquent ? " (Delinquent)" : ""));
    }

    private String fmt(double d) {
        return String.format("%.2f", d);
    }
}
