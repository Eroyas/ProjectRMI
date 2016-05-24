package LibraryService;

import iRMI.IRMIRegistry;

import java.rmi.Naming;

/**
 * Created by Eroyas on 22/05/16.
 */
public class ServiceLibrary {

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.out.println("Generating a new security manager ...");
            System.setSecurityManager(new SecurityManager());
            System.out.println("Done !");
        }

        try {
            IRMIRegistry rmi = (IRMIRegistry) Naming.lookup("rmi://localhost:8080/MyRMI");
            Library service = new Library();
            rmi.bind("Library", service);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
