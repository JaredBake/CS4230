

import java.util.Scanner;

import model.Customer;

public class MainBank {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("--- Welcome to WeCheatEm Bank ---");
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
                    String name = readNonEmptyLine("Enter customer name: ");
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
                        int loanId = c.openLoan(amt, bank.getLoanRate());
                        if (loanId > 0) {
                            System.out.println("Created loan ID #" + loanId);
                        }
                    }
                }
                case 5 -> {
                    Customer c = pickCustomer(bank);
                    if (c != null) {
                        c.printLoans();
                        System.out.print("Loan ID to pay: ");
                        int id = readInt(1, Integer.MAX_VALUE); // was nextInt()
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
        int acct = readInt(0, Integer.MAX_VALUE); // was nextInt() + nextLine()
        Customer c = bank.getCustomer(acct);
        if (c == null) {
            System.out.println("No such customer.");
        }
        return c;
    }

    private static String readNonEmptyLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private static double readDouble(double min, double max) {
        while (true) {
            String line = in.nextLine().trim();
            if (line.isEmpty()) {
                System.out.print("Enter a value between " + min + " and " + max + ": ");
                continue;
            }
            try {
                double d = Double.parseDouble(line);
                if (d >= min && d <= max) return d;
            } catch (NumberFormatException ignored) {}
            System.out.print("Enter a value between " + min + " and " + max + ": ");
        }
    }

    private static int readInt(int min, int max) {
        while (true) {
            String line = in.nextLine().trim();
            if (line.isEmpty()) {
                System.out.print("Enter a number between " + min + " and " + max + ": ");
                continue;
            }
            try {
                int i = Integer.parseInt(line);
                if (i >= min && i <= max) return i;
            } catch (NumberFormatException ignored) {}
            System.out.print("Enter a number between " + min + " and " + max + ": ");
        }
    }
}
