package PizzaService;

import LibraryService.Library;
import iRMI.IRMIRegistry;

import java.rmi.Naming;

/**
 * Created by corentinhardy on 25/05/2016.
 */
public class ServicePizza {

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.out.println("Generating a new security manager ...");
            System.setSecurityManager(new SecurityManager());
            System.out.println("Done !");
        }

        try {
            IRMIRegistry rmi = (IRMIRegistry)  Naming.lookup("rmi://localhost:8080/MyRMI");
            Pizza service = new Pizza();
            rmi.rebind("Pizza", service);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
