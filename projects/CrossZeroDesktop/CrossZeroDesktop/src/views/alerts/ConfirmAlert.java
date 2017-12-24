package views.alerts;

import models.PlayerSettings;
import supports.Strings;
import views.frames.game.GameFrame;

import javax.swing.*;
import java.awt.event.WindowEvent;

/**
 * Created by Валера on 01.08.2017.
 */
public class ConfirmAlert {
    public static void confirmRestartGame(GameFrame gameFrame) {
        UIManager.put("OptionPane.yesButtonText", Strings.BUTTON_YES);
        UIManager.put("OptionPane.noButtonText", Strings.BUTTON_NO);
        int result = JOptionPane.showConfirmDialog(
                gameFrame,
                Strings.CONFIRM_RESTART_TEXT,
                Strings.CONFIRM_TITLE,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) { // если пользователь перезаупскает игру
            gameFrame.getGameField().removeAllMouseListeners();

            gameFrame.getLaunchFrame().setPlayerSettings(new PlayerSettings());
            gameFrame.getLaunchFrame().setVisible(true);

            gameFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            gameFrame.dispatchEvent(new WindowEvent(gameFrame, WindowEvent.WINDOW_CLOSING));
            gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
    }
}
