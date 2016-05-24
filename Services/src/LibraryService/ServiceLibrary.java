package LibraryService;

import iRMI.IRMIRegistry;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Eroyas on 22/05/16.
 */
public class ServiceLibrary {
    public interface IToto extends Remote{
        String toto() throws RemoteException;
    }

    public static class Toto extends UnicastRemoteObject implements IToto, Serializable {
        protected Toto() throws RemoteException {
            System.out.println("Toto Construit");
        }


        public String toto() throws RemoteException {
            System.out.println("TOTO POWER");
            return "toto";
        }
    }

    public static class Bubu extends UnicastRemoteObject implements Remote, Serializable {
        protected Bubu() throws RemoteException {
            System.out.println("Bubu Construit");
        }


        public String toto() throws RemoteException {
            System.out.println("BUBU POWER");
            return "bubu";
        }
    }

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.out.println("Generating a new security manager ...");
            System.setSecurityManager(new SecurityManager());
            System.out.println("Done !");
        }

        try {

            IRMIRegistry rmi = (IRMIRegistry) Naming.lookup("rmi://localhost:8080/MyRMI");

            // rmi marche bien
            System.out.println("vide ? " + rmi.list().length);

            //Library service = new Library();
            //rmi.rebind("Library", service);

            System.out.println("\n##############################\n##############################\n");

            // Bubu marche bien
            Bubu b = new Bubu();
            rmi.rebind("KEY_BUBU", b);
            System.out.println("Done 2 !");
            System.out.println("last: " + rmi.getLastList(1)[0]);
            System.out.println("res: " + rmi.lookup("KEY_BUBU")); // non-castable du coup

            System.out.println("\n##############################\n##############################\n");

            // Toto (avec donc IToto) ne marche pas
            Toto t = new Toto();
            rmi.rebind("KEY_TOTO", t);
            System.out.println("Done 3 !");
            System.out.println("last: " + rmi.getLastList(1)[0]);
            System.out.println("res: " + ((IToto) rmi.lookup("KEY_TOTO")).toto());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
