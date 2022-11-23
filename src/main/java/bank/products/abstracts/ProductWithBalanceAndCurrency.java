package bank.products.abstracts;

import bank.enums.Currency;
import bank.utils.CheckAmount;

public abstract class ProductWithBalanceAndCurrency extends Product {
    protected Double balance;
    private final Currency currency;

    public ProductWithBalanceAndCurrency(String name, Double balance, Currency currency) {
        super(name);
        CheckAmount.checkAmountIsPositive(balance);
        this.balance = balance;
        this.currency = currency;
    }

    public Double getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void replenishBalance(Double amount) {
        CheckAmount.checkAmountIsNotNegative(amount);
        balance += amount;
    }

}
