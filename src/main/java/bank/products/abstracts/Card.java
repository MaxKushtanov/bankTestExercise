package bank.products.abstracts;

import bank.enums.Currency;
import bank.interfaces.Withdraw;

public abstract class Card extends ProductWithBalanceAndCurrency implements Withdraw {

    public Card(String name, Double balance, Currency currency) {
        super(name, balance, currency);
    }
}
