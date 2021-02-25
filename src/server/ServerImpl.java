package server;

import shared.ClientChat;
import shared.ServerChat;

import java.awt.*;
import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.stream.Stream;

public class ServerImpl implements ServerChat {
    private ArrayList<ClientChat> registeredClients = new ArrayList<>();

    public ServerImpl() {
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerClient(ClientChat clientChat) throws RemoteException {
        registeredClients.add(clientChat);
        System.out.println(clientChat.getUSerName() + "\t has connected to the server");
    }

    @Override
    public void sendMessageToClient(String messageBody, String toUserName) throws RemoteException {
        // redo into a stream
        ClientChat userToSendTo = null;
        for (int i = 0; i < registeredClients.size(); i++) {
            if (registeredClients.get(i).getUSerName().equals(toUserName)) {
                userToSendTo = registeredClients.get(i);
            }
        }

        userToSendTo.getMessage(messageBody);
    }

    @Override
    public ArrayList<ClientChat> getListOfActiveUsers() throws RemoteException {
        if (registeredClients.size() > 0) {
            return (ArrayList<ClientChat>)registeredClients.clone();
        } else {
            throw new RuntimeException("No user are online at the moments");
        }
    }
}
