import RMI.RMIRegistry;
import iRMI.IRMIRegistry;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.security.AccessControlException;

/**
 * Created by Eroyas on 22/05/16.
 */
public class main {
    public static void main(String[] args) {
        try {
            if (System.getSecurityManager() != null)
                System.out.println("Security Manager already Activated !");
            else {
                System.out.println("We activate Security Manager !");
                System.setSecurityManager(new SecurityManager());
            }

            System.getSecurityManager().checkAccept("localhost", 8080);

            IRMIRegistry rmi = new RMIRegistry();

            Naming.rebind("rmi://localhost:8080/MyRMI", rmi);

            System.out.println("RMI configured !");
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }catch(AccessControlException e ){
            System.out.println("Sorry, we can't acces to localhost:8080 (rmiregistry). \nHave you configured your Security file ?");
        }

    }
}
