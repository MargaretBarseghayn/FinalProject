package com.company;

import enums.CardType;
import enums.Currency;
import exceptions.IllegalPasswordLengthException;
import model.Bank;
import model.BankCard;
import model.Customer;
import service.AmeriaBank;

import javax.naming.InvalidNameException;
import java.rmi.server.UID;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;

public class Main {

    public static void main(String[] args) throws InvalidNameException {
        BankCard bankCard = new BankCard("Maga Bl", 1212121212, YearMonth.now(), CardType.MASTER, Currency.AMD, 1000);
        Account ac = new Account(bankCard);

        ac.deposit(120000);

        Customer c = new Customer("Maga", "Barseghyan", "123456",  new ArrayList<>());
        Customer c1 = new Customer("Maga", "Bl", "1123456", new ArrayList<>());
        c1.addAccount(ac);
        AmeriaBank ameriaBank = new AmeriaBank();
        ameriaBank.addCustomer(c);
        ameriaBank.addCustomer(c1);

        System.out.println(c1.getAccounts().size());
        ameriaBank.deal(c1);

//        ameriaBank.deal(c1);
//        System.out.println(ameriaBank.customersCount());

       ameriaBank.printCustomers();
    }
}
