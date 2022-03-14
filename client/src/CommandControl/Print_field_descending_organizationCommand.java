package CommandControl;

import CollectionClasses.Worker;
import CommandControl.CommandTools.ArgumentsTool;
import CommandControl.CommandTools.CommandTool;
import Exceptions.CommandArgumentException;
import Exceptions.WorkerDataInputException;
import UserDataManager.UserConsole;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
/**
 * Command, that prints the values of  organization of all elements in descending order
 */
public class Print_field_descending_organizationCommand extends CommandAbstract{
    {
        description = "вывести значения поля organization всех элементов в порядке убывания";
    }
    private HashSet<Worker> collection;
    public Print_field_descending_organizationCommand(UserConsole.Prints prints, HashSet<Worker> collection){
        this.prints = prints;
        this.collection = collection;
    }
    @Override
    public String toString() {
        return "print_field_descending_organization";
    }
    /**
     * Runs command from console
     */
    @Override
    public void run() {
        collection.stream().sorted(Comparator.reverseOrder()).forEach(worker -> prints.print(worker.getOrganization().toString()));
    }

    /**
     * Runs command from script file
     */
    @Override
    public void runInScriptMode() throws WorkerDataInputException, CommandArgumentException {
        collection.stream().sorted(Comparator.reverseOrder()).forEach(worker -> prints.print(worker.getOrganization().toString()));
    }
}
