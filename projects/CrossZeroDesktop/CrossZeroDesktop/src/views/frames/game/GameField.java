package views.frames.game;

import models.Stroke;
import models.players.Player;
import supports.Strings;
import supports.Painter;
import views.frames.AppPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Arrays;

/**
 * Created by Валера on 22.07.2017.
 *
 * Класс поля игры
 *
 */
public class GameField extends AppPanel {
    private int width;  // размеры поля
    private int height;
    private int cellWidth;  // размеры клетки
    private int cellHeight;

    private char[][] field; // внутреннее поле игры
    private byte size;  // количество строк/столбцов поля

    public GameField(JFrame frame, byte size) {
        width = frame.getWidth();
        height = frame.getWidth();
        cellWidth = width / size;
        cellHeight = height / size;
        this.size = size;
        setSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        initField();
    }

    public int getFieldWidth() {
        return width;
    }
    public int getFieldHeight() {
        return height;
    }
    public int getCellWidth() {
        return cellWidth;
    }
    public int getCellHeight() {
        return cellHeight;
    }
    public char[][] getField() {
        return field;
    }
    public byte getFieldSize() {
        return size;
    }

    public void setFieldWidth(int width) {
        this.width = width;
    }
    public void setFieldHeight(int height) {
        this.height = height;
    }
    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }
    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

//----------------------------------------------------Логика поля-----------------------------------------------------//
    // инициализация внутреннего поля
    private void initField() {
        field = new char[size][size];
        for (int i = 0; i < size; i++)
            Arrays.fill(field[i], Strings.DOT_EMPTY);
    }
    // установка марки Player-ом в ячейку [y][x], т.к координаты зерально отображены(не влияет на логику)
    public void setMark(Player player, int x, int y) {
        field[x][y] = player.getSide();
        player
                .getStrokesJournal()
                .addLast(new Stroke(x, y));
    }
    // проверка доступности ячейки для установки марки
    public boolean isCellValid(int x, int y) {
        if (x < 0 || x > size - 1 || y < 0 || y > size - 1) {
            return false;
        } else if (field[x][y] != Strings.DOT_EMPTY) {
            return false;
        } else return true;
    }

//----------------------------------------------------Методы панели---------------------------------------------------//
    // отрисовка поля с учетом обновлений
    @Override
    protected void paintComponent(Graphics g) {
        paintEmptyFiled(g);
        paintUpdates(g);
    }
    // отрисовка пустого поля
    private void paintEmptyFiled(Graphics g) {
        Painter.paintGameField(this, g);
    }
    // отрисовка пустого поля с очисткой внутреннего поля
    public void paintRefreshedFiled(Graphics g) {
        initField();
        paintEmptyFiled(g);
        repaint();
    }
    // отрисовка устанновленных марок
    private void paintUpdates(Graphics g) {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                if (field[i][j] == Strings.DOT_CROSS) {
                    Painter.paintCross(this, i, j, g);
                }
                else if (field[i][j] == Strings.DOT_ZERO) {
                    Painter.paintZero(this, i, j, g);
                }
            }
    }
    // удаление всех слушателей мыши c поля
    public void removeAllMouseListeners() {
        MouseListener[] mouseListeners = getMouseListeners();
        for (MouseListener mouseListener : mouseListeners)
            removeMouseListener(mouseListener);
    }

}
