package File;

import Collection.LabWork;
import Reader.FillerFields;
import java.time.LocalDate;
/**
 * Класс формирует объекты коллекции
 */
public class CreationLabwork {
    static int id = 1;
    private int newId = 1;
    private FillerFields fillerFields = new FillerFields();

    /**
     * Метод создает объект лабораторная работа
     * @return
     */
    public LabWork creationLabwork(){
        return new LabWork(getId(), fillerFields.readName(), fillerFields.readCoordinates(), LocalDate.now(), fillerFields.readMinimalPoint(), fillerFields.readDifficulty(), fillerFields.readPerson());
    }

    /**
     * Вспомогательный метод, позволяет при обновлении элемента не менять его id
     * @param oldId
     * @return
     */
    public LabWork updateLabwork(int oldId){
        return new LabWork(oldId, fillerFields.readName(), fillerFields.readCoordinates(), LocalDate.now(), fillerFields.readMinimalPoint(), fillerFields.readDifficulty(), fillerFields.readPerson());
    }

    /**
     * Метод возвращает id увеличенный на 1;
     * @return
     */
    public static int getId() {
        return id++;
    }

    /**
     * Сеттер для id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод позволяет получить уникальный id
     * @param commandLine
     * @return
     */

}
