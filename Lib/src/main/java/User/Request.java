package User;

import Collection.LabWork;
import User.User;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 7923661429084687689L;

    private String command;
    private String argument;
    private LabWork labWork;
    private User user;

    public Request() {
    }
    public Request(String command, String argument, LabWork labWork){
        this.command = command;
        this.argument = argument;
        this.labWork = labWork;
    }
    public Request(User user, String command, String argument, LabWork labWork){
        this.user = user;
        this.command = command;
        this.argument = argument;
        this.labWork = labWork;
        }
    public Request(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LabWork getLabwork() {
            return labWork;
        }

        public void setLabWork(LabWork labWork) {
            this.labWork = labWork;
        }


        public String getCommand() {
            return command;
        }

        public void setCommand(String command) {
            this.command = command;
        }

        public String getArgument() {
            return argument;
        }

        public void setArgument(String argument) {
            this.argument = argument;
        }

        @Override
        public String toString() {
            return "User.Request{" +
                    "command='" + command + '\'' +
                    ", argument='" + argument + '\'' +
                    ", labwork=" + labWork +
                    '}';
        }
}
