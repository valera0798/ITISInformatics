package exception;

public class IncorrectEmailAndPasswordException extends WebAppException {
    public IncorrectEmailAndPasswordException() {
        super(ERROR_EMAIL_AND_PASSWORD_ARE_INCORRECT);
    }
}
