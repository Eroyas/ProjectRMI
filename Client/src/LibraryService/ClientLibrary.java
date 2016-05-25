package LibraryService;

import iRMI.IRMIRegistry;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Eroyas on 22/05/16.
 */
public class ClientLibrary {

    public static void clientLibrary() {
        try {

            Registry registre =  LocateRegistry.getRegistry(8080);

            IRMIRegistry rmi = (IRMIRegistry) registre.lookup("MyRMI");
            ILibrary service = (ILibrary) rmi.lookup("Library");

            System.out.println("#### Demo ajouter un livre ####");

            System.out.println("On Ajoute le livre : Niourk");
            service.addBook("Niourk", "Un enfant sauvage allant vers NiourK");
            System.out.println("On Ajoute le livre : Xipehuz");
            service.addBook("Xipehuz", "Un livre de Rosny Aine");

            System.out.println("#### Demo chercher un livre ####");

            String livre = service.getBook("Xipehuz");
            System.out.println("Vous venez de chercher le livre Xipehuz : " + livre);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
