package controllers.actionlisteners;

import views.alerts.ConfirmAlert;
import views.frames.game.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Валера on 03.08.2017.
 */
public class RestartGameActionListener implements ActionListener {
    private GameFrame gameFrame;

    public RestartGameActionListener(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ConfirmAlert.confirmRestartGame(gameFrame);
    }
}
