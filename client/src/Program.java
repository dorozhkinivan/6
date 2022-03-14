import CollectionClasses.Worker;
import CommandControl.*;
import FileManager.CollectionFileAbstract;
import FileManager.CollectionFileCsv;
import UserDataManager.UserConsole;

import java.util.*;

public class Program {
    public static void main(String[] args) {
        Boolean interactiveWork = Boolean.TRUE;
        UserConsole userConsole = new UserConsole(new Scanner(System.in));
        CollectionFileAbstract dataCollectionManager = new CollectionFileCsv(System.getenv("filePath"), userConsole.prints);
        HashSet<Worker> collection = dataCollectionManager.InputCollection();
        CommandController commandController = new CommandController();
        commandController.addCommands(
                new AddCommand(userConsole.prints, collection, userConsole.worker),
                new ClearCommand(userConsole.prints, collection),
                new Execute_scriptCommand(userConsole.prints, userConsole.command, commandController),
                new ExitCommand(userConsole.prints),
                new InfoCommand(userConsole.prints, collection),
                new Remove_by_idCommand(userConsole.prints, userConsole.command, collection),
                new SaveCommand(userConsole.prints, dataCollectionManager, collection),
                new ShowCommand(userConsole.prints, collection),
                new UpdateCommand(userConsole.prints, userConsole.command, userConsole.worker, collection),
                new HelpCommand(userConsole.prints, commandController.getCommands()),
                new Add_if_maxCommand(userConsole.prints, collection, userConsole.worker),
                new Remove_greaterCommand(userConsole.prints, collection, userConsole.worker),
                new Remove_lowerCommand(userConsole.prints, collection, userConsole.worker),
                new Filter_by_positionCommand(userConsole.prints, collection, userConsole.command),
                new Filter_contains_nameCommand(userConsole.prints, collection, userConsole.command),
                new Print_field_descending_organizationCommand(userConsole.prints, collection)
        );
        do {
            commandController.runCommandFromUserConsole(userConsole.prints, userConsole.command);
            Integer i = new Integer(1);

        } while (interactiveWork);
    }
}
