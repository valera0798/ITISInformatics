package models;

/**
 * Created by Валера on 08.07.2017.
 *
 * Класс для описания ходов игрока, нужен для:
 *  - передачи координат
 *  - составления журнала ходов игрока
 *
 */
public class Stroke {
    private int x;
    private int y;

    public Stroke(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
