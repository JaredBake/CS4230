// Test customer operations - loans, deposits, withdrawals, statements.
package model;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {
    @Test
    public void testDepositValid(){
        // Arrange
        Customer customer = new Customer(1000, "Test User");
        double savings = customer.getSavingsBalance();

        // Act
        customer.deposit(500.0);
        double actualSavings = customer.getSavingsBalance();
        // Assert
        Assert.assertEquals(savings + 500.0, actualSavings, 0.0001);
    }

    @Test
    public void testWithdrawValid(){
        // Arrange
        Customer customer = new Customer(1001, "Test User");
        customer.deposit(300.0);
        double savings = customer.getSavingsBalance();

        // Act
        customer.withdraw(200.0);
        double actualSavings = customer.getSavingsBalance();
        // Assert
        Assert.assertEquals(savings - 200.0, actualSavings, 0.0001);
    }

    @Test
    public void testWithdrawInvalid(){
        // Arrange
        Customer customer = new Customer(1002, "Test User");
        customer.deposit(100.0);
        double savings = customer.getSavingsBalance();
        // Act
        customer.withdraw(200.0);
        double actualSavings = customer.getSavingsBalance();
        // Assert
        Assert.assertEquals(savings, actualSavings, 0.0001);
    }

    @Test
    public void testOpenLoanValid(){
        // Arrange
        Customer customer = new Customer(1003, "Test User");
        // Act
        int loanId = customer.openLoan(1000.0, 0.06);
        // Assert
        Assert.assertNotNull(loanId);
    }

    @Test
    public void testOpenLoanExceedLimit(){
        // Arrange
        Customer customer = new Customer(1004, "Test User");
        customer.openLoan(500.0, 0.06);
        customer.openLoan(600.0, 0.06);
        customer.openLoan(700.0, 0.06);
        // Act
        int loanId = customer.openLoan(800.0, 0.06);
        // Assert
        Assert.assertEquals(-1, loanId);
    }

    @Test
    public void testMakeLoanPaymentValid(){
        // Arrange
        Customer customer = new Customer(1005, "Test User");
        int loanId = customer.openLoan(1000.0, 0.06);
        // Act
        customer.makeLoanPayment(loanId, 200.0);
        Loan loan = customer.getLoans().get(0);
        // Assert
        Assert.assertEquals(800.0, loan.getBalance(), 0.0001);
    }

    @Test
    public void testMakeLoanPaymentInvalidLoan(){
        // Arrange
        Customer customer = new Customer(1006, "Test User");
        customer.openLoan(1000.0, 0.06);
        // Act
        customer.makeLoanPayment(9999, 200.0); // Invalid loan ID
        Loan loan = customer.getLoans().get(0);
        // Assert
        Assert.assertEquals(1000.0, loan.getBalance(), 0.0001); // Balance should remain unchanged
    }

    @Test
    public void testMakeLoanPaymentCloseLoan(){
        // Arrange
        Customer customer = new Customer(1007, "Test User");
        int loanId = customer.openLoan(500.0, 0.06);
        // Act
        customer.makeLoanPayment(loanId, 500.0); // Pay off the loan
        // Assert
        Assert.assertTrue(customer.getLoans().isEmpty()); // Loan list should be empty
    }

    @Test
    public void testMakeLoanPaymentTooLarge(){
        // Arrange
        Customer customer = new Customer(1007, "Test User");
        int loanId = customer.openLoan(500.0, 0.06);
        // Act
        customer.makeLoanPayment(loanId, 5000.0); // Pay off the loan
        // Assert
        Assert.assertTrue(customer.getLoans().isEmpty()); // Loan list should be empty
    }

    @Test
    public void testGetLoans(){
        // Arrange
        Customer customer = new Customer(1008, "Test User");
        customer.openLoan(400.0, 0.06);
        customer.openLoan(600.0, 0.07);
        // Act
        java.util.List<Loan> loans = customer.getLoans();
        // Assert
        Assert.assertEquals(2, loans.size());
    }

    @Test
    public void testProccessMonthlyInterest(){
        // Arrange
        Customer customer = new Customer(1009, "Test User");
        customer.openLoan(1000.0, 0.12); // 12% annual rate
        Loan loan = customer.getLoans().get(0);
        // Act
        loan.applyMonthlyInterest(0.01); // 1% monthly rate
        // Assert
        Assert.assertEquals(1010.0, loan.getBalance(), 0.0001); // 1000 + 10 interest
    }

    @Test
    public void testPrintStatement(){
        // TODO: Can't test print statement with current setup
    }
}
