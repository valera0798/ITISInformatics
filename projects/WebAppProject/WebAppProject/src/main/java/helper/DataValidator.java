package helper;

import exception.*;

import java.util.regex.Pattern;

public class DataValidator {
    private static final Pattern PATTERN_EMAIL = Pattern.compile("[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}");

    // валидация данных аутентификации
    public static String validate(String guestEmail, String guestPassword) {  // возвращает текст ошибки
        WebAppException emptyException = checkEmptiness(guestEmail, guestPassword);
        String errorEmptyParameters = (emptyException == null) ? null : emptyException.getMessage();
        if (errorEmptyParameters == null) {  // данные непустые
            WebAppException incorrectException = checkCorrectness(guestEmail, guestPassword);
            String errorIncorrectParameters = (incorrectException == null) ? null : emptyException.getMessage();
            if (errorIncorrectParameters == null) {    // данные валидны
                return null;
            } else {
                return errorIncorrectParameters;
            }
        } else {
            return errorEmptyParameters;
        }
    }
    // валидация данных регистрации
    public static void validate(String email, String password, String passwordRepetition) {

    }

    private static WebAppException checkEmptiness(String userEmail, String userPassword) {
        boolean emailIsEmpty = userEmail == null || userEmail.length() == 0;
        boolean passwordIsEmpty = userPassword == null || userPassword.length() == 0;
        if (emailIsEmpty && passwordIsEmpty) {
            return new EmptyEmailAndPasswordException();
        } else if (emailIsEmpty) {
            return new EmptyEmailException();
        } else if (passwordIsEmpty) {
            return new EmptyPasswordException();
        } else
            return null;
    }
    private static WebAppException checkCorrectness(String userEmail, String userPassword) {
        boolean emailIsIncorrect = !PATTERN_EMAIL.matcher(userEmail).matches();
        boolean passwordIsIncorrect = userPassword.equals(userPassword);
        if (emailIsIncorrect && passwordIsIncorrect) {
            return new IncorrectEmailAndPasswordException();
        } else if (emailIsIncorrect) {
            return new IncorrectEmailException();
        } else if (passwordIsIncorrect) {
            return new IncorrectPasswordException();
        } else
            return null;
    }
}
