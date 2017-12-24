package views.frames.launch.components.panels;

import models.PlayerSettings;
import models.players.ComputerPlayer;
import models.players.HumanPlayer;
import supports.Strings;
import views.frames.launch.components.DataPanel;
import views.frames.launch.components.panels.enemy.DifficultyEnum;
import views.frames.launch.components.panels.enemy.DifficultyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Валера on 03.08.2017.
 *
 * Панель выбора противника и его настроек
 *
 */
public class EnemyTypePanel extends DataPanel {
    private PlayerNamePanel enemyNamePanel;
    private DifficultyPanel difficultyPanel;

    public EnemyTypePanel() {
        super(Strings.LABEL_ENEMY_TYPE,
                Strings.RADIO_BUTTON_HUMAN_PLAYER_NAME,
                Strings.RADIO_BUTTON_COMPUTER_PLAYER_NAME);
        radioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficultyPanel.setVisible(false);
                enemyNamePanel.setVisible(true);
                repaint();
            }
        });
        radioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficultyPanel.setVisible(true);
                enemyNamePanel.setVisible(false);
                repaint();
            }
        });

        // TODO исправить выравнивание
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        enemyNamePanel = new PlayerNamePanel(Strings.LABEL_PLAYER_NAME_SECOND, false);
        enemyNamePanel.setVisible(true);
        difficultyPanel = new DifficultyPanel();

        add(difficultyPanel, BorderLayout.PAGE_END);
        add(enemyNamePanel, BorderLayout.PAGE_END);
    }

    public PlayerNamePanel getEnemyNamePanel() {
        return enemyNamePanel;
    }

    @Override
    public void setData(PlayerSettings inSetting) {
        char enemySide = Strings.DOT_ZERO;
        switch (inSetting.getSide()) {
            case Strings.DOT_CROSS:
                enemySide = Strings.DOT_ZERO;
                break;
            case Strings.DOT_ZERO:
                enemySide = Strings.DOT_CROSS;
                break;
        }

        if (radioButton1.isSelected())
            inSetting
                    .setEnemy(new HumanPlayer(enemyNamePanel.getPlayerName(), enemySide));
        else if (radioButton2.isSelected())
            inSetting
                    .setEnemy(new ComputerPlayer(enemySide, difficultyPanel.getByteDifficulty()));
    }
    @Override
    public void getData(PlayerSettings fromSetting) {
        String enemyName = (fromSetting.getEnemy() == null) ? "" : fromSetting.getEnemy().getName();
        switch (enemyName) {
            case Strings.RADIO_BUTTON_COMPUTER_PLAYER_NAME:
                radioButton2.setSelected(true);
                difficultyPanel.setDifficulty(((ComputerPlayer) fromSetting.getEnemy()).getDifficulty());
                difficultyPanel.setVisible(true);
                enemyNamePanel.setVisible(false);
                repaint();
                break;
            default:
                radioButton1.setSelected(true);
                difficultyPanel.setDifficulty(DifficultyEnum.EASY.getByteDifficulty());
                difficultyPanel.setVisible(false);
                enemyNamePanel.setPlayerName(enemyName);
                enemyNamePanel.setVisible(true);
                repaint();
                break;
        }
    }
}
