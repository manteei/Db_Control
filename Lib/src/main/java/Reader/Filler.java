package Reader;

import Collection.*;

/**
 * Интерфейс описывающий чтение полей класса Labwork для чтения с консоли и со скрипта.
 */
public interface Filler {
    String readName();
    long readX();
    long readY();
    Coordinates readCoordinates();
    Difficulty readDifficulty();
    String readAuthorName();
    Double readWeight();
    String readPassportId();
    Color readColor();
    Country readNationality();
    Person readPerson();
}
