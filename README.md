# CS4230
Code and Documents for the WeCheatEm Bank

java -jar bin/WeCheatEmBank.jar
```

### **3. Test Plan (80 points) - CRITICAL**
This is the biggest grade component! Create a document called `TestPlan.pdf` or `TestPlan.docx`:

#### **Required Sections:**

**A. Scope (5 points)**
```
What are you testing?
- Loan interest calculations
- Late fee logic
- Minimum payment calculations
- Savings interest
- Customer account management
- Multi-loan handling
- Month advancement

What are you NOT testing?
- User input validation (handled by UI)
- Print formatting
- Scanner operations
```

**B. Strategy (50 points)**
```
Testing Approach:
1. Unit Testing - Test individual classes in isolation
   - LoanTest: Core loan math, late fees, first month behavior
   - SavingsTest: Deposits, withdrawals, interest
   - CustomerTest: Loan management, activity logging
   - BankTest: Customer management, month advancement

2. Integration Testing - Test complete workflows
   - Complete customer lifecycle
   - Multi-month scenarios
   - Multiple customers interaction

3. Test Data Strategy:
   - Boundary values: $500 and $50,000 loans
   - Edge cases: First month, zero balances
   - Real-world scenarios: Typical customer behavior

4. Pass/Fail Criteria:
   - All calculations must be accurate to $0.01
   - No crashes on valid input
   - Late fees applied correctly after first month
```

**C. Resources (5 points)**
```
Tools & Technologies:
- JUnit 5 for automated testing
- Java 17+
- IDE: IntelliJ IDEA / VS Code / Eclipse

Team:
- Developer/Tester: [Your Name]

Environment:
- Development: Windows/Mac/Linux
- Testing: Same as development
```

**D. Timeline (10 points)**
```
Week 1:
- Implement core models (Loan, Savings, Customer, Bank)
- Write LoanTest and SavingsTest

Week 2:
- Complete UI (MainBank)
- Write CustomerTest and BankTest
- Fix bugs found in unit tests

Week 3:
- Write integration tests
- Perform manual testing
- Create executable JAR
- Document results
```

**E. Risk Assessment (10 points)**
```
Risk 1: First month late fee bug (HIGH)
- Impact: Incorrect charges to customers
- Mitigation: Specific test for first month behavior
- Status: MITIGATED with isFirstMonth flag

Risk 2: Loan ID duplication after closure (MEDIUM)
- Impact: Payment applied to wrong loan
- Mitigation: Use incrementing counter instead of list size
- Status: MITIGATED with nextLoanId counter

Risk 3: Rounding errors in interest (LOW)
- Impact: Balance off by pennies
- Mitigation: Use Math.round() to 2 decimal places
- Status: MITIGATED in all calculation methods

Risk 4: Insufficient test coverage (MEDIUM)
- Impact: Bugs in production
- Mitigation: Aim for >80% code coverage
- Status: ONGOING - comprehensive test suite created
```

### **4. List of Tests Written (20 points)**
Create `TestList.txt` or include in your test plan:
```
EXECUTED TESTS (✓ = Passed, ✗ = Failed):

LoanTest.java:
✓ testLoanCreation
✓ testMakePayment
✓ testPaymentCannotExceedBalance
✓ testMonthlyInterestApplied
✓ testFirstMonthNoLateFee
✓ testSecondMonthWithNoPaymentAddsLateFee
✓ testPaymentBelowMinimumAddsLateFee
✓ testPaymentAtMinimumNoLateFee
✓ testDelinquencyFlagSetWithLateFee
✓ testDelinquencyFlagClearsWithPayment
✓ testMinimumPaymentBasicFormula
✓ testMinimumPaymentFloorTenDollars
✓ testMinimumPaymentWhenDelinquent
✓ testMultipleMissedPaymentsAccumulateFees
✓ testLoanClosedWhenBalanceZero
✓ testTenThousandDollarLoanMinimum

SavingsTest.java:
✓ testInitialBalanceZero
✓ testDeposit
✓ testWithdrawSufficientFunds
✓ testWithdrawInsufficientFunds
✓ testMonthlyInterestApplied
✓ testInterestCompounds
✓ testInterestRounding

CustomerTest.java:
✓ testCustomerCreation
✓ testOpenFirstLoan
✓ testCannotOpenFourthLoan
✓ testLoanIdUniqueAfterClosure
✓ testMakeLoanPayment
✓ testLoanRemovedWhenPaidOff
✓ testProcessMonthlyAppliesSavingsInterest
✓ testProcessMonthlyAppliesLoanInterest

BankTest.java:
✓ testCreateCustomer
✓ testAccountNumbersStartAt1000
✓ testAdvanceMonthIncrementsCounter
✓ testAdvanceMonthProcessesAllCustomers

BankIntegrationTest.java:
✓ testCompleteCustomerLifecycle
✓ testLoanWithMissedPayment
✓ testMultipleLoansSameCustomer
✓ testLoanClosureAllowsNewLoan
✓ testMultipleCustomersIndependent
✓ testDelinquentLoanRecovery

