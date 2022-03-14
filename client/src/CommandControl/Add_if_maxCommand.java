package CommandControl;

import CollectionClasses.Worker;
import CommandControl.CommandTools.CommandTool;
import CommandControl.CommandTools.WorkerRequiredTool;
import Exceptions.CommandArgumentException;
import Exceptions.WorkerDataInputException;
import UserDataManager.UserConsole;

import java.util.Comparator;
import java.util.HashSet;
/**
 * Command, that add worker to collection, if its value is more than others.
 */
public class Add_if_maxCommand extends CommandAbstract {
    {
        description = "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }
    @CommandTool
    private WorkerRequiredTool workerRequiredTool;
    private HashSet<Worker> collection;

    public Add_if_maxCommand(UserConsole.Prints prints, HashSet<Worker> collection, UserConsole.Input.InputWorker inputWorker) {
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
        Worker worker = workerRequiredTool.getInputWorker().askWorker(collection);
        if (collection.size() == 0)
            collection.add(worker);
        if (worker.compareTo(collection.stream().max(Comparator.naturalOrder()).get()) > 0){
            collection.add(worker);
            prints.print("Элемент добавлен.");
        }
    }
    /**
     * Runs command from script file
     */
    @Override
    public void runInScriptMode() throws WorkerDataInputException, CommandArgumentException {
        Worker worker = workerRequiredTool.getScriptDataManager().getWorker(collection);
        for (Worker w : collection) {
            if (w.compareTo(worker) > 0)
                return;
        }
        collection.add(worker);
        prints.print("Элемент добавлен.");
    }

    @Override
    public String toString() {
        return "add_if_max";
    }
}
