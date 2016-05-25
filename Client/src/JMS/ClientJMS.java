package JMS;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * Created by Eroyas on 25/05/16.
 */
public class ClientJMS implements MessageListener {

    InitialContext context = null;
    private javax.jms.Connection connect = null;
    private javax.jms.Session receiveSession = null;

    public ClientJMS() throws JMSException {
        configurer();
    }

    private void configurer() throws JMSException {
        try {
            Hashtable properties = new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

            context = new InitialContext(properties);

            ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
            connect = factory.createConnection();

            this.configurerConsommateur("firstqueues");

            connect.start();
        } catch (javax.jms.JMSException jmse) {
            jmse.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        System.out.println("Ok for Client JMS");
    }

    private void configurerConsommateur(String queueName) throws JMSException, NamingException {
        receiveSession = connect.createSession(false,javax.jms.Session.AUTO_ACKNOWLEDGE);
        Queue queue = (Queue) context.lookup("dynamicQueues/" + queueName);

        System.out.println("Nom de la queue : " + queue.getQueueName());

        javax.jms.MessageConsumer qr1 = receiveSession.createConsumer(queue,"typeMess = 'important'");
        qr1.setMessageListener(this);

        javax.jms.MessageConsumer qr2 = receiveSession.createConsumer(queue, "numMess = 1");
        qr2.setMessageListener(this);
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.print("Recu un message de la queue: " + ((MapMessage)message).getString("nom"));
            System.out.println(((MapMessage)message).getString("num"));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new ClientJMS();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
