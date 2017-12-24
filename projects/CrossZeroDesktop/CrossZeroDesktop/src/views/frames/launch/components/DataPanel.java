package views.frames.launch.components;

import models.PlayerSettings;
import supports.Designer;
import supports.Strings;
import views.frames.AppPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by Валера on 03.08.2017.
 *
 * Класс панели с данными.
 * Нужен для:
 *  - установки данных из/в PlayerSettings
 *  - общих настроек дизайна
 */
public abstract class DataPanel extends AppPanel {
    private JLabel labelPlayerName;
    private Container radioButtonContainer;
    private ButtonGroup radioButtonGroupSide;
    protected JRadioButton radioButton1;
    protected JRadioButton radioButton2;
    protected JTextField textField;

    // панель с текстовым полем
    public DataPanel(String titleText, boolean bordered) {
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gbl);
        if (bordered)   // внешний контейнер
            Designer.setCustomBorder(this, titleText, TitledBorder.LEFT);
        else {  // внутренний - дополнительные настройки
            Designer.adjustConstraintsTitle(c);
            labelPlayerName = new JLabel(Designer.getColoredText(Designer.hexFontColor, titleText));
            labelPlayerName.setForeground(Designer.fontColor);

            gbl.setConstraints(labelPlayerName, c);
            add(labelPlayerName);
        }

        textField = new JTextField();
        textField.setForeground(Designer.fontColor);
        textField.setFont(Designer.font);
        Designer.adjustConstraintsTextField(c);
        gbl.setConstraints(textField, c);

        add(textField);

        setVisible(false);
    }
    // панель с двумя радиокнопками
    public DataPanel(String titleText, String radioButton1Text, String radioButton2Text) {
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();

        Designer.setCustomBorder(this, titleText, TitledBorder.LEFT);

        radioButton1 = new JRadioButton();
        radioButton1.setText(Designer.getColoredText(Designer.hexFontColor, radioButton1Text));
        radioButton1.setForeground(Designer.fontColor);
        radioButton1.setSelected(true);

        radioButton2 = new JRadioButton();
        radioButton2.setText(Designer.getColoredText(Designer.hexFontColor, radioButton2Text));
        radioButton2.setForeground(Designer.fontColor);

        radioButtonGroupSide = new ButtonGroup();
        radioButtonGroupSide.add(radioButton1);
        radioButtonGroupSide.add(radioButton2);

        radioButtonContainer = new Container();
        GridBagLayout gblContainer = new GridBagLayout();
        radioButtonContainer.setLayout(gblContainer);

        radioButtonContainer.add(radioButton1);
        radioButtonContainer.add(radioButton2);
        Designer.adjustConstraintsRadioButton(c);
        gblContainer.setConstraints(radioButton1, c);
        gblContainer.setConstraints(radioButton2, c);

        Designer.adjustConstraintsRadioButtonContainer(c);
        gbl.setConstraints(radioButtonContainer, c);

        add(radioButtonContainer);
    }

    // установка данных В PlayerSettings
    public abstract void setData(PlayerSettings inSetting);
    // установка данных ИЗ PlayerSettings
    public abstract void getData(PlayerSettings fromSetting);
}
