package CommandControl;

import CollectionClasses.Worker;

import java.io.Serializable;
import java.util.HashSet;

abstract public class SentCommand implements Serializable {
    private static final long serialVersionUID = 1L;
    public SentCommand(Worker worker){
        this.worker =worker;
    }
    public SentCommand(String[] arguments){
        this.arguments =arguments;
    }
    public SentCommand(String[] arguments, Worker worker){
        this.arguments =arguments;
        this.worker = worker;
    }
    public SentCommand(){}

   abstract public Object execute(HashSet<Worker> collection);

    protected Worker worker;
    protected String[] arguments;
    public String[] getArguments() {
        return arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    public SentCommand addWorker(Worker worker) {
        this.worker = worker;
        return this;
    }
    public SentCommand addArguments(String[] arguments) {
        this.arguments = arguments;
        return this;
    }
}
