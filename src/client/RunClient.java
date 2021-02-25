package client;

import shared.ClientChat;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class RunClient {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter username: ");
        String userName = keyboard.nextLine();

        ClientImpl client = new ClientImpl(userName);
        client.startClient();

        List<ClientChat> listOfActiveUsers = client.getListOfActiveUsers();
        listOfActiveUsers.forEach(temp -> {
            try {
                if (!(temp.getUSerName().equals(client.getUSerName())))
                System.out.println(temp.getUSerName());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Enter user to contact from list: ");
        String userToContact = keyboard.nextLine();
        String messageToSend = keyboard.nextLine();
        client.sendMessageToServer(messageToSend, userToContact);


    }
}
