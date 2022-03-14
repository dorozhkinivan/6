package Utility;

import CollectionClasses.*;
import Exceptions.ValueException;
import ValueControl.*;

import java.util.Collection;
import java.util.Date;
/**
 * Worker opportunities
 */
abstract public class WorkerActions {
    /**
     * @return boolean, existence of id in worker's collection
     */
    public static boolean checkIfWorkerIdExists(Integer id, Collection<Worker> collection) {
        for (Worker worker : collection) {
            if (worker.getId() == null && id == null)
                return true;
            if (worker.getId() != null && id != null)
                if (worker.getId().equals(id))
                    return true;
        }
        return false;
    }

    /**
     * set date and id to worker
     */
    public static void setDateAndId(Worker worker, Collection<Worker> collection) {
        NumberValueControl numberValueControl = ((NumberValueControl)WorkerValues.values.get("id").get(TypeOfControl.NUMBER));
        int min = numberValueControl.getMinInt();
        int max = numberValueControl.getMaxInt();
        UniqueControl uniqueControl = (UniqueControl)WorkerValues.values.get("id").get(TypeOfControl.UNIQUE);
        uniqueControl.setCollection(collection);
        try {
            worker.safeSetId((min + (int) (Math.random() * (max - min))), collection);
            worker.safeSetCreationDate(new Date());
        } catch (ValueException e) {
            if (e.getType() == TypeOfControl.UNIQUE) {
                setDateAndId(worker, collection);
            }
        }
    }
    /**
     * check if worker`s data is correct
     * @throws ValueException
     */
    public static void CheckWorkerValues(Worker worker, Collection<Worker> collection) throws ValueException {
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