TOTAL: 40 tests executed, 40 passed, 0 failed
```

### **5. Addenda to Test Plan (if appropriate)**
Include if you made changes during testing:
```
ADDENDUM - Changes Made During Testing:

1. Bug Fix: First Month Late Fee
   - Original: Late fee charged in first month
   - Fix: Added isFirstMonth flag to skip first month
   - Tests affected: testFirstMonthNoLateFee

2. Bug Fix: Loan ID Duplication
   - Original: Used loans.size() + 1 for ID
   - Fix: Used incrementing nextLoanId counter
   - Tests affected: testLoanIdUniqueAfterClosure

3. Enhancement: Display Minimum Payment
   - Added minimum payment display in loan summary
   - Requirement: "The bill includes the minimum payment required"
   - Tests affected: None (display only)
```

### **6. Changes/Clarifications to Specifications**
```
SPECIFICATION CLARIFICATIONS:

1. Loan Closure Timing
   - Specification: "Does that mean at the close of the month, or right away?"
   - Decision: Loan closes immediately when balance paid
   - Rationale: Allows customer to open new loan in same month

2. Rounding Method
   - Specification: "You decide (and consider ramifications)"
   - Decision: Round to nearest cent using Math.round()
   - Rationale: Most fair to both bank and customer

3. Multiple Actions Per Month
   - Specification: "It is up to you whether..."
   - Decision: Allow unlimited actions per month
   - Rationale: More realistic, easier to implement
```

### **7. Instructions for Using Your Program (20 points)**
Create `INSTRUCTIONS.txt`:
```
=== WeCheatEm Bank - User Instructions ===

SYSTEM REQUIREMENTS:
- Java 17 or higher installed
- Command line / terminal access

HOW TO RUN:
1. Open command prompt / terminal
2. Navigate to the folder containing WeCheatEmBank.jar
3. Run: java -jar WeCheatEmBank.jar

GETTING STARTED:
1. Enter loan interest rate (6.0 - 18.0)
   - Recommended: 12.0 for testing
   - Savings rate will be automatically set to 1/4 of loan rate

MENU OPTIONS:

1. Create New Customer
   - Enter customer name
   - System assigns account number starting at 1000

2. Deposit to Savings
   - Enter account number
   - Enter amount to deposit

3. Withdraw from Savings
   - Enter account number
   - Enter amount to withdraw
   - If insufficient funds, withdrawal fails

4. Initiate Loan
   - Enter account number
   - Enter loan amount ($500 - $50,000)
   - Maximum 3 loans per customer
   - System displays loan ID number

5. Make Loan Payment
   - Enter account number
   - Enter loan ID
   - Enter payment amount
   - Payment above balance pays off loan exactly

6. Advance to Next Month
   - Applies interest to all savings accounts
   - Applies interest to all loans
   - Checks for missed loan payments
   - $50 late fee if payment < minimum
   - NOTE: First month after opening loan has no late fee

7. Print All Statements
   - Shows all customer account summaries
   - Shows savings balance
   - Shows active loans with balances
   - Shows minimum payment due for each loan
   - Shows recent activity

0. Exit

EXAMPLE SESSION:
> java -jar WeCheatEmBank.jar
Enter loan rate: 12.0
Select: 1
Enter name: Alice
Created customer #1000

Select: 4
Enter account: 1000
Loan amount: 10000
Created loan #1

Select: 6
>>> Advancing to next month...

Select: 7
--- Month 2 Statements ---
Customer: Alice (Account #1000)
  Savings balance: $0.00
  Loan #1 balance: $10100.00
    Minimum payment due: $200.00
Recent Activity:
  Opened loan #1 for $10000.00

TIPS:
- Use option 6 (Advance Month) to see interest applied
- Make at least minimum payment to avoid $50 late fee
- Minimum payment = interest + 1% of principal (or $10, whichever greater)
- If delinquent, minimum payment includes extra $50
- Pay more than minimum to reduce principal faster

TROUBLESHOOTING:
- "No such customer" - Check account number
- "You already have 3 loans" - Pay off a loan first
- "Loan not found" - Check loan ID in statements
- Invalid input - Program will prompt for correct range
```

---

## 📦 Final Submission Structure
```
CS4230-Project3-YourName.zip
├── WeCheatEmBank.jar          (The executable)
├── INSTRUCTIONS.txt           (How to run)
├── TestPlan.pdf               (80 points - most important!)
├── TestList.txt               (20 points - which tests executed)
├── SpecificationChanges.txt   (Any clarifications)
├── source/                    (30 points)
│   └── bank/
│       ├── MainBank.java
│       ├── model/...
│       └── util/...
└── tests/                     (Shows your work)
    └── bank/
        ├── model/
        │   ├── LoanTest.java
        │   ├── SavingsTest.java
        │   ├── CustomerTest.java
        │   └── BankTest.java
        └── integration/
            └── BankIntegrationTest.java