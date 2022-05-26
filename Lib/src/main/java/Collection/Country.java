package Collection;

import java.io.Serializable;

/**
 * Класс с данными о национальности автора.
 */
public enum Country implements Serializable {
    GERMANY("germany"),
    SPAIN("spain"),
    VATICAN("vatican"),
    ITALY("italy"),
    NORTH_KOREA("north_korea");
    String country;
    Country(String country){
        this.country = country;
    }
}
