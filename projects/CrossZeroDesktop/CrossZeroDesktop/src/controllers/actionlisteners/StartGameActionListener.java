package controllers.actionlisteners;

import models.CrossZero;
import supports.Strings;
import supports.Designer;
import views.alerts.ErrorAlert;
import views.frames.game.GameFrame;
import views.frames.launch.LaunchFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Валера on 03.08.2017.
 */
public class StartGameActionListener implements ActionListener {
    private LaunchFrame launchFrame;
    private GameFrame gameFrame;

    public StartGameActionListener(LaunchFrame launchFrame) {
        this.launchFrame = launchFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (launchFrame.getPlayerSettings() != null) {
            launchFrame.setVisible(false);
//            if (gameFrame == null)
//                gameFrame = new GameFrame(launchFrame);
//            else {
//                gameFrame.getGameField().paintRefreshedFiled(gameFrame.getGraphics());
//                gameFrame.setVisible(true);
//            }
            gameFrame = new GameFrame(launchFrame);
            switch (launchFrame.getPlayerSettings().getSide()) {
                case Strings.DOT_CROSS:
                    gameFrame.getGameField()
                            .setBorder(Designer.getNextTurnInfoBorder(
                                    launchFrame.getPlayerSettings().getName(),
                                    launchFrame.getPlayerSettings().getSide()));
                    break;
                case Strings.DOT_ZERO:
                    gameFrame.getGameField()
                            .setBorder(Designer.getNextTurnInfoBorder(
                                    launchFrame.getPlayerSettings().getEnemy().getName(),
                                    launchFrame.getPlayerSettings().getEnemy().getSide()));
                    break;
            }
            new CrossZero().play(gameFrame);
        } else ErrorAlert.noNameError(launchFrame);
    }
}
