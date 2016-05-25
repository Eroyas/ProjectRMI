package JMS;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * Created by corentinhardy on 25/05/2016.
 */
public class ServiceJMS implements MessageListener {

    javax.jms.MessageProducer sender;
    javax.jms.Session sendSession;
    javax.jms.Connection connect;
    InitialContext context = null;

    public ServiceJMS() throws JMSException {
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

            configurerProducteur("firstqueues");

            connect.start();
        } catch (javax.jms.JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        this.produire();
    }

    private void configurerProducteur(String queueName) throws JMSException, NamingException {
        sendSession = connect.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
        Queue queue = (Queue) context.lookup("dynamicQueues/" + queueName);
        sender = sendSession.createProducer(queue);
    }

    private void produire() throws JMSException {
        for (int i = 1; i <= 10; i++) {

            MapMessage mess = sendSession.createMapMessage();
            mess.setInt("num",i);
            mess.setString("nom",i+"-");

            if (i % 2 == 0) {
                mess.setStringProperty("typeMess", "important");
            }

            if (i==1) {
                mess.setIntProperty("numMess",1);
            }

            sender.send(mess);
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.print("Recu un message de la queue: "+((MapMessage)message).getString("nom"));
            System.out.println(((MapMessage)message).getString("num"));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static  void main(String[] args) {
        try {
            new ServiceJMS();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
