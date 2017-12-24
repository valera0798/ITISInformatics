package controllers.mouselisteners;

import models.CrossZero;
import views.frames.game.GameField;
import supports.Painter;

import java.awt.*;

/**
 * Created by Валера on 23.07.2017.
 */
public class CrossSideMouseListener extends SideMouseListener {
    public CrossSideMouseListener(GameField gameField, CrossZero crossZero) {
        super(gameField, crossZero);
    }

    @Override
    public void paintSide(int x, int y, Graphics g) {
        Painter.paintCross(gameField, x, y, g);
    }
}
