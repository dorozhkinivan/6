package CommandControl;

import ClientServer.ClientServer;
import CollectionClasses.Worker;
import CommandControl.CommandTools.CommandTool;
import CommandControl.CommandTools.WorkerRequiredTool;
import Exceptions.ServerException;
import Exceptions.WorkerDataInputException;
import UserDataManager.UserConsole;

import java.util.HashSet;

/**
 * Command, that deletes workers, which are greater than selected one
 */
public class Remove_greaterCommand extends CommandAbstract{
    static private SentCommand sentCommand = new SentCommand() {
        @Override
        public Object execute(HashSet<Worker> collection) {
            collection.removeIf(worker1 -> worker1.compareTo(worker) > 0);
            return null;
        }
    };

    {
        description = "удалить из коллекции все элементы, превышающие заданный";
    }
    private ClientServer clientServer;
    @CommandTool
    private WorkerRequiredTool workerRequiredTool;

    public Remove_greaterCommand(UserConsole.Prints prints, UserConsole.Input.InputWorker inputWorker, ClientServer clientServer) {
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
    public void runInScriptMode() throws WorkerDataInputException, ServerException {
        clientServer.send(sentCommand.addWorker(workerRequiredTool.getScriptDataManager().getWorker()));
    }

    @Override
    public String toString() {
        return "remove_greater";
    }

}
