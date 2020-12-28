package model;

import exceptions.InvalidUserException;

import javax.naming.InvalidNameException;
import java.io.IOException;

public interface Bank {

    void addCustomer(Customer customer);

    void removeCustomer(Customer customer);

    int customersCount();

    void printCustomers();

    void deal(Customer customer) throws InvalidUserException, InvalidNameException, IOException;

    boolean containsCustomer(Customer customer);

}
