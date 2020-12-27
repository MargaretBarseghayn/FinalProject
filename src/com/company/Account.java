package com.company;

import exceptions.InvalidMoneyException;
import model.BankCard;

public class Account {
    private double balance;
    private final BankCard card;

    public Account(BankCard card) {
        this.card = card;
        this.balance = 0;
    }

    public BankCard getCard() {
        return card;
    }

    public double getBalance() {
        return balance;
    }

    public void withDrawMoney(double amount) throws InvalidMoneyException {
        if (amount <= 0 || amount > balance) {
            throw new InvalidMoneyException("Illegal amount. Try entering something real");
        }
        balance -= amount;
    }

    public void deposit(double amount) throws InvalidMoneyException {
        if (amount <= 0) {
            throw new InvalidMoneyException("Money being added must be greater than 0");
        }
        balance += amount;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", card=" + card.toString() +
                '}';
    }
}
