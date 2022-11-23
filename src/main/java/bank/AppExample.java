package bank;

import bank.enums.Currency;
import bank.interfaces.Withdraw;
import bank.products.abstracts.ProductWithBalanceAndCurrency;
import bank.products.cards.CreditCard;
import bank.products.cards.DebitCard;
import bank.products.cards.DebitCardWithForeignCurrency;
import bank.products.deposits.Deposit;

import java.util.ArrayList;
import java.util.List;

public class AppExample {

    public static void main(String[] args) {
        List<ProductWithBalanceAndCurrency> products = initProductList();
        for (ProductWithBalanceAndCurrency product : products) {
            printProductProperties(product);
            replenishProduct(product, 10.0);
            if (product instanceof Withdraw) {
                withdrawFromProduct(product, 5.0);
            }
            if(product instanceof Deposit){
                closeDeposit((Deposit) product);
            }
        }
    }

    private static void closeDeposit(Deposit product) {
        Deposit deposit = product;
        System.out.println("Депозит закрыт? " + deposit.isClosed());
        System.out.println("Закрываем депозит");
        deposit.closeDeposit();
        System.out.println("Депозит закрыт? " + deposit.isClosed());
        System.out.println();
    }

    private static void withdrawFromProduct(ProductWithBalanceAndCurrency product, double withdrawAmount) {
        Currency currencyName = product.getCurrency();
        System.out.printf("=== Снимаем деньги: %s %s ===%n", withdrawAmount, currencyName);
        ((Withdraw) product).withdraw(withdrawAmount);
        System.out.printf("Баланс после снятия: %s %s%n", product.getBalance(), currencyName);
    }

    private static void replenishProduct(ProductWithBalanceAndCurrency product, double replenishmentAmount) {
        Currency currencyName = product.getCurrency();
        System.out.printf("=== Пополняем счет на: %s %s ===%n", replenishmentAmount, currencyName);
        product.replenishBalance(replenishmentAmount);
        System.out.printf("Баланс после пополнения: %s %s%n", product.getBalance(), currencyName);
    }

    private static void printProductProperties(ProductWithBalanceAndCurrency product) {
        System.out.println();
        System.out.println("Имя продукта: " + product.getName());
        System.out.println("Начальный баланс: " + product.getBalance());
        System.out.println("Валюта: " + product.getCurrency());
    }

    private static List<ProductWithBalanceAndCurrency> initProductList() {
        List<ProductWithBalanceAndCurrency> products = new ArrayList<>();
        products.add(new CreditCard());
        products.add(new DebitCard());
        products.add(new Deposit());
        products.add(new DebitCardWithForeignCurrency(Currency.USD));
        return products;
    }

}
