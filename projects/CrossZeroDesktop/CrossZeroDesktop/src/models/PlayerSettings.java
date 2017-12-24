package models;

import models.players.Player;

/**
 * Created by Валера on 22.07.2017.
 *
 * Класс пользовательских настроек формируется на основе данных LaunchFrame
 * Нужен для:
 *  - передачи данных игре
 * Каждое поле является настройкой
 *
 */
public class PlayerSettings {
    private String name;
    private Player enemy;
    private char side;
    private byte fieldSize;

    public void setName(String name) {
        this.name = name;
    }
    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }
    public void setSide(char side) {
        this.side = side;
    }
    public void setFieldSize(byte fieldSize) {
        this.fieldSize = fieldSize;
    }

    public String getName() {
        return name;
    }
    public Player getEnemy() {
        return enemy;
    }
    public char getSide() {
        return side;
    }
    public byte getFieldSize() {
        return fieldSize;
    }
}
