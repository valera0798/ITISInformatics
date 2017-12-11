package exception;

public class EmptyPasswordException extends WebAppException {
    public EmptyPasswordException() {
        super(ERROR_PASSWORD_IS_EMPTY);
    }
}
