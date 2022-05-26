package Collection;

import java.io.Serializable;

/**
 * Класс с данными о сложности лабораторной работы.
 */
public enum Difficulty implements Serializable {
    EASY("easy"),
    HARD("hard"),
    IMPOSSIBLE("impossible"),
    HOPELESS("hopeless");
    String difficulty;
    Difficulty(String difficulty){
        this.difficulty = difficulty;
    }

}
