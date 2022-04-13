package CommandControl;

import ClientServer.ClientServer;
import CollectionClasses.Worker;
import CommandControl.CommandTools.CommandTool;
import CommandControl.CommandTools.WorkerRequiredTool;
import Exceptions.CommandArgumentException;
import Exceptions.ServerException;
import Exceptions.WorkerDataInputException;
import Server.CollectionServer;
import UserDataManager.UserConsole;

import java.util.Comparator;
import java.util.HashSet;
/**
 * Command, that add worker to collection, if its value is more than others.
 */
public class Add_if_maxCommand extends CommandAbstract{
    static private SentCommand sentCommand = new SentCommand() {
        @Override
        public Object execute(HashSet<Worker> collection) {
            CollectionServer.setDateAndId(worker);
            if (collection.size() == 0)
            {
                collection.add(worker);
            }
            if (worker.compareTo(collection.stream().max(Comparator.naturalOrder()).get()) > 0){
                collection.add(worker);
            }
            return null;
        }
    };

    {
        description = "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }
    @CommandTool
    private WorkerRequiredTool workerRequiredTool;
    private ClientServer clientServer;
    public Add_if_maxCommand(UserConsole.Prints prints, UserConsole.Input.InputWorker inputWorker, ClientServer clientServer) {
        workerRequiredTool = new WorkerRequiredTool();
        this.prints = prints;
        this.clientServer = clientServer;
        workerRequiredTool.setInputWorker(inputWorker);

    }

    /**
     * Runs command from console
     */
    @Override
    public void run() throws ServerException {

        clientServer.send(sentCommand.addWorker(workerRequiredTool.getInputWorker().askWorker()));
    }

    /**
     * Runs command from script file
     */
    @Override
    public void runInScriptMode() throws WorkerDataInputException, CommandArgumentException, ServerException {
        clientServer.send(sentCommand.addWorker(workerRequiredTool.getScriptDataManager().getWorker()));
    }

    @Override
    public String toString() {
        return "add_if_max";
    }



}
