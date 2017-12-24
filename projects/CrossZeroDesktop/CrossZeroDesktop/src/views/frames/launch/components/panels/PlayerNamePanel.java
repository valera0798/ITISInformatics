package views.frames.launch.components.panels;

import models.PlayerSettings;
import supports.Designer;
import views.frames.launch.components.DataPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Валера on 01.08.2017.
 *
 * Панель настройки имени реального игрока
 *
 */
public class PlayerNamePanel extends DataPanel {
    public PlayerNamePanel(String labelName, boolean bordered) {
        super(labelName, bordered);
    }

    public String getPlayerName() { return textField.getText(); }
    public void setPlayerName(String playerName) {
        textField.setText(playerName);
    }

    @Override
    public void setData(PlayerSettings inSetting) {
        inSetting.setName(textField.getText());
    }
    @Override
    public void getData(PlayerSettings fromSetting) {
        textField.setText(fromSetting.getName());
    }
}
