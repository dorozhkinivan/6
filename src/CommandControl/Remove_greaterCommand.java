package CommandControl;

import CollectionClasses.Worker;
import CommandControl.CommandTools.CommandTool;
import CommandControl.CommandTools.WorkerRequiredTool;
import Exceptions.WorkerDataInputException;
import UserDataManager.UserConsole;

import java.util.HashSet;

/**
 * Command, that deletes workers, which are greater than selected one
 */
public class Remove_greaterCommand extends CommandAbstract {
    {
        description = "удалить из коллекции все элементы, превышающие заданный";
    }

    @CommandTool
    private WorkerRequiredTool workerRequiredTool;
    private HashSet<Worker> collection;

    public Remove_greaterCommand(UserConsole.Prints prints, HashSet<Worker> collection, UserConsole.Input.InputWorker inputWorker) {
        workerRequiredTool = new WorkerRequiredTool();
        this.prints = prints;
        this.collection = collection;
        workerRequiredTool.setInputWorker(inputWorker);
    }

    /**
     * Runs command from console
     */
    @Override
    public void run() {
        collection.removeIf(worker1 -> worker1.compareTo(workerRequiredTool.getInputWorker().askWorker(collection)) > 0);
    }

    /**
     * Runs command from script file
     */
    @Override
    public void runInScriptMode() throws WorkerDataInputException {
        Worker w = workerRequiredTool.getScriptDataManager().getWorker(collection);
        collection.removeIf(worker1 -> (worker1.compareTo(w) > 0));
    }

    @Override
    public String toString() {
        return "remove_greater";
    }

}
