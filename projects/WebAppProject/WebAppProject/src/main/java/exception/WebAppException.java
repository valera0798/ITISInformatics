package exception;

public class WebAppException extends Exception {
    public static final String KEY_EXCEPTION = "webAppException";

    protected static final String ERROR_EMAIL_IS_EMPTY = "Email is empty.";
    protected static final String ERROR_PASSWORD_IS_EMPTY = "Password is empty.";
    protected static final String ERROR_EMAIL_AND_PASSWORD_ARE_EMPTY = "Email and password are empty.";
    protected static final String ERROR_EMAIL_IS_INCORRECT = "Email is incorrect.";
    protected static final String ERROR_PASSWORD_IS_INCORRECT = "Password is incorrect.";
    protected static final String ERROR_EMAIL_AND_PASSWORD_ARE_INCORRECT = "Email and password are incorrect.";

    protected static final String ERROR_NONEXCISTENT_USER = "User does not exist.";
    protected static final String ERROR_EMAIL_IS_BUSY = "The email address you have used is already registered.";

    public WebAppException(String specificMessage) {
        super(specificMessage);
    }

    public String getType() {
        return getMessage();
    }
}
