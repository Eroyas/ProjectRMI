package PizzaService;

import JMS.ClientJMS;
import LibraryService.ILibrary;
import iRMI.IRMIRegistry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Eroyas on 25/05/16.
 */
public class ClientPizza  extends UnicastRemoteObject {

    protected ClientPizza() throws RemoteException {
        super();

    }

    public static void main(String[] args) {

        if (System.getSecurityManager() == null) {
            System.out.println("Generating a new security manager ...");
            System.setSecurityManager(new SecurityManager());
            System.out.println("Done !");
        }

        try {
            ClientPizza pizza = new ClientPizza();
            ClientJMS jms;

            Registry registre =  LocateRegistry.getRegistry(8080);

            IRMIRegistry rmi = (IRMIRegistry) registre.lookup("MyRMI");
            IPizza service = (IPizza) rmi.lookup("Pizza");

            System.out.println("#### Demo vente de pizza avec JMS ####");

            String client = "Dupont";
            System.out.println("Connexion de " +  client);

            String[] list = service.list();

            System.out.println("Vous pouvez commander les pizza suivante : ");

            for (String s : list)
                System.out.println(s);

            String cmd = "Hawaienne";
            System.out.println(client + " commande une " + cmd);
            System.out.println("#### Attente de la pizza ####");
            service.commandePizza(cmd, client);

            jms = new ClientJMS();
            jms.configurerConsommateur("Pizza/" + client);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
