package WorkClass;

import User.User;
import CommandControl.Command;

/**
 * Класс выхода из программы.
 */
public class Exit implements Command {
    private User user;
    @Override
    public String execute() {
        return "Клиент отключился!";
    }
    @Override
    public void setUserArgument(User user) {
        this.user = user;
    }
}
