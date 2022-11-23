package bank.products.cards;

import bank.enums.Currency;
import bank.exceptions.IllegalAmountException;
import bank.utils.constants.ProductNames;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class DebitCardTest {
    @Test
    public void createCardWithNegativeBalance() {
        assertThrowsExactly(IllegalAmountException.class, () -> new DebitCard(-10.0));
    }

    @Test
    public void checkDefaultValues() {
        DebitCard card = new DebitCard();
        assertEquals(0.0, card.getBalance());
        assertEquals(Currency.RUB, card.getCurrency());
        assertEquals(ProductNames.DEBIT_CARD, card.getName());
    }

    @Test
    public void positiveReplenishmentTest() {
        DebitCard card = new DebitCard();
        double replenishmentAmount = 10.0;
        card.replenishBalance(replenishmentAmount);
        assertEquals(replenishmentAmount, card.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -10.0})
    public void replenishNegativeTest(double replenishmentAmount) {
        DebitCard card = new DebitCard();
        assertThrowsExactly(IllegalAmountException.class, () -> card.replenishBalance(replenishmentAmount));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -10.0})
    public void withdrawNegativeTest(double replenishmentAmount) {
        DebitCard card = new DebitCard();
        assertThrowsExactly(IllegalAmountException.class, () -> card.withdraw(replenishmentAmount));
    }

    @Test
    public void withdrawLessThanBalance() {
        double startBalance = 100.0;
        DebitCard card = new DebitCard(startBalance);
        double withdrawAmount = 50.0;
        card.withdraw(withdrawAmount);
        assertEquals(startBalance - withdrawAmount, card.getBalance());
    }

    @Test
    public void withdrawMoreThanBalance() {
        double startBalance = 50.0;
        DebitCard card = new DebitCard(startBalance);
        double withdrawAmount = 100.0;
        assertThrowsExactly(IllegalAmountException.class, () -> card.withdraw(withdrawAmount));
    }
}
