package bank.products.cards;

import bank.enums.Currency;
import bank.interfaces.Credit;
import bank.products.abstracts.Card;
import bank.utils.CheckAmount;
import bank.utils.constants.ProductNames;

public class CreditCard extends Card implements Credit {

    private Double interestRate;
    private Double debt = 0.0;

    public CreditCard(Double balance) {
        super(ProductNames.CREDIT_CARD, balance, Currency.RUB);
        interestRate = 1.0;
    }

    public CreditCard() {
        this(0.0);
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public Double getDebt() {
        return debt;
    }

    public void replenishBalance(Double amount) {
        CheckAmount.checkAmountIsNotNegative(amount);
        replenishCreditCard(amount);
    }

    private void replenishCreditCard(Double amount) {
        CheckAmount.checkAmountIsNotNegative(amount);
        if (debt > amount) {
            debt -= amount;
        } else {
            balance = amount - debt;
            debt = 0.0;
        }
    }

    public void withdraw(Double amount) {
        CheckAmount.checkAmountIsNotNegative(amount);
        if (balance < amount) {
            debt = amount - balance;
            balance = 0.0;
        } else {
            balance = balance - amount;
        }
    }

}
