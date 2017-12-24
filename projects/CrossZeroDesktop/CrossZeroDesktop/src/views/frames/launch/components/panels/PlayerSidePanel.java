package views.frames.launch.components.panels;

import models.PlayerSettings;
import supports.Strings;
import views.frames.launch.components.DataPanel;

/**
 * Created by Валера on 03.08.2017.
 *
 * Панель настройки выбора стороны, за которую будет вестись игра
 *
 */
public class PlayerSidePanel extends DataPanel {
    public PlayerSidePanel() {
        super(Strings.LABEL_PLAYER_SIDE,
                Strings.RADIO_BUTTON_SIDE_CROSS,
                Strings.RADIO_BUTTON_SIDE_ZERO);
    }

    @Override
    public void setData(PlayerSettings inSetting) {
        if (radioButton1.isSelected())
            inSetting.setSide(Strings.DOT_CROSS);
        else if (radioButton2.isSelected())
            inSetting.setSide(Strings.DOT_ZERO);
    }
    @Override
    public void getData(PlayerSettings fromSetting) {
        switch (fromSetting.getSide()) {
            case Strings.DOT_ZERO:
                radioButton2.setSelected(true);
                break;
            default:
                radioButton1.setSelected(true);
        }
    }
}
