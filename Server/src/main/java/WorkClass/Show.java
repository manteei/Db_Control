package WorkClass;

import User.User;
import User.CommandLine;
import CommandControl.Command;

/**
 * Класс выводит информацию о коллекции.
 */
public class Show implements Command {
    private CommandLine commandLine;
    private User user;
    public Show(CommandLine commandLine){
        this.commandLine = commandLine;
    }

    /**
     * Метод вывода информации
     * @return
     */
    @Override
    public String execute() {
        return commandLine.show();
    }
    @Override
    public void setUserArgument(User user) {
        this.user = user;
    }

}
