package WorkClass;

import User.Request;
import CommandControl.Command;
import CommandControl.CommandWithArgLab;
import CommandControl.CommandWithArgument;
import CommandControl.CommandWithLabworks;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

import User.CommandLine;

/**
 * Класс вызывает исполнение команд
 */
public class Commander {
    private HashMap<String, Command> commandNoArgument;
    private HashMap<String, CommandWithArgument> commandWithArgument;
    private HashMap<String, CommandWithLabworks> commandWithLabworks;
    private HashMap<String, CommandWithArgLab> commandWithArgLab;
    private CommandLine commandLine;
    ForkJoinPool pool = new ForkJoinPool(1);


    public Commander(CommandLine commandLine) {
        this.commandLine = commandLine;
        commandNoArgument = new HashMap<>();
        commandWithArgument = new HashMap<>();
        commandWithLabworks = new HashMap<>();
        commandWithArgLab = new HashMap<>();
        this.putCommand();

    }


    private void putCommand() {
        commandNoArgument.put("help", new Help(commandLine));
        commandNoArgument.put("info", new Info(commandLine));
        commandNoArgument.put("show", new Show(commandLine));
        commandWithLabworks.put("add", new Add(commandLine));
        commandWithArgLab.put("update", new Update(commandLine));
        commandWithArgument.put("remove_by_id", new Remove(commandLine));
        commandNoArgument.put("clear", new Clear(commandLine));
        commandNoArgument.put("exit", new Exit());
        commandWithLabworks.put("add_if_max", new AddIfMax(commandLine));
        commandWithLabworks.put("add_if_min", new AddIfMin(commandLine));
        commandWithLabworks.put("remove_lower", new RemoveLower(commandLine));
        commandNoArgument.put("min_by_minimal_point", new MinByMinimalPoint(commandLine));
        commandWithArgument.put("filter_by_minimal_point", new FilterByMinimalPoint(commandLine));
        commandWithArgument.put("filter_greater_than_minimal_point", new GreaterThanPoint(commandLine));

    }

    public String execute(Request request) {
        String response = new String();
        if (commandWithArgument.containsKey(request.getCommand())) {
            CommandWithArgument command;
            command = commandWithArgument.get(request.getCommand());
            command.getArgument(request.getArgument());
            command.setUserArgument(request.getUser());
            response = command.execute();
        } else if (commandNoArgument.containsKey(request.getCommand())) {
            Command command;
            command = commandNoArgument.get(request.getCommand());
            command.setUserArgument(request.getUser());
            response = command.execute();
        } else if (commandWithLabworks.containsKey(request.getCommand())) {
            CommandWithLabworks command;
            command = commandWithLabworks.get(request.getCommand());
            command.setLabworks(request.getLabwork());
            command.setUserArgument(request.getUser());
            response = command.execute();
        }else  if (commandWithArgLab.containsKey(request.getCommand())){
            CommandWithArgLab command;
            command = commandWithArgLab.get(request.getCommand());
            command.getArgument(request.getArgument());
            command.setLabworks(request.getLabwork());
            command.setUserArgument(request.getUser());
            response =  command.execute();
        }
            return response;
    }
}
