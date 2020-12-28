package model;

import enums.CardType;
import enums.Currency;
import exceptions.IllegalPasswordLengthException;
import md5.MD5;
import validation.Validations;

import javax.naming.InvalidNameException;
import java.time.YearMonth;
import java.util.Objects;

public final class BankCard {
    private final String owner;
    private final long cardNumber;
    private final YearMonth expirationDate;
    private final CardType cardType;
    private Currency currency;
    private final String password;

    public BankCard(String owner, long cardNumber, YearMonth expirationDate, CardType cardType, Currency currency, int password)
            throws IllegalPasswordLengthException, InvalidNameException {
        Validations.validateFullName(owner);

        this.owner = owner;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardType = cardType;
        this.currency = currency;

        int length = String.valueOf(password).length();
        if (length != 4) {
            throw new IllegalPasswordLengthException(length);
        }
        this.password = MD5.md5DigestJavaHexString(password);

    }

    public String getOwner() {
        return owner;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public YearMonth getExpirationDate() {
        return expirationDate;
    }

    public CardType getCardType() {
        return cardType;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getPassword() {
        return password;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankCard bankCard = (BankCard) o;
        return Objects.equals(cardNumber, bankCard.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber);
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "owner='" + owner + '\'' +
                ", cardNumber=" + cardNumber +
                ", expirationDate=" + expirationDate +
                ", cardType=" + cardType +
                ", currency=" + currency +
                ", password='" + password + '\'' +
                '}';
    }
}
