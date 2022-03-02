package CommandControl;

import CollectionClasses.Worker;
import CommandControl.CommandTools.CommandTool;
import CommandControl.CommandTools.WorkerRequiredTool;
import Exceptions.WorkerDataInputException;
import UserDataManager.UserConsole;

import java.util.HashSet;
/**
 * Command, that add worker to collection.
 */
public class AddCommand extends CommandAbstract {
    {
        description = "добавить новый элемент в коллекцию";
    }
    @CommandTool
    private WorkerRequiredTool workerRequiredTool;

    private HashSet<Worker> collection;

    public AddCommand(UserConsole.Prints prints, HashSet<Worker> collection, UserConsole.Input.InputWorker inputWorker) {
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
        collection.add(workerRequiredTool.getInputWorker().askWorker(collection));
    }

    /**
     * Runs command from script file
     */
    @Override
    public void runInScriptMode() throws WorkerDataInputException {
        System.out.println(workerRequiredTool.getScriptDataManager());
        collection.add(workerRequiredTool.getScriptDataManager().getWorker(collection));
    }

    @Override
    public String toString() {
        return "add";
    }
}
