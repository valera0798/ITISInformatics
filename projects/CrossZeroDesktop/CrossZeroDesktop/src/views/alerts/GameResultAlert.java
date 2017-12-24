package views.alerts;

import models.players.Player;
import supports.Strings;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Валера on 01.08.2017.
 */
public class GameResultAlert {
    public static void win(Component component, Player winner) {
        UIManager.put("OptionPane.okButtonText", Strings.BUTTON_RETURN);
        JOptionPane.showMessageDialog(
                component,
                new String[] {
                        "<html><h2>" + Strings.GAME_RESULT_WIN + "</h2><html>",
                        "<html><i>" + "Игрок \"" + winner.getName() + "\" выиграл" + "</i><html>"
                },
                Strings.GAME_RESULT_TITLE,
                JOptionPane.INFORMATION_MESSAGE);
    }
    public static void draw(Component component) {
        UIManager.put("OptionPane.okButtonText", Strings.BUTTON_RETURN);
        JOptionPane.showMessageDialog(
                component,
                new String[] {
                        "<html><h2>" + Strings.GAME_RESULT_DRAW + "</h2><html>",
                },
                Strings.GAME_RESULT_TITLE,
                JOptionPane.INFORMATION_MESSAGE);
    }
}
