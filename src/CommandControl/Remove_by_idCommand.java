package CommandControl;

import CollectionClasses.Worker;
import CommandControl.CommandTools.ArgumentsTool;
import CommandControl.CommandTools.CommandTool;
import Exceptions.CommandArgumentException;
import UserDataManager.UserConsole;
import Utility.WorkerActions;

import java.util.Collection;

/**
 * Command, that deletes worker with selected id
 */
public class Remove_by_idCommand extends CommandAbstract {
    {
        description = "удалить элемент из коллекции по его id";
    }

    @CommandTool
    private ArgumentsTool argumentsTool;
    private Collection<Worker> collection;

    public Remove_by_idCommand(UserConsole.Prints prints, UserConsole.Input.InputCommand inputCommand, Collection<Worker> collection) {
        argumentsTool = new ArgumentsTool();
        this.prints = prints;
        this.collection = collection;
        argumentsTool.setInputCommand(inputCommand);
    }
    /**
     * Runs command from console
     */
    @Override
    public void run() {
        if (argumentsTool.getArguments() != null && argumentsTool.getArguments().length >= 1) {
            try {
                Integer id = Integer.parseInt(argumentsTool.getArguments()[0]);
                boolean exists = WorkerActions.checkIfWorkerIdExists(id, collection);
                if (!exists) {
                    prints.print("Такого рабочего нет!");
                    argumentsTool.setArguments(argumentsTool.getInputCommand().askArguments(toString()).getArguments());
                    run();
                } else {
                    collection.removeIf(worker1 -> worker1.getId().equals(id));
                }
            } catch (NumberFormatException e) {
                prints.print("Нужно ввести id!");
                argumentsTool.setArguments(argumentsTool.getInputCommand().askArguments(toString()).getArguments());
                run();
            }
            return;
        }
        prints.print("ID не может быть null");
        argumentsTool.setArguments(argumentsTool.getInputCommand().askArguments(toString()).getArguments());
        run();

    }

    /**
     * Runs command from script file
     */
    @Override
    public void runInScriptMode() throws CommandArgumentException {
        if (argumentsTool.getArguments() != null && argumentsTool.getArguments().length >= 1) {
            try {
                int id = Integer.parseInt(argumentsTool.getArguments()[0]);
                if (!WorkerActions.checkIfWorkerIdExists(id, collection)) {
                    throw new CommandArgumentException();
                } else {
                    collection.removeIf(worker1 -> worker1.getId() == id);
                }
            } catch (NumberFormatException e) {
                throw new CommandArgumentException();
            }
            return;
        }
            throw new CommandArgumentException();

    }

    @Override
    public String toString() {
        return "remove_by_id";
    }
}
