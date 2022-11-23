package bank.products.deposits;


import bank.enums.Currency;
import bank.products.abstracts.ProductWithBalanceAndCurrency;
import bank.utils.constants.ProductNames;

public class Deposit extends ProductWithBalanceAndCurrency {

    private boolean closed;

    public Deposit(Double balance) {
        super(ProductNames.DEPOSIT, balance, Currency.RUB);
    }

    public Deposit() {
        this(0.0);
    }

    public void closeDeposit() {
        closed = true;
    }

    public boolean isClosed() {
        return closed;
    }
}
