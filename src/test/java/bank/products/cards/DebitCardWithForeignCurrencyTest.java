package bank.products.cards;

import bank.enums.Currency;
import bank.exceptions.IllegalAmountException;
import bank.exceptions.IllegalCurrencyException;
import bank.utils.CurrencyConverter;
import bank.utils.constants.ProductNames;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class DebitCardWithForeignCurrencyTest {

    @Test
    public void createCardWithNegativeBalance() {
        List<Currency> foreignCurrencyList = Currency.getForeignCurrencyList();
        for (Currency currency : foreignCurrencyList) {
            assertThrowsExactly(IllegalAmountException.class, () -> new DebitCardWithForeignCurrency(-10.0, currency));
        }
    }

    @Test
    public void createCardWithForeignCurrency() {
        List<Currency> foreignCurrencyList = Currency.getForeignCurrencyList();
        for (Currency currency : foreignCurrencyList) {
            DebitCardWithForeignCurrency card = new DebitCardWithForeignCurrency(currency);
            assertEquals(0.0, card.getBalance());
            assertEquals(currency, card.getCurrency());
            assertEquals(ProductNames.DEBIT_CARD_WITH_FOREIGN_CURRENCY, card.getName());
        }
    }

    @Test
    public void createCardWithRUBShouldThrowException() {
        assertThrowsExactly(IllegalCurrencyException.class, () -> new DebitCardWithForeignCurrency(Currency.RUB));
    }

    @Test
    public void positiveReplenishmentTestInSameCurrency() {
        List<Currency> foreignCurrencyList = Currency.getForeignCurrencyList();
        for (Currency currency : foreignCurrencyList) {
            DebitCardWithForeignCurrency card = new DebitCardWithForeignCurrency(currency);
            double replenishmentAmount = 10.0;
            card.replenishBalance(replenishmentAmount);
            assertEquals(replenishmentAmount, card.getBalance());
        }
    }

    @Test
    public void positiveReplenishmentTestWithExchange() {
        double replenishmentAmount = 10.0;
        Currency replenishmentCurrency = Currency.RUB;
        List<Currency> foreignCurrencyList = Currency.getForeignCurrencyList();
        for (Currency currency : foreignCurrencyList) {
            DebitCardWithForeignCurrency card = new DebitCardWithForeignCurrency(currency);
            card.replenishBalance(replenishmentAmount, replenishmentCurrency);
            double exchangeRate = CurrencyConverter.getExchangeRate(replenishmentCurrency, currency);
            double replenishmentAmountAfterExchange = replenishmentAmount * exchangeRate;
            assertEquals(replenishmentAmountAfterExchange, card.getBalance());
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -10.0})
    public void replenishNegativeTestInSameCurrency(double replenishmentAmount) {
        List<Currency> foreignCurrencyList = Currency.getForeignCurrencyList();
        for (Currency currency : foreignCurrencyList) {
            DebitCardWithForeignCurrency card = new DebitCardWithForeignCurrency(currency);
            assertThrowsExactly(IllegalAmountException.class, () -> card.replenishBalance(replenishmentAmount));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -10.0})
    public void replenishNegativeTestWithExchange(double replenishmentAmount) {
        Currency replenishmentCurrency = Currency.RUB;
        List<Currency> foreignCurrencyList = Currency.getForeignCurrencyList();
        for (Currency currency : foreignCurrencyList) {
            DebitCardWithForeignCurrency card = new DebitCardWithForeignCurrency(currency);
            assertThrowsExactly(IllegalAmountException.class, () -> card.replenishBalance(replenishmentAmount, replenishmentCurrency));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -10.0})
    public void withdrawNegativeTestInSameCurrency(double withdrawAmount) {
        List<Currency> foreignCurrencyList = Currency.getForeignCurrencyList();
        for (Currency currency : foreignCurrencyList) {
            DebitCardWithForeignCurrency card = new DebitCardWithForeignCurrency(currency);
            assertThrowsExactly(IllegalAmountException.class, () -> card.withdraw(withdrawAmount));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -10.0})
    public void withdrawNegativeTestWithExchange(double withdrawAmount) {
        Currency withdrawCurrency = Currency.RUB;
        List<Currency> foreignCurrencyList = Currency.getForeignCurrencyList();
        for (Currency currency : foreignCurrencyList) {
            DebitCardWithForeignCurrency card = new DebitCardWithForeignCurrency(currency);
            assertThrowsExactly(IllegalAmountException.class, () -> card.withdraw(withdrawAmount, withdrawCurrency));
        }
    }

    @Test
    public void withdrawLessThanBalance() {
        double startBalance = 100.0;
        double withdrawAmount = 50.0;
        List<Currency> foreignCurrencyList = Currency.getForeignCurrencyList();
        for (Currency currency : foreignCurrencyList) {
            DebitCardWithForeignCurrency card = new DebitCardWithForeignCurrency(startBalance, currency);
            card.withdraw(withdrawAmount);
            assertEquals(startBalance - withdrawAmount, card.getBalance());
        }
    }

    @Test
    public void withdrawLessThanBalanceWithExchange() {
        double startBalance = 1000.0;
        double withdrawAmount = 1.0;
        Currency withdrawCurrency = Currency.RUB;
        List<Currency> foreignCurrencyList = Currency.getForeignCurrencyList();
        for (Currency currency : foreignCurrencyList) {
            DebitCardWithForeignCurrency card = new DebitCardWithForeignCurrency(startBalance, currency);
            card.withdraw(withdrawAmount, withdrawCurrency);
            double exchangeRate = CurrencyConverter.getExchangeRate(withdrawCurrency, currency);
            double withdrawAmountAfterExchange = withdrawAmount * exchangeRate;
            assertEquals(startBalance - withdrawAmountAfterExchange, card.getBalance());
        }
    }

    @Test
    public void withdrawMoreThanBalance() {
        double startBalance = 0.0;
        double withdrawAmount = 100.0;
        List<Currency> foreignCurrencyList = Currency.getForeignCurrencyList();
        for (Currency currency : foreignCurrencyList) {
            DebitCardWithForeignCurrency card = new DebitCardWithForeignCurrency(startBalance, currency);
            assertThrowsExactly(IllegalAmountException.class, () -> card.withdraw(withdrawAmount));
        }
    }

    @Test
    public void withdrawMoreThanBalanceWithExchange() {
        double startBalance = 0.0;
        double withdrawAmount = 100.0;
        Currency withdrawCurrency = Currency.RUB;
        List<Currency> foreignCurrencyList = Currency.getForeignCurrencyList();
        for (Currency currency : foreignCurrencyList) {
            DebitCardWithForeignCurrency card = new DebitCardWithForeignCurrency(startBalance, currency);
            assertThrowsExactly(IllegalAmountException.class, () -> card.withdraw(withdrawAmount, withdrawCurrency));
        }
    }
}
