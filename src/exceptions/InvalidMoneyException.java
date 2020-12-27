package exceptions;

public class InvalidMoneyException extends RuntimeException {
    public InvalidMoneyException(String errorMessage){
        super(errorMessage);
    }
}
