package JMS;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by corentinhardy on 25/05/2016.
 */
public class ServiceJMS {

    javax.jms.MessageProducer sender;
    javax.jms.Session sendSession;
    javax.jms.Connection connect;
    InitialContext context = null;

    public ServiceJMS() throws JMSException {
        configurer();
    }

    public class OurConnect implements Serializable{

        javax.jms.Connection connection;
        public Session createSession(boolean b, int i) throws JMSException {
            return null;
        }

        public String getClientID() throws JMSException {
            return connection.getClientID();
        }

        public void setClientID(String s) throws JMSException {
            connection.setClientID(s);
        }

        public ConnectionMetaData getMetaData() throws JMSException {
            return connection.getMetaData();
        }

        public ExceptionListener getExceptionListener() throws JMSException {
            return connection.getExceptionListener();
        }

        public void setExceptionListener(ExceptionListener exceptionListener) throws JMSException {
            connection.setExceptionListener(exceptionListener);
        }

        public void start() throws JMSException {
            connection.start();
        }

        public void stop() throws JMSException {
            connection.stop();
        }

        public void close() throws JMSException {
            connection.close();
        }

        public ConnectionConsumer createConnectionConsumer(Destination destination, String s, ServerSessionPool serverSessionPool, int i) throws JMSException {
            return connection.createConnectionConsumer(destination, s, serverSessionPool, i);
        }

        public ConnectionConsumer createDurableConnectionConsumer(Topic topic, String s, String s1, ServerSessionPool serverSessionPool, int i) throws JMSException {
            return connection.createDurableConnectionConsumer(topic, s, s1, serverSessionPool, i);
        }
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

        System.out.println("Ok for Service JMS");
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

    public static  void main(String[] args) {
        try {
            new ServiceJMS();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
