package bank.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public enum Currency {
    RUB("руб."),
    EUR("евро"),
    USD("долл. США");

    private final String currencyName;

    Currency(String currencyName) {
        this.currencyName = currencyName;
    }

    public static List<Currency> getForeignCurrencyList() {
        return Arrays.stream(Currency.values())
                .filter(currency -> !currency.equals(Currency.RUB))
                .collect(Collectors.toList());
    }

    public String getName() {
        return currencyName;
    }

    @Override
    public String toString() {
        return getName();
    }
}
