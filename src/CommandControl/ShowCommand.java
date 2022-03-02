package CommandControl;

import CollectionClasses.Worker;
import UserDataManager.UserConsole;

import java.util.Collection;
import java.util.HashSet;
/**
 * Command, that prints all elements in collection
 */
public class ShowCommand extends CommandAbstract {
    {
        description = "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
    private Collection<Worker> collection;
    public ShowCommand(UserConsole.Prints prints, HashSet<Worker> collection ){
        this.prints = prints;
        this.collection = collection;
    }
    /**
     * Runs command from console
     */
    @Override
    public void run() {
        for (Worker worker : collection){
            prints.print(worker.toString());
        }
    }

    /**
     * Runs command from script file
     */
    @Override
    public void runInScriptMode(){
        for (Worker worker : collection){
            prints.print(worker.toString());
        }
    }

    @Override
    public String toString() {
        return "show";
    }
}
