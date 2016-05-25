package PizzaService;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by corentinhardy on 25/05/2016.
 */
public class Pizza extends UnicastRemoteObject implements IPizza, Serializable {

    private class ThreadPizza extends Thread {

        private int time;
        private javax.jms.MessageProducer sender;
        private javax.jms.Session sendSession;
        private javax.jms.Connection connect;
        private InitialContext context = null;
        private String name, pizza;

        public ThreadPizza(String pizza, String name, int time){
            this.pizza = pizza;
            this.name = name;
            this.time = time;
        }

        public void run(){
            try {
                Hashtable properties = new Hashtable();
                properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
                properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

                context = new InitialContext(properties);

                ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
                connect = factory.createConnection();

                sendSession = connect.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
                Queue queue = (Queue) context.lookup("Pizza/" + name);
                sender = sendSession.createProducer(queue);

                connect.start();

                try {
                    MapMessage mess = sendSession.createMapMessage();
                    mess.setString("pizza", pizza);
                    mess.setString("state", "starting");
                    sender.send(mess);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            } catch (javax.jms.JMSException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            }

            try {
                sleep(time);
                System.out.println("Pizza is ready");
            } catch (InterruptedException e) {
                System.out.println("Oh, a golden pizza !");
            } finally {
                try {
                    MapMessage mess = sendSession.createMapMessage();
                    mess.setString("state", "done");
                    mess.setStringProperty("typeMess", "important");
                    sender.send(mess);
                    commandes.remove(name);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Map<String, String> commandes;
    private static final String[] PIZZAS = {"4 Stagioni", "Hawaienne", "Queen"};

    protected Pizza() throws RemoteException {
        commandes = new HashMap<>();
    }

    private boolean pizzaExist(String nomPizza){
        for (String pizza : PIZZAS)
            if(pizza.equals(nomPizza))
                return true;
        return false;
    }

    public boolean commandePizza(String nomPizza, String nomReserve){
        if (! pizzaExist(nomPizza))
            return false;
        if(commandes.containsKey(nomReserve))
            return false;
        commandes.put(nomReserve, nomPizza);
        ThreadPizza cook = new ThreadPizza(nomPizza, nomReserve, 3000);
        cook.run();
        return true;
    }

    public String[] list(){
        return PIZZAS;
    }
}
