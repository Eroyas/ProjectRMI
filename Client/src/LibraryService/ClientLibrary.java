package LibraryService;

import iRMI.IRMIRegistry;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Eroyas on 22/05/16.
 */
public class ClientLibrary {

    public static void main(String[] args) {
        try {

            Registry registre =  LocateRegistry.getRegistry(4000);

            IRMIRegistry rmi = (IRMIRegistry) registre.lookup("MyRMI");
            ILibrary service = (ILibrary) rmi.lookup("Library");

            System.out.println("Ajouter un livre");
            System.out.println("Ajouter du livre : Un Livre");
            service.addBook("Un Livre", "Le premier livre de notre bibliotheque !");
            System.out.println("Ajouter du livre : Le livre sur les services");
            service.addBook("Le livre sur les services", "Le livre qui explique les services");

            System.out.println("Chercher un livre");
            String livre = service.getBook("Un Livre");
            System.out.println("Livre trouvé : " + livre);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
