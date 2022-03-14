package CommandControl;

import CollectionClasses.Worker;
import UserDataManager.UserConsole;

import java.util.HashSet;
/**
 * Command, that delete all elements in collection.
 */
public class ClearCommand extends CommandAbstract {
    {
        description = "очистить коллекцию";
    }
    private HashSet<Worker> collection;

    public ClearCommand(UserConsole.Prints prints, HashSet<Worker> collection) {
        this.collection = collection;
        this.prints = prints;
    }
    /**
     * Runs command from console
     */
    @Override
    public void run() {
        collection.removeAll(collection);
        prints.print("Коллекция очищена.");
    }
    /**
     * Runs command from script file
     */
    @Override
    public void runInScriptMode() {
        collection.removeAll(collection);
    }

    @Override
    public String toString() {
        return "clear";
    }
}
