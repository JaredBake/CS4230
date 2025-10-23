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
    public void createCustomerEmptyString() {
        // Arrange
        Bank bank = new Bank(6.0);
        String name = "";
        // Act
        int acctNum = bank.createCustomer(name);
        Customer customer = bank.getCustomer(acctNum);
        // Assert
        org.junit.Assert.assertEquals(name, customer.getName());
    }

}