package CommandControl;

import CollectionClasses.Worker;
import CommandControl.CommandTools.ArgumentsTool;
import CommandControl.CommandTools.CommandTool;
import Exceptions.CommandArgumentException;
import Exceptions.WorkerDataInputException;
import UserDataManager.UserConsole;

import java.util.HashSet;
import java.util.regex.Pattern;
/**
 * Command, that prints workers with names, that contains selected string.
 */
public class Filter_contains_nameCommand extends CommandAbstract{
    {
        description = "вывести элементы, значение поля name которых содержит заданную подстроку";
    }
    private HashSet<Worker> collection;
    @CommandTool
    private ArgumentsTool argumentsTool;
    public Filter_contains_nameCommand(UserConsole.Prints prints, HashSet<Worker> collection, UserConsole.Input.InputCommand inputCommand){
        argumentsTool = new ArgumentsTool();
        this.prints = prints;
        this.collection = collection;
        argumentsTool.setInputCommand(inputCommand);
    }
    /**
     * Runs command from console
     */
    @Override
    public void run() {
        String pattern = argumentsTool.getArguments()[0];
        if (argumentsTool.getArguments() != null && argumentsTool.getArguments().length > 0) {
            for (Worker i : collection){
                if (i.getName().contains(pattern))
                    prints.print(i.toString());
            }
            return;
        }
        argumentsTool.setArguments(argumentsTool.getInputCommand().askArguments(toString()).getArguments());
        run();
    }

    /**
     * Runs command from script file
     */
    @Override
    public void runInScriptMode() throws WorkerDataInputException, CommandArgumentException {
        String pattern = argumentsTool.getArguments()[0];
        if (argumentsTool.getArguments() != null && argumentsTool.getArguments().length > 0) {
            for (Worker i : collection){
                if (i.getName().contains(pattern))
                    prints.print(i.toString());
            }
            return;
        }
        throw new CommandArgumentException();
    }

    @Override
    public String toString() {
        return "filter_contains_name";
    }
}
