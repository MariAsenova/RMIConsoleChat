package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerChat extends Remote {
    void registerClient(ClientChat clientChat) throws RemoteException;
    void sendMessageToClient(String messageBody, String toUserName) throws RemoteException;
    ArrayList<ClientChat> getListOfActiveUsers() throws RemoteException;
}
