package RMI;

import iRMI.IRMIRegistry;

import java.io.Serializable;
import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Eroyas on 22/05/16.
 *
 * Javadocs complete in file IRMIRegistry
 */
public class RMIRegistry extends UnicastRemoteObject implements IRMIRegistry {

    private Registry registry;

    public RMIRegistry() throws RemoteException {
        super();
    }

    public RMIRegistry(int port) throws RemoteException {
        super(port);
    }

    public RMIRegistry(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void bind(String key, Serializable obj) throws RemoteException, AlreadyBoundException {
        registry.bind(key, obj);
    }

    @Override
    public void rebind(String key, Serializable obj) throws RemoteException {
        registry.rebind(key, obj);
    }

    @Override
    public void unbind(String key) throws RemoteException, NotBoundException {
        registry.unbind(key);
    }

    @Override
    public Serializable lookup(String key) throws RemoteException, NotBoundException {
        return registry.lookup(key);
    }

    @Override
    public String[] list() throws RemoteException {
        return registry.list();
    }

    @Override
    public String[] getLastList(int x) throws RemoteException {
        return registry.getLastList(x);
    }

    @Override
    public Serializable[] getLastBind(int x) throws RemoteException {
        return registry.getLastBind(x);
    }

}
