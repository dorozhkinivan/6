package Server;

import CollectionClasses.Worker;
import CommandControl.SentCommand;
import Server.FileManager.CollectionFileAbstract;
import Server.FileManager.CollectionFileCsv;
import Utility.WorkerValues;
import ValueControl.NumberValueControl;
import ValueControl.TypeOfControl;
import ValueControl.UniqueControl;
import ValueControl.ValueException;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class CollectionServer {
    private static HashSet<Worker> collection;
    private static CollectionFileAbstract dataCollectionManager = new CollectionFileCsv(System.getenv("filePath"));

    static {
        collection = dataCollectionManager.InputCollection();
    }

    public Object executeCommand(SentCommand command) {
        return command.execute(collection);
    }

    public static void saveCollection() {
        dataCollectionManager.OutputCollection(collection);
    }


    /**
     * @return boolean, existence of id in worker's collection
     */


    /**
     * set date and id to worker
     */
    public static void setDateAndId(Worker worker) {
        NumberValueControl numberValueControl = ((NumberValueControl) WorkerValues.values.get("id").get(TypeOfControl.NUMBER));
        int min = numberValueControl.getMinInt();
        int max = numberValueControl.getMaxInt();
        UniqueControl uniqueControl = (UniqueControl) WorkerValues.values.get("id").get(TypeOfControl.UNIQUE);
        uniqueControl.setCollection(collection);
        try {
            worker.safeSetId((min + (int) (Math.random() * (max - min))), collection);
            worker.safeSetCreationDate(new Date());
        } catch (ValueException e) {
            if (e.getType() == TypeOfControl.UNIQUE) {
                setDateAndId(worker);
            }
        }
    }

    /**
     * check if worker`s data is correct
     *
     * @throws ValueException
     */
    public static void CheckWorkerValues(Worker worker) throws ValueException {
        worker.safeSetId(worker.getId(), collection);
        worker.safeSetSalary(worker.getSalary());
        worker.safeSetCoordinates(worker.getCoordinates());
        worker.safeSetName(worker.getName());
        worker.safeSetCreationDate(worker.getCreationDate());
        worker.safeSetPosition(worker.getPosition());
        worker.safeSetStatus(worker.getStatus());
        worker.safeSetOrganization(worker.getOrganization());
        if (worker.getCoordinates() != null) {
            worker.getCoordinates().safeSetX(worker.getCoordinates().getX());
            worker.getCoordinates().safeSetY(worker.getCoordinates().getY());
        }
        if (worker.getOrganization() != null) {
            worker.getOrganization().safeSetAnnualTurnover(worker.getOrganization().getAnnualTurnover());
            worker.getOrganization().safeSetPostalAddress(worker.getOrganization().getPostalAddress());
            if (worker.getOrganization().getPostalAddress() != null) {
                worker.getOrganization().getPostalAddress().safeSetStreet(worker.getOrganization().getPostalAddress().getStreet());
                worker.getOrganization().getPostalAddress().safeSetZipCode(worker.getOrganization().getPostalAddress().getZipCode());
            }
        }
    }

}
