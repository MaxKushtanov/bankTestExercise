package bank.products.cards;

import bank.enums.Currency;
import bank.exceptions.IllegalAmountException;
import bank.utils.constants.ProductNames;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


public class CreditCardTest {

    @Test
    public void createCardWithNegativeBalance() {
        assertThrowsExactly(IllegalAmountException.class, () -> new CreditCard(-10.0));
    }

    @Test
    public void checkDefaultValues() {
        CreditCard card = new CreditCard();
        assertEquals(0.0, card.getBalance());
        assertEquals(0.0, card.getDebt());
        assertEquals(Currency.RUB, card.getCurrency());
        assertEquals(ProductNames.CREDIT_CARD, card.getName());
        assertTrue(card.getInterestRate() >= 0);
    }

    @Test
    public void positiveReplenishmentTest() {
        CreditCard card = new CreditCard();
        double replenishmentAmount = 10.0;
        card.replenishBalance(replenishmentAmount);
        assertEquals(replenishmentAmount, card.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -10.0})
    public void replenishNegativeTest(double replenishmentAmount) {
        CreditCard card = new CreditCard();
        assertThrowsExactly(IllegalAmountException.class, () -> card.replenishBalance(replenishmentAmount));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -10.0})
    public void withdrawNegativeTest(double replenishmentAmount) {
        CreditCard card = new CreditCard();
        assertThrowsExactly(IllegalAmountException.class, () -> card.withdraw(replenishmentAmount));
    }

    @Test
    public void withdrawLessThanBalance() {
        double startBalance = 100.0;
        CreditCard card = new CreditCard(startBalance);
        double withdrawAmount = 50.0;
        card.withdraw(withdrawAmount);
        assertEquals(startBalance - withdrawAmount, card.getBalance());
        assertEquals(0.0, card.getDebt());
    }

    @Test
    public void withdrawMoreThanBalance() {
        double startBalance = 50.0;
        CreditCard card = new CreditCard(startBalance);
        double withdrawAmount = 100.0;
        card.withdraw(withdrawAmount);
        assertEquals(0.0, card.getBalance());
        assertEquals(withdrawAmount - startBalance, card.getDebt());
    }


}
