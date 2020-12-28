package service;

import enums.CardType;
import enums.Currency;
import model.BankCard;

import javax.naming.InvalidNameException;
import java.time.YearMonth;

public class BankCardService {
    private static final long LIMIT = 1000000000000L;
    private static long last = 0;

    public BankCard generateCard(String fullName, CardType cardType, Currency currency) throws InvalidNameException {
        long id = System.currentTimeMillis() % LIMIT;
        if (id <= last) {
            id = (last + 1) % LIMIT;
        }
        ++last;
        YearMonth yearMonth = YearMonth.now();
        YearMonth expirationDate = YearMonth.of(yearMonth.getYear() + 4, yearMonth.getMonth());

        int pin = 1000 + (int) (Math.random() * 9000);
        return new BankCard(fullName, id, expirationDate, cardType, currency, pin);
    }
}
