// Test bank-level operations - customer management, interest rates, month advancement.
import org.junit.Test;

import model.Customer;

public class BankTest {

    @Test
    public void createCustomerValid() {
        // Arrange
        Bank bank = new Bank(6.0);
        String name = "Alice";
        // Act
        int acctNum = bank.createCustomer(name);
        Customer customer = bank.getCustomer(acctNum);
        // Assert
        org.junit.Assert.assertEquals(name, customer.getName());
    }

    @Test
    public void createCustomerNullString() {
        // Arrange
        Bank bank = new Bank(6.0);
        String name = null;
        // Act
        int acctNum = bank.createCustomer(name);
        Customer customer = bank.getCustomer(acctNum);
        // Assert
        org.junit.Assert.assertEquals(name, customer.getName());
    }

    @Test
    public void getLoanRate() {
        // Arrange
        double expectedRate = 6.0;
        Bank bank = new Bank(expectedRate);
        // Act
        double actualRate = bank.getLoanRate();
        // Assert
        org.junit.Assert.assertEquals(expectedRate / 100.0, actualRate, 0.0001);
    }

    @Test
    public void getSavingsRate() {
        // Arrange
        double loan = 8.0;
        Bank bank = new Bank(loan);
        double loanRate = bank.getLoanRate();
        double expectedSavingsRate = loanRate / 4;
        // Act
        double actualSavingsRate = bank.getSavingsRate();
        // Assert
        org.junit.Assert.assertEquals(expectedSavingsRate, actualSavingsRate, 0.0001);
    }

}