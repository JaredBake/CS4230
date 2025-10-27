package model;

import org.junit.Assert;
import org.junit.Test;

public class SavingsTest {

    @Test
    public void testDepositValid() {
        // Arrange
        Savings savings = new Savings();
        // Act
        savings.deposit(200.0);
        // Assert
        Assert.assertEquals(200.0, savings.getBalance(), 0.0001);
    }

    @Test
    public void testWithdrawValid() {
        // Arrange
        Savings savings = new Savings();
        savings.deposit(300.0);
        // Act
        boolean success = savings.withdraw(100.0);
        // Assert
        Assert.assertTrue(success);
        Assert.assertEquals(200.0, savings.getBalance(), 0.0001);
    }

    @Test
    public void testWithdrawInvalid() {
        // Arrange
        Savings savings = new Savings();
        savings.deposit(100.0);
        // Act
        boolean success = savings.withdraw(200.0);
        // Assert
        Assert.assertFalse(success);
        Assert.assertEquals(100.0, savings.getBalance(), 0.0001);
    }

    @Test
    public void testApplyMonthlyInterest() {
        // Arrange
        Savings savings = new Savings();
        savings.deposit(1000.0);
        // Act
        savings.applyMonthlyInterest(0.01); // 1% interest
        // Assert
        Assert.assertEquals(1010.0, savings.getBalance(), 0.0001);
    }

    @Test
    public void testApplyMonthlyInterestRoundsTwoDecimals() {
        // Arrange
        Savings savings = new Savings();
        savings.deposit(100.005);
        // Act
        savings.applyMonthlyInterest(0.005);
        // Assert
        Assert.assertEquals(100.51, savings.getBalance(), 0.0001); // Rounded to 2 decimals
    }

    @Test
    public void testMultipleDepositsAndWithdrawals() {
        // Arrange
        Savings savings = new Savings();
        // Act
        savings.deposit(500.0);
        savings.withdraw(100.0);
        savings.deposit(200.0);
        // Assert
        Assert.assertEquals(600.0, savings.getBalance(), 0.0001);
    }
}
