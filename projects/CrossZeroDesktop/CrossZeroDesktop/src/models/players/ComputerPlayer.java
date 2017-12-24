package models.players;

import models.CrossZero;
import models.Stroke;
import supports.Strings;
import models.ComputerTurnMouseEvent;
import views.frames.game.GameField;

/**
 * Created by Валера on 22.07.2017.
 *
 * Модель бота. Всё что касается искуственного интеллекта
 *
 */
public class ComputerPlayer extends Player {
    private byte difficulty;

    public ComputerPlayer(char side, byte difficulty) {
        super(Strings.RADIO_BUTTON_COMPUTER_PLAYER_NAME, side);
        this.difficulty = difficulty;
    }

    // в зависимости от выбранной игроком сложности, выбирается соответствующая тактика игры
    @Override
    public void turn(GameField gameField) {
        gameField.addMouseListener(sideMouseListener);
        switch (difficulty) {
            case 0:
                computerTurnEasyLevel(gameField);
                break;
            case 1:
                computerTurnMiddleLevel(gameField);
                break;
            case 2:
                // TODO сложный уровень игры
                computerTurnHardLevel(gameField);
                break;
        }
    }

    public byte getDifficulty() {
        return difficulty;
    }

//-----------------------------------------------Уровни игры----------------------------------------------------------//
    // лёгкий уровень - ход в произвольную доступную клетку поля
    private void computerTurnEasyLevel(GameField gameField) {
        byte x, y;
        do {
            x = (byte) (Math.random()* gameField.getFieldSize());
            y = (byte) (Math.random()* gameField.getFieldSize());
        } while (!sideMouseListener.getGameField().isCellValid(x, y));

        sideMouseListener.mouseReleased(new ComputerTurnMouseEvent(gameField, x, y));
    }
    // средний уровень - блокировка победы противника, выигрыш при возможности, иначе логка computerTurnEasyLevel
    // при возможности занятие центральной позиции
    private void computerTurnMiddleLevel(GameField gameField) {
        doNeatGame(gameField);
    }
    // сложный уровень
    private void computerTurnHardLevel(GameField gameField) {
        if (getSide() == Strings.DOT_CROSS) {    // ходит первым
            if (isCenterEmpty(gameField)) {
                // занять центр
                sideMouseListener
                        .mouseReleased(new ComputerTurnMouseEvent(gameField,
                                gameField.getFieldSize()/2, gameField.getFieldSize()/2));
            } else {
                //  противник в углу
                if (enemyIsInCorner(gameField)) doNeatGame(gameField);
                // если противник не занял угловую позицию
                else enemyDidNotStayInCorner(gameField);
            }
        } else   // ходит вторым
            doNeatGame(gameField);
    }
//    private void computerTurnHardLevel(GameField gameField) {
//        gameField.addMouseListener(sideMouseListener);
//        if (getSide() == Strings.DOT_CROSS) {    // ходит первым
//            if (gameField.getField()[gameField.SIZE/2][gameField.SIZE/2] == Strings.DOT_EMPTY)
//                gameField.setMark(this, gameField.SIZE/2, gameField.SIZE/2);   // занять центр
//            else {
//                if (enemyIsInCorner(getEnemy().getSide())) { //  противник в углу
//                    Stroke winStroke = getWinStroke();
//                    if (winStroke != null) {    // если есть победный ход
//                        sideMouseListener.mouseReleased(new ComputerTurnMouseEvent(
//                                gameField,
//                                winStroke.getX(),
//                                winStroke.getY())
//                        );
//                    }
//                    else {
//                        Stroke blockStroke = getBlockStroke();
//                        if (blockStroke != null) {  // если могу блокировать
//                            sideMouseListener.mouseReleased(new ComputerTurnMouseEvent(
//                                    gameField,
//                                    winStroke.getX(),
//                                    winStroke.getY())
//                            );
//                            setMark(computerSide, blockStroke.getX(), blockStroke.getY());
//                        } else return computerTurnEasyLevel();
//                    }
//                } else {    // если противник не занял угловую позицию
//                    Stroke lastMoveOfPlayer = playerStrokesJournal.getLast();
//                }
//            }
//        } else {    // ходит вторым
//
//        }
//    }

//-----------------------------------------------Искуственный интеллект-----------------------------------------------//

//-------------------------------------Проверки, получение позиций победы, блокировки---------------------------------//
    private boolean enemyIsInCorner(GameField gameField) {
        boolean isInCorner = false;
        if (gameField.getField()[0][0] == getEnemy().getSide()) isInCorner = true;
        else if (gameField.getField()[0][gameField.getFieldSize() - 1] == getEnemy().getSide())
            isInCorner = true;
        else if (gameField.getField()[gameField.getFieldSize() - 1][0] == getEnemy().getSide())
            isInCorner = true;
        else if (gameField.getField()[gameField.getFieldSize() - 1][gameField.getFieldSize() - 1] ==
                getEnemy().getSide())
            isInCorner = true;
        return isInCorner;
    }
    // возращает boolean - есть ли марка в центре поля
    private boolean isCenterEmpty(GameField gameField) {
        return gameField.getField()[gameField.getFieldSize()/2][gameField.getFieldSize()/2] == Strings.DOT_EMPTY;
    }

