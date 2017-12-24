package supports;

import views.frames.game.GameField;

import java.awt.*;

/**
 * Created by Валера on 02.08.2017.
 *
 * Класс отрисовки основных элементов игры
 *
 */
public class Painter {
    public static final int MARGIN = 30;
    public static final int MARGIN_MEDIUM = 12;
    public static final int MARGIN_SMALL = 8;

    public static void paintGameField(GameField gameField, Graphics g) {
        Graphics2D g2D = ((Graphics2D) g);

        gameField.setFieldWidth(gameField.getWidth());
        gameField.setFieldHeight(gameField.getHeight());
        gameField.setCellWidth(gameField.getFieldWidth() / gameField.getFieldSize());
        gameField.setCellHeight(gameField.getFieldHeight() / gameField.getFieldSize());

        g2D.setColor(Color.WHITE);
        g2D.fillRect(0, 0, gameField.getFieldWidth(), gameField.getFieldHeight());

        BasicStroke basicStroke = new BasicStroke(Designer.getLineWidth(gameField.getWidth()));
        g2D.setStroke(basicStroke);

        g2D.setColor(Designer.primaryDarkColor);
        for (int i = 1; i < gameField.getFieldSize(); i++) {
            // горизонатальные линии
            g2D.drawLine(MARGIN_SMALL, i*gameField.getCellHeight(),
                    gameField.getFieldWidth() - MARGIN_SMALL, i*gameField.getCellHeight());
            // вертикальные линии
            g2D.drawLine(i*gameField.getCellWidth(), MARGIN_MEDIUM,
                    i*gameField.getCellWidth(), gameField.getFieldHeight() - MARGIN_SMALL);
        }
    }
    public static void paintCross(GameField gameField, int x, int y, Graphics g) {
        Graphics2D g2D = ((Graphics2D) g);
        BasicStroke basicStroke = new BasicStroke(Designer.getLineWidth(gameField.getWidth()));
        g2D.setStroke(basicStroke);
        g2D.setColor(Designer.crossColor);

        int yTop = y*gameField.getCellHeight() + MARGIN;
        int yBottom = (y + 1)*gameField.getCellHeight() - MARGIN;
        int xLeft = x*gameField.getCellWidth() + MARGIN;
        int xRight = (x + 1)*gameField.getCellWidth() - MARGIN;

        g2D.drawLine(xLeft, yTop, // левый верхний угол
                xRight, yBottom); // првый нижний угол
        g2D.drawLine(xRight, yTop, // правый верхний угол
                xLeft, yBottom); // левый нижний угол
    }
    public static void paintZero(GameField gameField, int x, int y, Graphics g) {
        Graphics2D g2D = ((Graphics2D) g);
        BasicStroke basicStroke = new BasicStroke(Designer.getLineWidth(gameField.getWidth()));
        g2D.setStroke(basicStroke);
        g2D.setColor(Designer.zeroColor);

        g2D.drawOval(x*gameField.getCellWidth() + MARGIN, y*gameField.getCellHeight() + MARGIN,
                gameField.getCellWidth() - 2*MARGIN, gameField.getCellHeight() - 2*MARGIN);
    }

}
