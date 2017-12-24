package views.frames.game;

import controllers.actionlisteners.CloseAppActionListener;
import controllers.actionlisteners.RestartGameActionListener;
import supports.Designer;
import views.frames.ButtonsPanel;
import views.frames.PlotFrame;
import views.frames.launch.LaunchFrame;
import supports.Strings;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Валера on 21.07.2017.
 *
 * Класс окна игры
 *
 */
public class GameFrame extends PlotFrame {
    private JLabel linksLabel;
    private GameField gameField;
    private ButtonsPanel restartCloseButtonsPanel;

    public GameFrame(LaunchFrame launchFrame) throws HeadlessException {
        super();

        frameWidth = 400;
        frameHeight = 485;

        this.launchFrame = launchFrame;

        init();
    }

    public GameField getGameField() {
        return gameField;
    }

    @Override
    protected void init() {
        setTitle(Strings.APP_NAME);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds();
        setLayout(new BorderLayout());

        linksLabel = new JLabel(Designer.getColoredSecondaryText(Designer.hexFontSecondaryColor, Strings.LINKS));
        linksLabel.setHorizontalAlignment(JLabel.CENTER);
        add(linksLabel, BorderLayout.PAGE_START);

        gameField = new GameField(this, launchFrame.getFieldSize());
        add(gameField, BorderLayout.CENTER);

        restartCloseButtonsPanel = new ButtonsPanel(Strings.BUTTON_RESTART, Strings.BUTTON_EXIT,
                new RestartGameActionListener(this), new CloseAppActionListener());
        add(restartCloseButtonsPanel, BorderLayout.PAGE_END);

        setVisible(true);
    }
}
