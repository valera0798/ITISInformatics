package exception;

public class IncorrectPasswordException extends WebAppException {
    public IncorrectPasswordException() {
        super(ERROR_PASSWORD_IS_INCORRECT);
    }
}
