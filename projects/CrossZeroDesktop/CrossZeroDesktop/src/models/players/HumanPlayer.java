package models.players;

import views.frames.game.GameField;

/**
 * Created by Валера on 22.07.2017.
 *
 * Модель реального игрока
 *
 */
public class HumanPlayer extends Player{
    public HumanPlayer(String name, char side) {
        super(name, side);
    }

    // ход рельного игрока возможно отследить, только при помощи слушателя,
    // поэтому ход рельного игрока - добавление соответствующего слушателя полю игры
    @Override
    public void turn(GameField gameField) {
        gameField.addMouseListener(sideMouseListener);
    }
}