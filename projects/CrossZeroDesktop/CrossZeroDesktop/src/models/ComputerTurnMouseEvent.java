package models;

import views.frames.game.GameField;

import java.awt.event.MouseEvent;

/**
 * Created by Валера on 31.07.2017.
 *
 * Класс искусственного создания события мыши: клик с последующим отжатием кнопки
 * Нужен для:
 *  - Поддержания логики работы приложения в момент хода ComputerPlayer
 *
 */
public class ComputerTurnMouseEvent extends MouseEvent {
    public ComputerTurnMouseEvent(GameField gameField, int x, int y) {
        super(gameField,
                MouseEvent.NOBUTTON,
                0,
                MouseEvent.BUTTON1,
                x*gameField.getCellWidth(),
                y*gameField.getCellHeight(),
                1,
                false);
    }
}
