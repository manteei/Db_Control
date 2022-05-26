package CommandControl;
import User.User;
/**
 * Интерфейс, описывающий поведение всех команд.
 */
public interface Command {
    String execute();
    public void setUserArgument(User user);
}
