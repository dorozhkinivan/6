package CommandControl;

import ClientServer.ClientServer;
import CollectionClasses.Worker;
import CommandControl.CommandTools.CommandTool;
import CommandControl.CommandTools.WorkerRequiredTool;
import Exceptions.ServerException;
import Exceptions.WorkerDataInputException;
import Server.CollectionServer;
import UserDataManager.UserConsole;

import java.util.HashSet;
/**
 * Command, that add worker to collection.
 */
public class AddCommand extends CommandAbstract{
    static private SentCommand sentCommand = new SentCommand() {
        @Override
        public Object execute(HashSet<Worker> collection) {
            CollectionServer.setDateAndId(worker);
            collection.add(worker);
            return null;
        }
    };
    {
        description = "добавить новый элемент в коллекцию";
    }
    @CommandTool
    private WorkerRequiredTool workerRequiredTool;
    private ClientServer clientServer;
    public AddCommand(UserConsole.Prints prints, UserConsole.Input.InputWorker inputWorker, ClientServer clientServer) {
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
        return "add";
    }
}
