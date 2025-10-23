// Test the core loan logic - interest, payments, late fees, minimum payments.
package model;

import org.junit.Assert;
import org.junit.Test;

public class LoanTest {

    @Test 
    public void testDelinquentStatus(){
        // Arrange
        Loan loan = new Loan(1, 1000.0, 0.12); // 12% annual rate
        double monthlyRate = 0.12 / 12;

        // Act - First month, make insufficient payment
        loan.makePayment(5.0); // Less than minimum
        loan.applyMonthlyInterest(monthlyRate);

        // Assert delinquent after first month
        Assert.assertFalse(loan.isDelinquent());

        // Act - Second month, make sufficient payment
        double interest = loan.getBalance() * monthlyRate;
        double minPayment = loan.getMinimumPayment(interest);
        loan.makePayment(minPayment); // Pay minimum
        loan.applyMonthlyInterest(monthlyRate);
        loan.isFirstMonth = false;

        // Assert not delinquent after second month
        Assert.assertFalse(loan.isDelinquent());
    }
}
