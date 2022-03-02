package CommandControl;

import UserDataManager.UserConsole;
/**
 * Command, that stops program
 */
public class ExitCommand extends CommandAbstract {
    {
        description = "завершить программу (без сохранения в файл)";
    }
    public ExitCommand(UserConsole.Prints prints) {
        this.prints = prints;
    }
    /**
     * Runs command from console
     */
    @Override
    public void run() {
        prints.print("Работа завершена!");
        System.exit(0);
    }

    /**
     * Runs command from script file
     */
    @Override
    public void runInScriptMode() {
        prints.print("Работа завершена!");
        System.exit(0);
    }

    @Override
    public String toString() {
        return "exit";
    }
}
