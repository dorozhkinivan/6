package CommandControl;

import Exceptions.CommandArgumentException;
import Exceptions.WorkerDataInputException;
import UserDataManager.UserConsole;

/**
 * All commands are extended of this class.
 */
abstract public class CommandAbstract {
    /**
     * Runs command from console
     */
    abstract public void run();

    /**
     * Runs command from script file
     */
    abstract public void runInScriptMode() throws WorkerDataInputException, CommandArgumentException;

    abstract public String toString();
    protected String description;
    public String getDescription(){
        return description;
    }
    protected UserConsole.Prints prints;

}
