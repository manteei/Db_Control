package CommandControl;

/**
 * Интерфейс, описывающий поведение команд с аргументом.
 */
public interface CommandWithArgument extends Command {
    void getArgument(String arguments);
}
