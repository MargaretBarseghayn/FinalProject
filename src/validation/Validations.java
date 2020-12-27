package validation;

import com.company.Account;
import exceptions.InvalidCardException;

import javax.naming.InvalidNameException;
import java.time.YearMonth;

public abstract class Validations {

    public static void validateName(String name) throws InvalidNameException {
        if(!name.matches("[A-Z][a-z]+")){
            throw new InvalidNameException("Name must contain only letters");
        }
    }

    public static void validateFullName(String fullName) throws InvalidNameException{
        if(!fullName.matches("[A-Z][a-z]+[\\u0020\"][A-Z][a-z]+")){
            throw new InvalidNameException("Full Name format must be ex. Margarit Barseghyan");
        }
    }

    public static void validateCard(Account account) throws InvalidCardException{
        if(account.getCard().getExpirationDate().compareTo(YearMonth.now()) < 0){
            throw new InvalidCardException("Card is expired");
        }
    }



}
