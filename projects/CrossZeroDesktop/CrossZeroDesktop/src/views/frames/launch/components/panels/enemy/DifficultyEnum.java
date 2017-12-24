package views.frames.launch.components.panels.enemy;

import supports.Strings;

/**
 * Created by Валера on 23.07.2017.
 *
 * Класс-перечисление типов сложности ComputerPlayer-а
 *
 */
public enum DifficultyEnum {
    EASY(Strings.DIFFICULTY_EASY), 
    MEDIUM(Strings.DIFFICULTY_MEDIUM), 
    HARD(Strings.DIFFICULTY_HARD);

    private byte byteDifficulty;
    private String stringDifficulty;

    DifficultyEnum(String stringDifficulty) {
        this.stringDifficulty = stringDifficulty;
        switch (stringDifficulty) {
            case Strings.DIFFICULTY_EASY:
                byteDifficulty = 0;
                break;
            case Strings.DIFFICULTY_MEDIUM:
                byteDifficulty = 1;
                break;
            case Strings.DIFFICULTY_HARD:
                byteDifficulty = 2;
                break;
            default:
                byteDifficulty = 0;
        }
    }

    public byte getByteDifficulty() {
        return byteDifficulty;
    }
    public String getStringDifficulty() {
        return stringDifficulty;
    }
}
