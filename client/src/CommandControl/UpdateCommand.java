package CommandControl;

import CollectionClasses.Worker;
import CommandControl.CommandTools.ArgumentsTool;
import CommandControl.CommandTools.CommandTool;
import CommandControl.CommandTools.WorkerRequiredTool;
import Exceptions.CommandArgumentException;
import Exceptions.WorkerDataInputException;
import UserDataManager.UserConsole;
import Utility.WorkerActions;

import java.util.HashSet;

/**
 * Command, that deletes worker from collection and adds users one with the same id
 */
public class UpdateCommand extends CommandAbstract {
    {
        description = "обновить значение элемента коллекции, id которого равен заданному";
    }

    @CommandTool
    private ArgumentsTool argumentsTool;
    @CommandTool
    private WorkerRequiredTool workerRequiredTool;
    private HashSet<Worker> collection;

    public UpdateCommand(UserConsole.Prints prints, UserConsole.Input.InputCommand inputCommand, UserConsole.Input.InputWorker inputWorker, HashSet<Worker> collection) {
        workerRequiredTool = new WorkerRequiredTool();
        argumentsTool = new ArgumentsTool();
        this.prints = prints;
        this.collection = collection;
        argumentsTool.setInputCommand(inputCommand);
        workerRequiredTool.setInputWorker(inputWorker);
    }
    /**
     * Runs command from console
     */
    @Override
    public void run() {
        if (argumentsTool.getArguments() != null && argumentsTool.getArguments().length >= 1) {
            try {
                int id = Integer.parseInt(argumentsTool.getArguments()[0]);
                if (!WorkerActions.checkIfWorkerIdExists(id, collection)) {
                    prints.print("Такого рабочего нет!");
                    argumentsTool.setArguments(argumentsTool.getInputCommand().askArguments(toString()).getArguments());
                    run();
                } else {
                    collection.removeIf(worker1 -> worker1.getId().equals(id));
                    Worker worker = workerRequiredTool.getInputWorker().askWorker(collection);
                    worker.setId(id);
                    collection.add(worker);
                }
            } catch (NumberFormatException e) {
                prints.print("Нужно ввести id!");
                argumentsTool.setArguments(argumentsTool.getInputCommand().askArguments(toString()).getArguments());
                run();
            }
            return;
        }
        prints.print("ID не может быть null.");
        argumentsTool.setArguments(argumentsTool.getInputCommand().askArguments(toString()).getArguments());
        run();
    }

    /**
     * Runs command from script file
     */
    @Override
    public void runInScriptMode() throws WorkerDataInputException, CommandArgumentException {
        if (argumentsTool.getArguments() != null && argumentsTool.getArguments().length >= 1) {
            try {
                int id = Integer.parseInt(argumentsTool.getArguments()[0]);
                if (!WorkerActions.checkIfWorkerIdExists(id, collection)) {
                    throw new CommandArgumentException();
                } else {
                    collection.removeIf(worker1 -> worker1.getId().equals(id));
                    collection.add(workerRequiredTool.getScriptDataManager().getWorker(collection));
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
        return "update";
    }
}
