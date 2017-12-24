package views.frames.launch.components.panels;

import models.PlayerSettings;
import supports.Strings;
import views.frames.launch.components.DataPanel;

/**
 * Created by Валера on 03.08.2017.
 *
 * Панель настройки размера игрового поля
 *
 */
public class FieldSizePanel extends DataPanel {
    public FieldSizePanel() {
        super(Strings.LABEL_FILED_SIZE,
                Strings.RADIO_BUTTON_FIELD_SIZE_3X3,
                Strings.RADIO_BUTTON_FIELD_SIZE_4X4);
    }

    @Override
    public void setData(PlayerSettings inSetting) {
        if (radioButton1.isSelected())
            inSetting.setFieldSize(Strings.FIELD_SIZE_3X3);
        else if (radioButton2.isSelected())
            inSetting.setFieldSize(Strings.FIELD_SIZE_4X4);
    }
    @Override
    public void getData(PlayerSettings fromSetting) {
        switch (fromSetting.getFieldSize()) {
            case Strings.FIELD_SIZE_4X4:
                radioButton2.setSelected(true);
                break;
            default:
                radioButton1.setSelected(true);
        }
    }
}
