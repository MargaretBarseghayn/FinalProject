package model;

import com.company.Account;
import validation.Validations;

import javax.naming.InvalidNameException;
import java.util.ArrayList;
import java.util.Objects;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String passport;
    private final ArrayList<Account> accounts;

    public Customer(String firstName, String lastName, String passport, ArrayList<Account> accounts) throws InvalidNameException {
        Validations.validateName(firstName);
        Validations.validateName(lastName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
        this.accounts = accounts;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassport() {
        return passport;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account){
            accounts.add(account);
    }

    public void removeAccount(Account account){
        accounts.remove(account);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return (passport.equals(customer.passport));
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passport='" + passport + '\'' +
                ", accounts= {" + accounts.toString() +
                '}';
    }
}
