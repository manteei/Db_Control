package CommandControl;

import Collection.LabWork;


public interface CommandWithArgLab extends Command{
    void getArgument(String arguments);
    void setLabworks(LabWork lab);
}
