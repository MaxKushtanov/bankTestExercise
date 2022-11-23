package bank.products.cards;


import bank.enums.Currency;
import bank.products.abstracts.Card;
import bank.utils.constants.ProductNames;
import bank.utils.CheckAmount;

public class DebitCard extends Card {

    public DebitCard(Double balance) {
        this(ProductNames.DEBIT_CARD, balance, Currency.RUB);
    }

    public DebitCard() {
        this(ProductNames.DEBIT_CARD, 0.0, Currency.RUB);
    }

    protected DebitCard(String name, Double balance, Currency currency) {
        super(name, balance, currency);
    }

    public void withdraw(Double amount) {
        CheckAmount.checkAmountIsNotNegative(amount);
        CheckAmount.checkBalanceMoreThanWithdraw(balance, amount);
        balance -= amount;
    }

}
