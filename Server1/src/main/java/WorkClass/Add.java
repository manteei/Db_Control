package WorkClass;


import Collection.LabWork;
import CommandControl.CommandWithLabworks;
import DataBase.DBManager;
import User.CommandLine;
import User.User;

/**
 * Класс добавляющий элемент в коллекцию
 */
public class Add implements CommandWithLabworks {
   private User user;
   private CommandLine commandLine;
   private LabWork labWork;
   public Add(CommandLine commandLine){
       this.commandLine = commandLine;
   }

    /**
     * Метод добавления с консоли.
     * @return
     */
    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        if (DBManager.addLabwork(labWork, user)) {
            synchronized (commandLine) {
                commandLine.insert(labWork);
                commandLine.sortCollection();
            }
            stringBuilder.append("Лабораторная работа добавлена!");
        } else{
            stringBuilder.append("При добавлении произошла ошибка!");
        }
        return stringBuilder.toString();
    }

    @Override
    public void setUserArgument(User user) {
        this.user = user;
    }

    @Override
    public void setLabworks(LabWork lab) {
        this.labWork = lab;
    }
}
