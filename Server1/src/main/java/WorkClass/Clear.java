package WorkClass;

import User.User;
import User.CommandLine;
import CommandControl.Command;

/**
 * Класс очищающий коллекцию.
 */
public class Clear implements Command {
    private CommandLine commandLine;
    private User user;
    public Clear(CommandLine commandLine){
        this.commandLine = commandLine;
    }

    @Override
    public String execute() {
        commandLine.clear(user);
        return "Коллекция очищена!";
    }

    @Override
    public void setUserArgument(User user) {
        this.user = user;
    }
}
