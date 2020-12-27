package exceptions;

public class IllegalPasswordLengthException extends RuntimeException {
    public IllegalPasswordLengthException(int param) {
        super("Required length is 4. Provided" + param);
    }
}
