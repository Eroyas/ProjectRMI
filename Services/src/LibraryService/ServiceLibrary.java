package LibraryService;

import iRMI.IRMIRegistry;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

/**
 * Created by Eroyas on 22/05/16.
 */
public class ServiceLibrary {

    public static void main(String[] args) {
        try {
            IRMIRegistry rmi = (IRMIRegistry) Naming.lookup("rmi://localhost:4000/MyRMI");

            Library service = new Library();

            rmi.bind("Library", service);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
