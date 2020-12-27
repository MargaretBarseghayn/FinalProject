package service;

import com.company.Account;
import enums.CardType;
import enums.Currency;
import exceptions.InvalidUserException;
import model.Bank;
import model.BankCard;
import model.Customer;
import model.Menu;

import javax.naming.InvalidNameException;
import java.util.ArrayList;
import java.util.Scanner;

public class AmeriaBank implements Bank {
    private static AmeriaBank instance;
    private static ArrayList<Customer> customers = new ArrayList<>();

    public static AmeriaBank getInstance() {
        if (instance == null) {
            instance = new AmeriaBank();
        }
        return instance;
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    @Override
    public void addCustomer(Customer customer) {
        if (customer == null || customers.contains(customer)) {
            return;
        }
        customers.add(customer);
    }

    @Override
    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    @Override
    public int customersCount() {
        return customers.size();
    }

    @Override
    public void printCustomers() {
        for (var customer : customers
        ) {
            System.out.println(customer.toString());
        }
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

            for (Customer value : customers) {
                if (value.equals(customer)) {
                    value.addAccount(new Account(bankCard));
                }
            }

        } else if (selectedItem >= 2) {
            if (!customers.contains(customer))
                throw new InvalidUserException("Can't find selected user in customers list");
            int payment = 10000 + (int) (Math.random() * 90000);
            System.out.println("Your Utility Payments are " + payment);
            for (Customer value : customers) {
                if (value.equals(customer)) {
                    value.getAccounts().get(selectedItem - 2).withDrawMoney(payment);
                }
            }
            for (int i = 0; i < customers.size(); i++) {
                Customer c = customers.get(i);
                if(c.equals(customer)){
                    System.out.println(customers.get(i).getAccounts().get(selectedItem - 2).getBalance());
                    c.getAccounts().get(selectedItem - 2).withDrawMoney(payment);
                    customers.set(i, c);
                }
                System.out.println(customers.get(i).getAccounts().get(selectedItem - 2).getBalance());
            }
        }
    }

}
