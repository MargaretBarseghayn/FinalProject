package model;

import exceptions.InvalidUserException;

import javax.naming.InvalidNameException;

public interface Bank {

    void addCustomer(Customer customer);

    void removeCustomer(Customer customer);

    int customersCount();

    void printCustomers();

    void deal(Customer customer) throws InvalidUserException, InvalidNameException;

}
