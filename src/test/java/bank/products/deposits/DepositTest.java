package bank.products.deposits;

import bank.enums.Currency;
import bank.exceptions.IllegalAmountException;
import bank.utils.constants.ProductNames;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class DepositTest {

    @Test
    public void createCardWithNegativeBalance() {
        assertThrowsExactly(IllegalAmountException.class, () -> new Deposit(-10.0));
    }

    @Test
    public void checkDefaultValues() {
        Deposit deposit = new Deposit();
        assertEquals(0.0, deposit.getBalance());
        assertEquals(Currency.RUB, deposit.getCurrency());
        assertEquals(ProductNames.DEPOSIT, deposit.getName());
        assertFalse(deposit.isClosed(), "При создании депозита, он должен быть открытым");
    }

    @Test
    public void positiveReplenishmentTest() {
        Deposit deposit = new Deposit();
        double replenishmentAmount = 10.0;
        deposit.replenishBalance(replenishmentAmount);
        assertEquals(replenishmentAmount, deposit.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -10.0})
    public void replenishNegativeTest(double replenishmentAmount) {
        Deposit deposit = new Deposit();
        assertThrowsExactly(IllegalAmountException.class, () -> deposit.replenishBalance(replenishmentAmount));
    }

    @Test
    public void checkCloseDepositMethod() {
        Deposit deposit = new Deposit();
        deposit.closeDeposit();
        assertTrue(deposit.isClosed(), "Депозит не закрылся!");
    }

}
