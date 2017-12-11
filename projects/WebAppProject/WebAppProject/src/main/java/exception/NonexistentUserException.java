package exception;

public class NonexistentUserException extends WebAppException {
    public NonexistentUserException() {
        super(ERROR_NONEXCISTENT_USER);
    }
}
