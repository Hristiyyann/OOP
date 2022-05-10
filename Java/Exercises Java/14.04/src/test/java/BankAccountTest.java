import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void depositWithPositiveNumber()
    {
        BankAccount bankAccount = new BankAccount();
        bankAccount.deposit(45.97f);
        assertEquals(45.97f, bankAccount.getTotal_money());
    }

    @Test
    void depositWithNegativeNumber()
    {
        BankAccount bankAccount = new BankAccount();
        assertFalse(bankAccount.deposit(-20f));
    }

    @Test
    void depositWithZero()
    {
        BankAccount bankAccount = new BankAccount();
        assertFalse(bankAccount.deposit(0f));
    }

    @Test
    void withdrawWithMoreMoneyThanInTheAccount()
    {
        BankAccount bankAccount = new BankAccount();
        bankAccount.deposit(30f);
        assertFalse(bankAccount.withdraw(70f));
    }

    @Test
    void withdrawWithLessMoney()
    {
        BankAccount bankAccount = new BankAccount();
        bankAccount.deposit(30f);
        assertTrue(bankAccount.withdraw(10f));
        //arrange act assert
    }
}