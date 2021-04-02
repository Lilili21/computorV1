package exeption;

public class SolutionProblemException extends RuntimeException {
    String message;

    public SolutionProblemException(String message){
        super(message);
    }

}
