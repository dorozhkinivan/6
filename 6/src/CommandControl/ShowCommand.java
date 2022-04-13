package CommandControl;

import ClientServer.ClientServer;
import CollectionClasses.Worker;
import Exceptions.ServerException;
import UserDataManager.UserConsole;

import java.io.NotSerializableException;
import java.util.HashSet;
/**
 * Command, that prints all elements in collection
 */
public class ShowCommand extends CommandAbstract {
    static private SentCommand sentCommand;
     static {
        sentCommand = new SentCommand() {
            @Override
            public Object execute(HashSet<Worker> collection) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Worker worker : collection){
                    stringBuilder.append("\nРАБОЧИЙ\n");
                    stringBuilder.append(worker.toString());
                }
                if (stringBuilder.toString().equals(""))
                    return "Коллекция пуста.";
                return stringBuilder.toString();
            }
        };
    }

    {
        description = "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
    private ClientServer clientServer;
    public ShowCommand(UserConsole.Prints prints, ClientServer clientServer){
        this.prints = prints;
        this.clientServer = clientServer;
    }
    /**
     * Runs command from console
     */
    @Override
    public void run() throws ServerException {

        prints.print((String) clientServer.sendAndGet(sentCommand));
    }


    /**
     * Runs command from script file
     */
    @Override
    public void runInScriptMode() throws ServerException{
        prints.print((String) clientServer.sendAndGet(sentCommand));
    }

    @Override
    public String toString() {
        return "show";
    }
}
