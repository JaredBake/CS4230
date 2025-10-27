package model;

import org.junit.Assert;
import org.junit.Test;

public class SavingsTest {

    @Test
    public void testDepositValid() {
        Savings savings = new Savings();
        savings.deposit(200.0);
        Assert.assertEquals(200.0, savings.getBalance(), 0.0001);
    }

    @Test
    public void testWithdrawValid() {
        Savings savings = new Savings();
        savings.deposit(300.0);
        boolean success = savings.withdraw(100.0);
        Assert.assertTrue(success);
        Assert.assertEquals(200.0, savings.getBalance(), 0.0001);
    }

    @Test
    public void testWithdrawInvalid() {
        Savings savings = new Savings();
        savings.deposit(100.0);
        boolean success = savings.withdraw(200.0);
        Assert.assertFalse(success);
        Assert.assertEquals(100.0, savings.getBalance(), 0.0001);
    }

    @Test
    public void testApplyMonthlyInterest() {
        Savings savings = new Savings();
        savings.deposit(1000.0);
        savings.applyMonthlyInterest(0.01); // 1% interest
        Assert.assertEquals(1010.0, savings.getBalance(), 0.0001);
    }

    @Test
    public void testApplyMonthlyInterestRoundsTwoDecimals() {
        Savings savings = new Savings();
        savings.deposit(100.005);
        savings.applyMonthlyInterest(0.005);
        Assert.assertEquals(100.51, savings.getBalance(), 0.0001); // Rounded to 2 decimals
    }

    @Test
    public void testMultipleDepositsAndWithdrawals() {
        Savings savings = new Savings();
        savings.deposit(500.0);
        savings.withdraw(100.0);
        savings.deposit(200.0);
        Assert.assertEquals(600.0, savings.getBalance(), 0.0001);
    }
}
