package exception;

public class EmailIsBusyException extends WebAppException {
    public EmailIsBusyException() {
        super(ERROR_EMAIL_IS_BUSY);
    }
}
