package exception;

public class EmptyEmailAndPasswordException extends WebAppException {
    public EmptyEmailAndPasswordException() {
        super(ERROR_EMAIL_AND_PASSWORD_ARE_EMPTY);
    }
}
