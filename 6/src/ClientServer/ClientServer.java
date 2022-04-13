package ClientServer;


import CommandControl.InfoCommand;
import CommandControl.SentCommand;
import Exceptions.ServerException;
import UserDataManager.UserConsole;

import java.io.*;
import java.net.Socket;

public class ClientServer {
    private UserConsole.Prints prints;
    public ClientServer(UserConsole.Prints prints){
        this.prints = prints;
    }
    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public void start() throws ServerException{
        try {
            clientSocket = new Socket("localhost", 4004);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            prints.print("Сервер подключён!");
        } catch (IOException e) {
            throw new ServerException();
        }
    }
    public void send(SentCommand command) throws ServerException{
        try {
            out.writeObject(command);
            out.flush();

        } catch (IOException e) {
            throw new ServerException();
        }


    }

    public Object sendAndGet(SentCommand command) throws ServerException {
        try {
            out.writeObject(command);
            out.flush();
            return (in.readObject());
       }
        catch (ClassNotFoundException e) {
            System.out.println("Wrong class!");
        }
        catch (IOException e) {
            throw new ServerException();
        }
        return null;
    }
    public void stop(){
        try {
            clientSocket.close();
            in.close();
            out.close();
        }
        catch (IOException e){
            prints.print("Ошибка соединения.");
        }

    }
}
