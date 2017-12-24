package models;

import models.players.HumanPlayer;
import models.players.Player;
import views.alerts.GameResultAlert;
import views.frames.game.GameFrame;
import views.frames.game.GameField;
import supports.Strings;
import controllers.mouselisteners.CrossSideMouseListener;
import controllers.mouselisteners.SideMouseListener;
import controllers.mouselisteners.ZeroSideMouseListener;

/**
 * Created by Валера on 23.07.2017.
 *
 * Класс игры - все действия относящиеся к игровому процессу
 *
 */
public class CrossZero {
    private static GameField gameField; // поле игры

//-----------------------------------------------Логика класса--------------------------------------------------------//
    public void play(GameFrame gameFrame) {
        gameField = gameFrame.getGameField();

        PlayerSettings playerSettings = gameFrame.getLaunchFrame().getPlayerSettings();
        // объекты Player нужны только существуют только во время процесса игры
        Player player = new HumanPlayer(playerSettings.getName(), playerSettings.getSide());
        Player enemy = playerSettings.getEnemy();
        player.setEnemy(enemy);
        enemy.setEnemy(player);

        initSideMouseListeners(player, enemy);

        // выбор игрока идущего первым
        if (player.getSide() == Strings.DOT_CROSS) {
            player.turn(gameField);
        } else {
            enemy.turn(gameField);
        }
    }
    // В зависимости от текущего положения выносит вердикт о резуьтате игры. Передача хода другому игроку
    public void goOn(Player player) {
        gameField.removeAllMouseListeners();
        Stroke currentStroke = player.getStrokesJournal().getLast();
        if (checkWin(player, currentStroke.getX(), currentStroke.getY())) {
            GameResultAlert.win(gameField, player);
        } else if (isDraw()) {
            GameResultAlert.draw(gameField);
        } else
            player.getEnemy().turn(gameField);
    }

//-----------------------------------------------Проверки исхода------------------------------------------------------//
    // проверка только тех линий, на пересечении которых игрок поставил пометку
    // быстрее полной проверки поля: O(4n) < O(n^2)

    // победит ли dotType, если поставит в (x_lastTurn, y_lastTurn)
    public static boolean checkWin(Player player, int x_lastTurn, int y_lastTurn) {
        boolean lastTurnIsFake = false;
        // установить маркер для проверки
        if (gameField.getField()[x_lastTurn][y_lastTurn] == Strings.DOT_EMPTY) {
            gameField.getField()[x_lastTurn][y_lastTurn] = player.getSide();
            lastTurnIsFake = true;
        }

        boolean isWon = false;
        if (x_lastTurn == y_lastTurn) isWon = checkMainDiagonal(player.getSide());
        if (y_lastTurn == gameField.getFieldSize() - 1 - x_lastTurn && !isWon)
            isWon = checkSideDiagonal(player.getSide());
        if (!isWon) isWon = checkDotVerticalAndHorizontal(player.getSide(), x_lastTurn, y_lastTurn);

        // снять маркер после проверки
        if (lastTurnIsFake) gameField.getField()[x_lastTurn][y_lastTurn] = Strings.DOT_EMPTY;

        return isWon;
    }
    private static boolean checkDotVerticalAndHorizontal(char dotType, int x_lastTurn, int y_lastTurn) {
        boolean isWon = checkDotHorizontal(dotType, x_lastTurn);
        if (!isWon) isWon = checkDotVertical(dotType, y_lastTurn);
        return isWon;
    }
    private static boolean checkDotVertical(char dotType, int y) {
        boolean isWon = true;
        for (int i = 0; i < gameField.getFieldSize() && isWon; i++)
            if (gameField.getField()[i][y] != dotType) isWon = false;
        return isWon;
    }
    private static boolean checkDotHorizontal(char dotType, int x) {
        boolean isWon = true;
        for (int i = 0; i < gameField.getFieldSize() && isWon; i++)
            if (gameField.getField()[x][i] != dotType) isWon = false;
        return isWon;
    }
    private static boolean checkMainDiagonal(char dotType) {
        boolean isWon = true;
        for (int i = 0; i < gameField.getFieldSize() && isWon; i++)
            if (gameField.getField()[i][i] != dotType) isWon = false;
        return isWon;
    }
    private static boolean checkSideDiagonal(char dotType) {
        boolean isWon = true;
        for (int i = 0; i < gameField.getFieldSize() && isWon; i++)
            if (gameField.getField()[i][gameField.getFieldSize() - 1 - i] != dotType) isWon = false;
        return isWon;
    }

    // ничья
    public boolean isDraw() {
        for (int i = 0; i < gameField.getFieldSize(); i++)
            for (int j = 0; j < gameField.getFieldSize(); j++)
                if (gameField.getField()[i][j] == Strings.DOT_EMPTY) return false;
        return true;
    }

//----------------------------------------------Прикладные методы-----------------------------------------------------//
    // настройка слушателей мыши для каждого Player
    private void initSideMouseListeners(Player player, Player enemy) {
        SideMouseListener crossSideMouseListener =
                new CrossSideMouseListener(gameField, this);
        SideMouseListener zeroSideMouseListener =
                new ZeroSideMouseListener(gameField, this);
        switch (player.getSide()) {
            case Strings.DOT_CROSS:
                player.setSideMouseListener(crossSideMouseListener);
                crossSideMouseListener.setPlayer(player);
                enemy.setSideMouseListener(zeroSideMouseListener);
                zeroSideMouseListener.setPlayer(enemy);
                break;
            case Strings.DOT_ZERO:
                player.setSideMouseListener(zeroSideMouseListener);
                zeroSideMouseListener.setPlayer(player);
                enemy.setSideMouseListener(crossSideMouseListener);
                crossSideMouseListener.setPlayer(enemy);
                break;
        }
    }

}
