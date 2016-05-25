package PizzaService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by corentinhardy on 25/05/2016.
 */
public class Pizza extends UnicastRemoteObject implements IPizza, Serializable{

    private class ThreadPizza extends Thread {

        private int time;
        public ThreadPizza(int time){
            this.time = time;
        }
    }

    private Map<String, String> commandes;
    private static final String[] PIZZAS = {"4 Stagioni", "Hawaienne", "Queen"};

    protected Pizza() throws RemoteException {
        commandes = new HashMap<>();
    }

    public boolean commandePizza(String nomPizza, String nomReserve){
        commandes.put(nomReserve, nomPizza);

        return false;
    }
}
