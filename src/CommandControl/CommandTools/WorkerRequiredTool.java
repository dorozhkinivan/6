package CommandControl.CommandTools;

import ScriptControl.ScriptDataManager;
import UserDataManager.UserConsole;
/**
 * Tool, that makes it possible for command to ask user worker`s data without arguments.
 * What is tool? {@link CommandTool}
 */
public class WorkerRequiredTool {
    private UserConsole.Input.InputWorker inputWorker;
    private ScriptDataManager scriptDataManager;

    public UserConsole.Input.InputWorker getInputWorker() {
        return inputWorker;
    }

    public void setInputWorker(UserConsole.Input.InputWorker inputWorker) {
        this.inputWorker = inputWorker;
    }

    public ScriptDataManager getScriptDataManager() {
        return scriptDataManager;
    }

    public void setScriptDataManager(ScriptDataManager scriptDataManager) {
        this.scriptDataManager = scriptDataManager;
    }
}
