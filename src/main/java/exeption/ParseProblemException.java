package exeption;

public class ParseProblemException extends RuntimeException {
    String message;

    public ParseProblemException(String message) {
        super(message);
    }

}
