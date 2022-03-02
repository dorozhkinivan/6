package CommandControl;

import CollectionClasses.Worker;
import UserDataManager.UserConsole;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
/**
 * Command, that prints information about collection.
 */
public class InfoCommand extends CommandAbstract {
    {
        description = "вывести в стандартный поток вывода информацию о коллекции";
    }
    Collection<Worker> collection;

    public InfoCommand(UserConsole.Prints prints, Collection<Worker> collection) {
        this.prints = prints;
        this.collection = collection;
    }

    /**
     * Runs command from script file
     */
    @Override
    public void runInScriptMode() {
        prints.print("Тип данных: рабочие, Длина: " + collection.size() + ( (collection.size()==0) ? "Коллекция пуста." : (" Дата создания первого элемента: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(collection.stream().min(Comparator.comparing(Worker::getCreationDate)).get().getCreationDate())  + " Самый высокая зарплата: " + collection.stream().max(Comparator.naturalOrder()).get().getSalary())));
    }
    /**
     * Runs command from console
     */
    @Override
    public void run() {
        prints.print("Тип данных: рабочие, Длина: " + collection.size() + ( (collection.size()==0) ? "Коллекция пуста." : (" Дата создания первого элемента: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(collection.stream().min(Comparator.comparing(Worker::getCreationDate)).get().getCreationDate())  + " Самый высокая зарплата: " + collection.stream().max(Comparator.naturalOrder()).get().getSalary())));
    }

    @Override
    public String toString() {
        return "info";
    }
}
