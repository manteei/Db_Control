package WorkClass;

import User.User;
import User.CommandLine;
import CommandControl.CommandWithArgument;


/**
 * Класс выводит элементы, чье поле минимального балла больше указанного.
 * filter_greater_than_minimal_point
 */
public class GreaterThanPoint implements CommandWithArgument {
    private String arguments;
    private CommandLine commandLine;
    private User user;
    public GreaterThanPoint(CommandLine commandLine){
        this.commandLine = commandLine;
    }
    @Override
    public String execute() {
        Double minPoint;
        String result;
        try{
            minPoint = Double.parseDouble(arguments);
            result = commandLine.greaterLab(minPoint);
        if (result.equals("")) {
                return "Таких нет элементов нет!";
        } else return result;
        } catch (ArrayIndexOutOfBoundsException e){
            return "Не указан аргумент команды!\n";
        }
    }

    @Override
    public void setUserArgument(User user) {
        this.user = user;
    }


    @Override
    public void getArgument(String arguments) {
        this.arguments = arguments;
    }
}
