package bank.utils;

import bank.exceptions.IllegalAmountException;

public class CheckAmount {
    public static void checkAmountIsNotNegative(Double amount) {
        if (amount <= 0) {
            throw new IllegalAmountException("Число должно быть положительным!");
        }
    }

    public static void checkAmountIsPositive(Double amount) {
        if (amount < 0) {
            throw new IllegalAmountException("Число не должно быть отрицательным!");
        }
    }

    public static void checkBalanceMoreThanWithdraw(Double balance, Double withdrawAmount) {
        if (balance < withdrawAmount) {
            throw new IllegalAmountException(
                    String.format("Сумма списания равная %s превышает остаток на балансе равный %s!",
                            withdrawAmount, balance));
        }
    }

}
