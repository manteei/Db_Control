package WorkClass;

import User.User;
import User.CommandLine;
import CommandControl.Command;

/**
 * Класс выводит список доступных команд.
 */
public class Help implements Command {
    private CommandLine commandLine;
    private User user;
    public Help(CommandLine commandLine){
        this.commandLine = commandLine;
    }
    @Override
    public String execute(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(commandLine.help());
        return stringBuilder.toString();
    }
    @Override
    public void setUserArgument(User user) {
        this.user = user;
    }
}
