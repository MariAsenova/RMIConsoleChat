package client;

import shared.ClientChat;
import shared.ServerChat;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.MessageFormat;
import java.util.ArrayList;

public class ClientImpl implements ClientChat {
    private String username;
    private ServerChat serverChat;

    public ClientImpl(String username) {
        this.username = username;
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void startClient() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(1099);
        serverChat = (ServerChat) registry.lookup("server");
        serverChat.registerClient(this);
    }

    @Override
    public void sendMessageToServer(String messageBody, String toUserName) throws RemoteException {
        serverChat.sendMessageToClient(messageBody, toUserName);
    }

    @Override
    public void getMessage(String receivedMessage) throws RemoteException {
        // check Java interpolation
        System.out.println(username + "\t has received the message: " + receivedMessage);
    }

    @Override
    public String getUSerName() throws RemoteException {
        return username;
    }

    @Override
    public ArrayList<ClientChat> getListOfActiveUsers() throws RemoteException {
        return serverChat.getListOfActiveUsers();
    }
}
