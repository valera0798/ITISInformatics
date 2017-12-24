package models.players;

import models.Stroke;

import views.frames.game.GameField;
import controllers.mouselisteners.SideMouseListener;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Валера on 22.07.2017.
 *
 * Модель игрока
 *
 */
public abstract class Player {
    protected String name;
    protected char side;
    protected Deque<Stroke> strokesJournal;
    protected Player enemy;
    protected SideMouseListener sideMouseListener;

    protected Player(String name, char side) {
        this.name = name;
        this.side = side;
        strokesJournal = new ArrayDeque<>();
    }

    public abstract void turn(GameField gameField);

    public String getName() {
        return name;
    }
    public char getSide() { return side; }
    public Deque<Stroke> getStrokesJournal() {
        return strokesJournal;
    }
    public Player getEnemy() {
        return enemy;
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }
    public void setSideMouseListener(SideMouseListener sideMouseListener) {
        this.sideMouseListener = sideMouseListener;
    }
}
