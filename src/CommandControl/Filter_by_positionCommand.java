package CommandControl;

import CollectionClasses.Position;
import CollectionClasses.Worker;
import CommandControl.CommandTools.ArgumentsTool;
import CommandControl.CommandTools.CommandTool;
import CommandControl.CommandTools.WorkerRequiredTool;
import Exceptions.CommandArgumentException;
import Exceptions.WorkerDataInputException;
import UserDataManager.UserConsole;
import Utility.EnumString;
import java.util.HashSet;
/**
 * Command, that prints workers with position, which are equal with selected.
 */
public class Filter_by_positionCommand extends CommandAbstract {
    {
        description = "вывести элементы, значение поля position которых равно заданному";
    }
    private HashSet<Worker> collection;
    @CommandTool
    private ArgumentsTool argumentsTool;

    public Filter_by_positionCommand(UserConsole.Prints prints, HashSet<Worker> collection, UserConsole.Input.InputCommand inputCommand) {
        argumentsTool = new ArgumentsTool();
        this.prints = prints;
        this.collection = collection;
        argumentsTool.setInputCommand(inputCommand);
    }

    @Override
    public String toString() {
        return "filter_by_position";
    }
    /**
     * Runs command from console
     */
    @Override
    public void run() {
        EnumString<Position> enumString = new EnumString<>("позиция", Position.values());
        if (argumentsTool.getArguments() != null && argumentsTool.getArguments().length > 0) {
            try {
                System.out.println(argumentsTool.getArguments()[0]);
                Position position = enumString.getEnum(argumentsTool.getArguments()[0]);
                System.out.println(position);
                for (Worker i : collection) {
                    if (position.equals(i.getPosition()))
                        prints.print(i.toString());
                }
                return;
            } catch (IllegalArgumentException e) {
                prints.print("Введите одно из значений ");
                prints.print(enumString.toString());
                argumentsTool.setArguments(argumentsTool.getInputCommand().askArguments(toString()).getArguments());
                run();
            }
            return;
        }
        argumentsTool.setArguments(argumentsTool.getInputCommand().askArguments(toString()).getArguments());
        run();

    }

    /**
     * Runs command from script file
     */
    @Override
    public void runInScriptMode() throws WorkerDataInputException, CommandArgumentException {
        EnumString<Position> enumString = new EnumString<>("позиция", Position.values());
        try {
            if (argumentsTool.getArguments() != null && argumentsTool.getArguments().length > 0) {
                Position position = enumString.getEnum(argumentsTool.getArguments()[0]);
                for (Worker i : collection) {
                    if (position.equals(i.getPosition()))
                        prints.print(i.toString());
                }
                return;
            }
                throw new CommandArgumentException();
        } catch (IllegalArgumentException e) {
            throw new CommandArgumentException();
        }
    }
}
