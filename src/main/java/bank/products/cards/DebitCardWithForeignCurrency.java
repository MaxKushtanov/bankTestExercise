package bank.products.cards;

import bank.enums.Currency;
import bank.utils.CheckAmount;
import bank.utils.constants.ProductNames;
import bank.exceptions.IllegalCurrencyException;
import bank.utils.CurrencyConverter;
import bank.utils.CurrencyHelper;

public class DebitCardWithForeignCurrency extends DebitCard {

    public DebitCardWithForeignCurrency(Double balance, Currency currency) {
        super(ProductNames.DEBIT_CARD_WITH_FOREIGN_CURRENCY, balance, getCurrencyIfValid(currency));
    }

    public DebitCardWithForeignCurrency(Currency currency) {
        this(0.0, getCurrencyIfValid(currency));
    }

    private static Currency getCurrencyIfValid(Currency currency) {
        CurrencyHelper.checkCurrencyIsNotNull(currency);
        if (CurrencyHelper.currencyIsForeign(currency)) {
            throw new IllegalCurrencyException("Невозможно создать валютную дебетовую карту в рублях");
        }
        return currency;
    }

    public void withdraw(Double amount, Currency currency) {
        CheckAmount.checkAmountIsNotNegative(amount);
        Double exchangeRate = CurrencyConverter.getExchangeRate(currency, getCurrency());
        Double amountAfterConvert = exchangeRate * amount;
        super.withdraw(amountAfterConvert);
    }

    public void replenishBalance(Double amount, Currency currency) {
        CheckAmount.checkAmountIsNotNegative(amount);
        Double exchangeRate = CurrencyConverter.getExchangeRate(currency, getCurrency());
        Double amountAfterConvert = exchangeRate * amount;
        super.replenishBalance(amountAfterConvert);
    }


}
