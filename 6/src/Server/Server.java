package Server;

import CommandControl.SentCommand;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.*;

public class Server {
    static Logger LOGGER;
    static FileHandler fileHandler;

    static {
        LOGGER = Logger.getLogger(Server.class.getName());
        LogManager.getLogManager().reset();
        try {
            fileHandler = new FileHandler("info.config");
            LOGGER.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            System.out.println("File logger error.");
        }
    }

    private Socket clientSocket;
    private ServerSocket server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private CollectionServer collectionServer;

    public Server(CollectionServer collectionServer) {
        this.collectionServer = collectionServer;
    }


    public void stop() {
        LOGGER.log(Level.INFO, "Stop server");
        try {
            if (in != null)
                in.close();
        } catch (IOException ignore) {
        }
        try {
            if (out != null)
                out.close();
        } catch (IOException ignore) {
        }
        try {
            if (server != null)
                server.close();
        } catch (IOException ignore) {
        }

    }

    public void start() {
        try {
            server = new ServerSocket(4004);
            LOGGER.log(Level.INFO, "Start server");
            System.out.println("Команда exit - завершение работы, save - сохранение коллекции. Логгер в файле info.config");
            newClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void newClient() throws IOException {
        while (true) {
            try {
                clientSocket = server.accept();
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());
                Object answer = collectionServer.executeCommand((SentCommand) in.readObject());
                LOGGER.log(Level.INFO, "Got command from " + clientSocket.getInetAddress().toString());
                if (answer != null) {
                    sendAnswer(answer);
                }
            } catch (ClassNotFoundException e) {
                LOGGER.log(Level.WARNING, "Got wrong data from " + clientSocket.getInetAddress().toString());
            } catch (SocketException e) {
                break;
            }
        }
    }

    private void sendAnswer(Object answer) throws IOException {
        LOGGER.log(Level.INFO, "Server sends answer");
        out.writeObject(answer);
        out.flush();
    }

}
