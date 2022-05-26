package WorkClass;

import User.User;
import User.CommandLine;
import CommandControl.Command;

/**
 * Класс выводит информацию о коллекции.
 */
public class Info implements Command {
    private CommandLine commandLine;
    private User user;
    public Info(CommandLine commandLine){
        this.commandLine = commandLine;
    }
    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(commandLine.info());
        return stringBuilder.toString();
    }
    @Override
    public void setUserArgument(User user) {
        this.user = user;
    }


}
