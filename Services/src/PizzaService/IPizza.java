package PizzaService;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by corentinhardy on 25/05/2016.
 */
public interface IPizza extends Remote {

    String[] list() throws RemoteException;

    boolean commandePizza(String nomPizza, String nomReserve) throws RemoteException;
}
