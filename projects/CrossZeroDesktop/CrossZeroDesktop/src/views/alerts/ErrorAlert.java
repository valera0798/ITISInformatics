package views.alerts;

import supports.Strings;
import views.frames.game.GameField;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Валера on 01.08.2017.
 */
public class ErrorAlert {
    public static void noNameError(Component component) {
        UIManager.put("OptionPane.okButtonText", Strings.BUTTON_FIX);
        JOptionPane.showMessageDialog(
                component,
                Strings.ERROR_INCORRECT_INPUT_NAME,
                Strings.ERROR_TITLE,
                JOptionPane.ERROR_MESSAGE);
    }
    public static void cellIsNotValid(GameField gameField) {
        UIManager.put("OptionPane.okButtonText", Strings.BUTTON_RETURN);
        JOptionPane.showMessageDialog(
                gameField,
                Strings.ERROR_CELL_IS_NOT_VALID,
                Strings.ERROR_TITLE,
                JOptionPane.ERROR_MESSAGE);
    }
}
