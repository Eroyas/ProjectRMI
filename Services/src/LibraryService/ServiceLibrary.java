package LibraryService;

import iRMI.IRMIRegistry;

import java.rmi.Naming;

/**
 * Created by Eroyas on 22/05/16.
 */
public class ServiceLibrary{

    public static void serviceLibrary() {
        if (System.getSecurityManager() == null) {
            System.out.println("Generating a new security manager ...");
            System.setSecurityManager(new SecurityManager());
            System.out.println("Done !");
        }

        try {
            IRMIRegistry rmi = (IRMIRegistry) Naming.lookup("rmi://localhost:8080/MyRMI");
            Library service = new Library();
            rmi.rebind("Library", service);
            System.out.println("serviceLibrary is working");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

/*
try {
        Hashtable properties = new Hashtable();
        properties.put(Context.INITIAL_CONTEXT_FACTORY,
        "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

        InitialContext context = new InitialContext(properties);
        javax.jms.ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        javax.jms.Connection  connect = factory.createConnection();

        javax.jms.Session sendSession = connect.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
        javax.jms.Queue queue = (Queue) context.lookup("dynamicQueues/queueExo2");
        javax.jms.MessageProducer sender = sendSession.createProducer(queue);

        System.out.println("TEST_F");
        }catch(Exception e){
        e.printStackTrace();
        }
        */
