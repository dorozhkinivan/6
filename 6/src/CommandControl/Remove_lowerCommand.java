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
 * Command, that deletes workers, which are lower than selected one
 */
public class Remove_lowerCommand extends CommandAbstract{
    static private SentCommand sentCommand = new SentCommand() {
        @Override
        public Object execute(HashSet<Worker> collection) {
            collection.removeIf(worker1 -> worker1.compareTo(worker)<0);
            return null;
        }
    };

    {
        description = "удалить из коллекции все элементы, меньшие, чем заданный";
    }
    @CommandTool
    private WorkerRequiredTool workerRequiredTool;
    private ClientServer clientServer;
    public Remove_lowerCommand(UserConsole.Prints prints, UserConsole.Input.InputWorker inputWorker, ClientServer clientServer){
        workerRequiredTool = new WorkerRequiredTool();
        this.clientServer = clientServer;
        this.prints = prints;
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
        return "remove_lower";
    }

}
