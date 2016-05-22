import iRMI.IRMIRegistry;
import RMI.RMIRegistry;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * Created by Eroyas on 22/05/16.
 */
public class main {
    public static void main(String[] args) {
        try {
            IRMIRegistry rmi = new RMIRegistry();
            Naming.rebind("rmi://localhost:4000/MyRMI", rmi);
            System.out.println("RMI configured !");
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
