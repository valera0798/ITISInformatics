package views.frames.launch;

import models.PlayerSettings;
import supports.Strings;
import views.frames.AppPanel;
import views.frames.launch.components.DataPanel;
import views.frames.launch.components.panels.FieldSizePanel;
import views.frames.launch.components.panels.PlayerNamePanel;
import views.frames.launch.components.panels.PlayerSidePanel;
import views.frames.launch.components.panels.EnemyTypePanel;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

/**
 * Created by Валера on 22.07.2017.
 *
 * Панель-контейнер для всех настроек
 *
 */
public class SettingsPanel extends AppPanel {
    private static final String NAME_PATTERN = "[A-Z|a-z|А-Я|а-я]+";    // общий вид допустимого имени игрока

    private PlayerNamePanel playerNamePanel;
    private DataPanel playerSidePanel;
    private DataPanel fieldSizePanel;
    private EnemyTypePanel enemyTypePanel;


    public SettingsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(new Color(255, 255, 255, 0));
        initNamePart();
        initSidePart();
        initFieldSizePart();
        initEnemyPart();
    }

    private void initNamePart() {
        playerNamePanel = new PlayerNamePanel(Strings.LABEL_PLAYER_NAME, true);
        playerNamePanel.setVisible(true);
        add(playerNamePanel);
    }
    private void initSidePart() {
        playerSidePanel = new PlayerSidePanel();
        playerSidePanel.setVisible(true);
        add(playerSidePanel);
    }
    private void initFieldSizePart() {
        fieldSizePanel = new FieldSizePanel();
        fieldSizePanel.setVisible(true);
        add(fieldSizePanel);

    }
    private void initEnemyPart() {
        enemyTypePanel = new EnemyTypePanel();
        enemyTypePanel.setVisible(true);
        add(enemyTypePanel);
    }

    public boolean fieldsFilled() {
        boolean result = Pattern.matches(NAME_PATTERN, playerNamePanel.getPlayerName());
        if (enemyTypePanel.getEnemyNamePanel().isVisible())
            result &= Pattern.matches(NAME_PATTERN, enemyTypePanel.getEnemyNamePanel().getPlayerName());
        return result;
    }
    public PlayerSettings getPlayerSettings() {
        if (fieldsFilled()) {
            PlayerSettings playerSettings = new PlayerSettings();
            // установка имени пользователя
            playerNamePanel.setData(playerSettings);
            // установка стороны
            playerSidePanel.setData(playerSettings);
            // установка размера игрового поля
            fieldSizePanel.setData(playerSettings);
            // установка противника
            enemyTypePanel.setData(playerSettings);
            return playerSettings;
        } else return null;
    }
    public void setPlayerSettings(PlayerSettings playerSettings) {
        // установка имени пользователя согласно настройке
        playerNamePanel.getData(playerSettings);
        // установка стороны согласно настройке
        playerSidePanel.getData(playerSettings);
        // установка размера игрового поля согласно настройке
        fieldSizePanel.getData(playerSettings);
        // установка противника согласно настройке
        enemyTypePanel.getData(playerSettings);
    }
}
