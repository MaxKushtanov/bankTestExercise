package bank.utils;

import bank.enums.Currency;
import bank.exceptions.IllegalCurrencyException;

public class CurrencyHelper {

    public static void checkCurrencyIsNotNull(Currency currency) {
        if (currency == null) {
            throw new IllegalCurrencyException("Валюта должна быть указана! Текущее значение null");
        }
    }

    public static boolean currencyIsForeign(Currency currency) {
        return currency.equals(Currency.RUB);
    }

}
