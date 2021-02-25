package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientChat extends Remote {
    void sendMessageToServer(String messageBody, String toUserName) throws RemoteException;
    void getMessage(String receivedMessage) throws RemoteException;
    String getUSerName() throws RemoteException;
    ArrayList<ClientChat> getListOfActiveUsers() throws RemoteException;
}
