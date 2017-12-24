package views.frames.launch;

import controllers.actionlisteners.CloseAppActionListener;
import controllers.actionlisteners.StartGameActionListener;
import models.PlayerSettings;
import views.frames.AppFrame;
import supports.Strings;
import views.frames.ButtonsPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Валера on 22.07.2017.
 *
 * Класс лаунчера игры
 *
 */
public class LaunchFrame extends AppFrame {
    private SettingsPanel settingsPanel;
    private ButtonsPanel startCloseButtonsPanel;

    public LaunchFrame() throws HeadlessException {
        super();
        frameWidth = 320;
        frameHeight = 430;

        init();
    }

    public PlayerSettings getPlayerSettings() {
        return settingsPanel.getPlayerSettings();
    }
    public void setPlayerSettings(PlayerSettings playerSettings) {
        settingsPanel.setPlayerSettings(playerSettings);
    }
    public byte getFieldSize() {
        return settingsPanel.getPlayerSettings().getFieldSize();
    }

    @Override
    protected void init() {
        setTitle(Strings.SETTINGS);
        setBounds();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        settingsPanel = new SettingsPanel();
        startCloseButtonsPanel = new ButtonsPanel(Strings.BUTTON_START, Strings.BUTTON_EXIT,
                new StartGameActionListener(this), new CloseAppActionListener());

        add(settingsPanel);
        add(startCloseButtonsPanel);

        pack();
        setVisible(true);
    }
}
