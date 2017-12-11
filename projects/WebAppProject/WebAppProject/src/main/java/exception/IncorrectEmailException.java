package exception;

public class IncorrectEmailException extends WebAppException {
    public IncorrectEmailException() {
        super(ERROR_EMAIL_IS_INCORRECT);
    }
}
