package CommandControl;

import Collection.LabWork;

/**
 * Интерфейс, описывающий поведение команд с аргументом, с которыми подается объект класса Labwork.
 */
public interface CommandWithLabworks extends Command {
    void setLabworks(LabWork lab);
}
