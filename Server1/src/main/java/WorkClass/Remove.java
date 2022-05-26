package WorkClass;

import User.User;
import User.CommandLine;
import CommandControl.CommandWithArgument;

/**
 * Метод удаляет из коллекции элемент с данным id.
 */
public class Remove implements CommandWithArgument {
    private String arguments;
    private CommandLine commandLine;
    private User user;

    public Remove(CommandLine commandLine){
        this.commandLine = commandLine;
    }

    @Override
    public String execute() {
        try {
            int id = Integer.parseInt(this.arguments);
            if (id <= 0) return "Некорректно введен id!";
            if (!commandLine.containsCollection(id, user)) {
                return "У пользователя нет объекта с таким id!";
            }
            if (commandLine.removeId(id)) {
                return "Элемент с id " + id + " успешно удален!";
            }
        } catch (NumberFormatException e) {
            return "Id должен быть числом!";
        }
        return "";
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
