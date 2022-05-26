package OrgClient;

import Collection.LabWork;
import Reader.FillerFields;
import User.User;
import User.Request;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ForkJoinPool;

public class ComandReader {
    private FillerFields fillerFields;
    public ComandReader() {
        this.fillerFields = new FillerFields();
    }

    public Request getRequestFromCommand(String command, User user) {
        String[] com = command.trim().toLowerCase(Locale.ROOT).split("\\s+");
        String[] args = Arrays.copyOfRange(com, 1, com.length);

        Request request = new Request();
        switch (com[0]) {
            case "show":
                request.setCommand("show");
                break;
            case "help":
                request.setCommand("help");
                break;
            case "info":
                request.setCommand("info");
                break;
            case "clear":
                request.setCommand("clear");
                break;
            case "min_by_minimal_point":
                request.setCommand("min_by_minimal_point");
                break;
            case "exit":
                request.setCommand("exit");
                break;
            case "add":
                request.setCommand("add");
                request.setLabWork(new LabWork(fillerFields.readName(), fillerFields.readCoordinates(), LocalDate.now(), fillerFields.readMinimalPoint(), fillerFields.readDifficulty(), fillerFields.readPerson(), user.getUsername()));
                break;
            case "add_if_max":
                request.setCommand("add_if_max");
                request.setLabWork(new LabWork(fillerFields.readName(), fillerFields.readCoordinates(), LocalDate.now(), fillerFields.readMinimalPoint(), fillerFields.readDifficulty(), fillerFields.readPerson(), user.getUsername()));
                break;
            case "add_if_min":
                request.setCommand("add_if_min");
                request.setLabWork(new LabWork(fillerFields.readName(), fillerFields.readCoordinates(), LocalDate.now(), fillerFields.readMinimalPoint(), fillerFields.readDifficulty(), fillerFields.readPerson(), user.getUsername()));
                break;
            case "remove_by_id":
                request.setCommand("remove_by_id");
                if (args.length == 0) {
                    request.setArgument("no_args");
                    break;
                }
                request.setArgument(args[0]);
                break;
            case "remove_lower":
                request.setCommand("remove_lower");
                request.setLabWork(new LabWork(fillerFields.readName(), fillerFields.readCoordinates(), LocalDate.now(), fillerFields.readMinimalPoint(), fillerFields.readDifficulty(), fillerFields.readPerson(), user.getUsername()));
                break;
            case "filter_by_minimal_point":
                request.setArgument(args[0]);
                request.setCommand("filter_by_minimal_point");
                break;
            case "filter_greater_than_minimal_point":
                request.setArgument(args[0]);
                request.setCommand("filter_greater_than_minimal_point");
                break;
            case "update":
                request.setCommand("update");
                if (args.length == 0) {
                    request.setArgument("no_args");
                    break;
                }
                request.setArgument(args[0]);
                request.setLabWork(new LabWork(fillerFields.readName(), fillerFields.readCoordinates(), LocalDate.now(), fillerFields.readMinimalPoint(), fillerFields.readDifficulty(), fillerFields.readPerson(), user.getUsername()));
                break;
            default:
                request.setCommand("no_command");
                System.err.println("Такой команды нет!");
                break;

        }
        return request;

    }
    public Request getRequestFromScript(String command, LabWork labWork, User user) {
        String[] com = command.trim().toLowerCase(Locale.ROOT).split("\\s+");
        String[] args = Arrays.copyOfRange(com, 1, com.length);
        Request request = new Request();
        labWork.setUsername(user.getUsername());
        switch (com[0]) {
            case "add":
                request.setCommand("add");
                request.setLabWork(labWork);
                break;
            case "add_if_max":
                request.setCommand("add_if_max");
                request.setLabWork(labWork);
                break;
            case "add_if_min":
                request.setCommand("add_if_min");
                request.setLabWork(labWork);
                break;
            case "update":
                if (args.length == 0) {
                    request.setArgument("no_args");
                    break;
                }
                request.setArgument(args[0]);
                request.setCommand("update");
                request.setLabWork(labWork);
                break;
            case "remove_lower":
                request.setCommand("remove_lower");
                request.setLabWork(labWork);
                break;
            default:
                request.setCommand("no_command");
                System.err.println("Такой команды нет!");
                break;

        }

        return request;

    }


}
