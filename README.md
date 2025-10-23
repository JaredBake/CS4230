# 🏦 WeCheatEm Bank  
### CS4230 — Banking System Project  

## 📋 Overview
**WeCheatEm Bank** is a console-based banking simulation program that allows users to manage customers, savings accounts, and loans.  
Users can create customers, deposit and withdraw money, initiate and pay loans, and advance the system through multiple months to simulate account growth and loan payments.

This project demonstrates fundamental object-oriented programming concepts in Java including classes, encapsulation, and modular design.

---

## 🚀 How to Run the Program
### Requirements
- **Java 17 or higher**

- Works on Windows, macOS, or Linux

- `WeCheatEmBank.jar` must be in the same folder as the `lib/` directory (if any external libraries are used)

### Steps
1. Open a terminal (or PowerShell on Windows).  

2. Navigate to the directory containing the JAR file:
   ```bash
    cd path/to/dist
   ```
3. Run the program:
   ```bash
    java -jar WeCheatEmBank.jar
   ```

4. Follow the on-screen menu prompts.

---

## 🧭 Program Menu
After starting, you’ll see:
```bash
--- Welcome to WeCheatEm Bank ---
Enter loan annual interest rate (6.0 - 18.0): 8
--- Main Menu ---
1. Create new customer
2. Deposit to savings
3. Withdraw from savings
4. Initiate loan
5. Make loan payment
6. Advance to next month
7. Print all statements
0. Exit
Select:
```
###  Menu Options Explained
| Option | Action |
|--------|--------|
| 1 | Create a new customer account |
| 2 | Deposit funds into a customer's savings |
| 3 | Withdraw funds from a customer's savings |
| 4 | Open a new loan for a customer |
| 5 | Make a loan payment |
| 6 | Simulate the passing of one month (updates balances and interest) |
| 7 | Print all account and loan statements |
| 0 | Exit the program |

---
## 🧱 Project Structure
```bash
WeCheatEmBank/
├─ src/
│  └─ src/bank/
│     ├─ MainBank.java
│     ├─ Bank.java
│     ├─ util/FormatUtil.java
│     └─ model/
│        ├─ Customer.java
│        ├─ Loan.java
│        └─ Savings.java
├─ out/
│  └─ (compiled .class files)
├─ dist/
│  └─ WeCheatEmBank.jar
├─ tests/
│  ├─ test_plan.docx
│  ├─ test_cases.xlsx
│  ├─ test_results.docx
│  └─ addenda.docx
├─ MANIFEST.MF
└─ README.md
```
---
## 🧠 Notes & Limitations

- The program runs entirely in the console; no GUI is required.

- Data is not persisted between runs (no database or file storage).

- All inputs are validated to prevent empty or invalid entries.

- Pressing Enter with no input will re-prompt safely.