package views.frames.launch.components.panels.enemy;

import supports.Strings;
import supports.Designer;
import views.frames.AppPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Валера on 22.07.2017.
 *
 * Панель настройки сложности ComputerPlayer-а
 *
 */
public class DifficultyPanel extends AppPanel {
    private JLabel labelDifficulty;
    private JComboBox<String> comboBoxDifficultyTypes;
    private String[] difficultyTypes = new String[] {
            Designer.getColoredText(Designer.hexFontColor, DifficultyEnum.EASY.getStringDifficulty()),
            Designer.getColoredText(Designer.hexFontColor, DifficultyEnum.MEDIUM.getStringDifficulty()),
            Designer.getColoredText(Designer.hexFontColor, DifficultyEnum.HARD.getStringDifficulty())
    };

    public DifficultyPanel() {
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();

        labelDifficulty = new JLabel();
        labelDifficulty.setText(Designer.getColoredText(Designer.hexFontColor, Strings.LABEL_DIFFICULTY));
        labelDifficulty.setForeground(Designer.fontColor);
        Designer.adjustConstraintsTitle(c);
        gbl.setConstraints(labelDifficulty, c);


        comboBoxDifficultyTypes = new JComboBox(difficultyTypes);
        comboBoxDifficultyTypes.setBackground(Designer.primaryColor);
        comboBoxDifficultyTypes.setForeground(Designer.fontColor);
        Designer.adjustConstraintsTextField(c);
        gbl.setConstraints(comboBoxDifficultyTypes, c);

        add(labelDifficulty);
        add(comboBoxDifficultyTypes);

        setVisible(false);
    }

    public byte getByteDifficulty() { return (byte) comboBoxDifficultyTypes.getSelectedIndex(); }
    public void setDifficulty(byte difficulty) {
        comboBoxDifficultyTypes.setSelectedIndex(difficulty);
    }
}
