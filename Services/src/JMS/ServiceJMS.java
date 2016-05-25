package JMS;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * Created by corentinhardy on 25/05/2016.
 */
public class ServiceJMS implements MessageListener{
    javax.jms.Queue queue;
    javax.jms.MessageProducer sender;
    javax.jms.Session sendSession;
    javax.jms.Connection connect;
    InitialContext context = null;

    public ServiceJMS(){
        try {
            Hashtable properties = new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

            context = new InitialContext(properties);

            ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
            connect = factory.createConnection();

            sendSession = connect.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
            queue = (Queue) context.lookup("dynamicQueues/queueExo2");
            sender = sendSession.createProducer(queue);

            connect.start();

            System.out.println("TEST_F");
        } catch (javax.jms.JMSException e){
            e.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static  void main(String[] args) {
        ServiceJMS sjms = new ServiceJMS();
    }

    @Override
    public void onMessage(Message message) {
        // Methode permettant au consommateur de consommer effectivement chaque msg recu
        // via la queue
        try {
            System.out.print("Recu un message de la queue: "+((MapMessage)message).getString("nom"));
            System.out.println(((MapMessage)message).getString("num"));
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
