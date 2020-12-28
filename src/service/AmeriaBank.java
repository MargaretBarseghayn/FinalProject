package service;

import com.company.Account;
import enums.Banks;
import enums.CardType;
import enums.Currency;
import exceptions.InvalidUserException;
import model.Bank;
import model.BankCard;
import model.Customer;
import model.Menu;

import javax.naming.InvalidNameException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AmeriaBank implements Bank {
    private static AmeriaBank instance;
    private static final Banks NAME = Banks.AMERIA;
    private static final ArrayList<Customer> CUSTOMERS = new ArrayList<>();

    public static AmeriaBank getInstance() {
        if (instance == null) {
            instance = new AmeriaBank();
        }
        return instance;
    }

    public static ArrayList<Customer> getCustomers() {
        return CUSTOMERS;
    }

    @Override
    public void addCustomer(Customer customer) {
        if (customer == null || CUSTOMERS.contains(customer)) {
            return;
        }
        CUSTOMERS.add(customer);
    }

    @Override
    public void removeCustomer(Customer customer) {
        CUSTOMERS.remove(customer);
    }

    @Override
    public int customersCount() {
        return CUSTOMERS.size();
    }

    @Override
    public void printCustomers() {
        for (var customer : CUSTOMERS
        ) {
            System.out.println(customer.toString());
        }
    }

    @Override
    public boolean containsCustomer(Customer customer) {
        for (Customer value : CUSTOMERS) {
            if (value.equals(customer)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deal(Customer customer) throws InvalidUserException, InvalidNameException {
        int selectedItem = Menu.menuList(customer);
        if (selectedItem == 1) {
            BankCardService bankCardService = new BankCardService();
            CardType cd = CardType.MASTER;
            System.out.println("Current card type is Master card. If you want Visa card press 1");
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextInt() == 1) cd = CardType.VISA;

            BankCard bankCard = bankCardService.generateCard(customer.getFirstName() + " " + customer.getLastName(), cd, Currency.AMD);
            if (containsCustomer(customer)) {
                for (Customer value : CUSTOMERS) {
                    if (value.equals(customer)) {
                        value.addAccount(new Account(bankCard));
                        DataRead_Write.update(NAME,value);

                    }
                }
            } else {
                customer.addAccount(new Account(bankCard));
                addCustomer(customer);
                DataRead_Write.write(NAME, customer);

            }


        } else if (selectedItem >= 2) {
            if (!CUSTOMERS.contains(customer))
                throw new InvalidUserException("Can't find selected user in customers list");
            int payment = 10000 + (int) (Math.random() * 90000);
            System.out.println("Your Utility Payments are " + payment);

            for (int i = 0; i < CUSTOMERS.size(); i++) {
                Customer c = CUSTOMERS.get(i);
                if (c.equals(customer)) {
                    c.getAccounts().get(selectedItem - 2).withDrawMoney(payment);
                    CUSTOMERS.set(i, c);
                    DataRead_Write.update(NAME, c);

                }
            }
        }
    }

}
