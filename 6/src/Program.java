import ClientServer.ClientServer;
import CollectionClasses.Worker;
import CommandControl.*;
import Exceptions.ServerException;
import Server.FileManager.CollectionFileAbstract;
import Server.FileManager.CollectionFileCsv;
import UserDataManager.UserConsole;

import java.util.*;

public class Program {
    public static void main(String[] args) {

        Boolean interactiveWork = Boolean.TRUE;
        UserConsole userConsole = new UserConsole(new Scanner(System.in));
        CommandController commandController = new CommandController();
        ClientServer clientServer = new ClientServer(userConsole.prints);
        commandController.addCommands(
                new AddCommand(userConsole.prints, userConsole.worker, clientServer),
                new ClearCommand(userConsole.prints, clientServer),
                new Execute_scriptCommand(userConsole.prints, userConsole.command, commandController),
                new ExitCommand(userConsole.prints, clientServer),
                new InfoCommand(userConsole.prints, clientServer),
                new Remove_by_idCommand(userConsole.prints, userConsole.command, clientServer),
                new ShowCommand(userConsole.prints, clientServer),
                new UpdateCommand(userConsole.prints, userConsole.command, userConsole.worker, clientServer),
                new HelpCommand(userConsole.prints, commandController.getCommands()),
                new Add_if_maxCommand(userConsole.prints, userConsole.worker, clientServer),
                new Remove_greaterCommand(userConsole.prints, userConsole.worker, clientServer),
                new Remove_lowerCommand(userConsole.prints, userConsole.worker, clientServer),
                new Filter_by_positionCommand(userConsole.prints, userConsole.command, clientServer),
                new Filter_contains_nameCommand(userConsole.prints, userConsole.command, clientServer),
                new Print_field_descending_organizationCommand(userConsole.prints, clientServer)
        );
        try {
            clientServer.start();
        }
        catch (ServerException e){
            userConsole.prints.print("???????????? ????????????????????.");
            return;
        }

        do {
            commandController.runCommandFromUserConsole(userConsole.prints, userConsole.command);
        } while (interactiveWork);
    }
}