    // возвращает выигрышную позицию. если такой нет, то вернет null
    private Stroke getWinStroke(GameField gameField)  {
        for (int x = 0; x < gameField.getFieldSize(); x++)
            for (int y = 0; y < gameField.getFieldSize(); y++)
                if (CrossZero.checkWin(this, x, y)) return new Stroke(x, y);
        return null;
    }
    // возвращает позицию блокировки победы противника. если такой нет, то вернет null
    private Stroke getBlockStroke(GameField gameField) {
        for (int x = 0; x < gameField.getFieldSize(); x++)
            for (int y = 0; y < gameField.getFieldSize(); y++)
                if (CrossZero.checkWin(getEnemy(), x, y)) return new Stroke(x, y);
        return null;
    }

//------------------------------------------------Стратегии игры------------------------------------------------------//
    private void enemyDidNotStayInCorner(GameField gameField) {
        int x = 0, y = 0;
        Stroke lastMoveOfPlayer = getEnemy().getStrokesJournal().getLast();
        switch (lastMoveOfPlayer.getX()) {
            case 0:
                x = lastMoveOfPlayer.getX();
                y = lastMoveOfPlayer.getY() - 1;
                break;
            case 1:
                if (lastMoveOfPlayer.getY() == 1) {
                    x = lastMoveOfPlayer.getX() - 1;
                    y = lastMoveOfPlayer.getY();
                } else {
                    x = lastMoveOfPlayer.getX() + 1;
                    y = lastMoveOfPlayer.getY();
                }
                break;
            case 2:
                x = lastMoveOfPlayer.getX();
                y = lastMoveOfPlayer.getY() + 1;
                break;
        }
        sideMouseListener.mouseReleased(new ComputerTurnMouseEvent(gameField, x, y));

        Stroke winStroke = getWinStroke(gameField);
        if (winStroke != null)
            sideMouseListener
                    .mouseReleased(new ComputerTurnMouseEvent(gameField, winStroke.getX(), winStroke.getY()));
        else {
            Stroke lastMoveOfComputer = getStrokesJournal().getLast();
            switch (lastMoveOfComputer.getX()) {
                case 0:
                    if (getEnemy().getStrokesJournal().getFirst().getX() == 0) {
                        x = gameField.getFieldSize() - 1;
                        y = getEnemy().getStrokesJournal().getFirst().getY() - 1;
                    } else {
                        x = getEnemy().getStrokesJournal().getFirst().getX() - 1;
                        y = gameField.getFieldSize() - 1;
                    }
                    break;
                case 2:
                    if (getEnemy().getStrokesJournal().getFirst().getX() == gameField.getFieldSize() - 1) {
                        x = 0;
                        y = getEnemy().getStrokesJournal().getFirst().getY() + 1;
                    } else {
                        x = getEnemy().getStrokesJournal().getFirst().getX() + 1;
                        y = 0;
                    }
                    break;
            }
        }
        sideMouseListener.mouseReleased(new ComputerTurnMouseEvent(gameField, x, y));
    }
    // блокировка выигрышных ходов противника, выигрыш если воможно
    private void doNeatGame(GameField gameField) {
        if (isCenterEmpty(gameField))
            sideMouseListener
                    .mouseReleased(new ComputerTurnMouseEvent(gameField,
                            gameField.getFieldSize()/2, gameField.getFieldSize()/2));
        else {
            Stroke winStroke = getWinStroke(gameField);
            if (winStroke != null) {    // если есть победный ход
                sideMouseListener
                        .mouseReleased(new ComputerTurnMouseEvent(gameField, winStroke.getX(), winStroke.getY()));
            } else {
                Stroke blockStroke = getBlockStroke(gameField);
                if (blockStroke != null) {  // если могу блокировать
                    sideMouseListener
                            .mouseReleased(new ComputerTurnMouseEvent(gameField, blockStroke.getX(), blockStroke.getY()));
                } else computerTurnEasyLevel(gameField);
            }
        }
    }
}
