package exception;

public class EmptyEmailException extends WebAppException {
    public EmptyEmailException() {
        super(ERROR_EMAIL_IS_EMPTY);
    }
}
