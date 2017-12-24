package controllers.mouselisteners;

import models.CrossZero;
import models.players.Player;
import supports.Designer;
import views.alerts.ErrorAlert;
import views.frames.game.GameField;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Валера on 23.07.2017.
 */
public abstract class SideMouseListener extends MouseAdapter {
    private CrossZero crossZero;  // для продолжения игры
    protected GameField gameField;  // игровое поле на котором отслеживается мышь
    private Player player;    // игрок которому принадлежит слушатель

    SideMouseListener(GameField gameField, CrossZero crossZero) {
        this.gameField = gameField;
        this.crossZero = crossZero;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX() / gameField.getCellWidth();
        int y = e.getY() / gameField.getCellHeight();
        if (gameField.isCellValid(x, y)) {
            gameField.setMark(player, x, y);

            paintSide(x, y, gameField.getGraphics());
            gameField.removeMouseListener(this);
            gameField.setBorder(Designer
                    .getNextTurnInfoBorder(player.getEnemy().getName(), player.getEnemy().getSide()));

            gameField.repaint();  // перерисовка
            crossZero.goOn(player);
        } else ErrorAlert.cellIsNotValid(gameField);
    }
    public abstract void paintSide(int x_table, int y_table, Graphics g);

    public GameField getGameField() {
        return gameField;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
