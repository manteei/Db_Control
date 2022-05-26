package WorkClass;


import User.User;
import User.CommandLine;
import CommandControl.Command;

/**
 * Класс выводит минимальный элемент по полю минимальный балл.
 * min_by_minimal_point
 */
public class MinByMinimalPoint implements Command {
    private CommandLine commandLine;
    private User user;
    public MinByMinimalPoint(CommandLine commandLine){
        this.commandLine = commandLine;
    }
    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(commandLine.labMinimalPoint().toString());
        return stringBuilder.toString();
    }
    @Override
    public void setUserArgument(User user) {
        this.user = user;
    }
}
