package FileManager;

import CollectionClasses.Worker;
import Exceptions.ValueException;
import UserDataManager.UserConsole;
import Utility.WorkerActions;
import com.opencsv.CSVReader;
import com.opencsv.bean.*;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Manage csv file with collection
 */
public class CollectionFileCsv extends CollectionFileAbstract {
    private String fileName;
    private UserConsole.Prints prints;
    public CollectionFileCsv(String fileName, UserConsole.Prints prints){
        this.fileName = fileName;
        this.prints = prints;
    }

    /**
     * @return collection from csv file
     */
    @Override
    public HashSet<Worker> InputCollection(){
        if (fileName == null)
            prints.print("Путь к файлу не найден!");
        else {
            try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
                HashSet<Worker> collectionGet = new HashSet<>();
                List<Worker> list = new CsvToBeanBuilder<Worker>(csvReader).withType(Worker.class).withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS).build().parse();
                collectionGet.addAll(list);

                for (Worker w : collectionGet){
                    WorkerActions.CheckWorkerValues(w, collectionGet);
                }
                return collectionGet;
            } catch (IOException fileNotFoundException) {
                prints.print("Файл не найден");
            }
            catch (ValueException e){
                prints.print("Неверные значения в файле!");
            }
        }
        return new HashSet<>();
    }
    /**
     * write collection to csv file
     */
    @Override
    public void OutputCollection(Collection<Worker> collection) {
        System.out.println(fileName);
        ArrayList<Worker> list = new ArrayList<Worker>();
        list.addAll(collection);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            new StatefulBeanToCsvBuilder<Worker>(bufferedWriter).build().write(list);
            //beanToCsv
        }
        catch (IOException e){    System.out.println(1);    }
        catch (CsvDataTypeMismatchException e){System.out.println(2);}
        catch (CsvRequiredFieldEmptyException e){System.out.println(3);}
    }

//    private boolean checkCollection(Collection<Worker> collection){
//        for (Worker w : collection){
//            int countUnique = 0;
//            for (Worker worker1 : collection){
//                if (worker1.getId().equals(w.getId())) {
//                    countUnique++;
//                }
//            }
//            if (countUnique != 1 || !ChecksValuesInReadyWorker.CheckUserWorker(w))
//                return false;
//        }
//        return true;
//    }
}
