package WorkClass;

import DataBase.DBManager;
import User.User;
import Collection.LabWork;
import User.CommandLine;
import CommandControl.CommandWithLabworks;

import java.util.ArrayDeque;

/**
 * Метод удаляет объекты коллекции, чья координата Y меньше чем у введенного объекта
 */
public class RemoveLower implements CommandWithLabworks {
    private CommandLine commandLine;
    private LabWork lab;
    private User user;
    public RemoveLower(CommandLine commandLine){
        this.commandLine = commandLine;
    }

    /**
     * Метод реализует удаления для консоли
     * @return
     */
    @Override
    public String execute(){
        ArrayDeque<LabWork> removeLab = new ArrayDeque<>();
        for (LabWork labWork: commandLine.getLabworksLogUser(user, commandLine.getLabworks())){
            if (labWork.getCoordinates().getY() < lab.getCoordinates().getY()){
                removeLab.add(labWork);
            }
        }
        removeLab.stream()
                .forEach(labW -> DBManager.removeById(labW.getId()));
        commandLine.getLabworks().removeAll(removeLab);
        return "Все элементы , чья координата Y меньше заданного, удалены из базы!";
    }
    @Override
    public void setUserArgument(User user) {
        this.user = user;
    }


    @Override
    public void setLabworks(LabWork lab) {
        this.lab = lab;
    }
}
