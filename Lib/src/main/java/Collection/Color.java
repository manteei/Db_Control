package Collection;

import java.io.Serializable;

/**
 * Класс с данными о цвете волос автора.
 */
public enum Color implements Serializable {
    GREEN("green"),
    YELLOW("yellow"),
    WHITE("white"),
    BROWN("brown");
    private String color;
    Color(String color){
        this.color = color;
    }
    public String getColor(){
        return color;
    }
}

