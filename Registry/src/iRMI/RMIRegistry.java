package iRMI;

import java.io.Serializable;
import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Eroyas on 22/05/16.
 */
public class RMIRegistry extends UnicastRemoteObject implements IRMIRegistry {

    

    protected RMIRegistry() throws RemoteException {
        super();
    }

    @Override
    public void bind(String key, Serializable obj) throws RemoteException, AlreadyBoundException {

    }

    @Override
    public void rebind(String key, Serializable obj) throws RemoteException {

    }

    @Override
    public void unbind(String key) throws RemoteException, NotBoundException {

    }

    @Override
    public Serializable lookup(String key) throws RemoteException, NotBoundException {
        return null;
    }

    @Override
    public String[] list() throws RemoteException {
        return new String[0];
    }

    @Override
    public String[] getLastList(int x) throws RemoteException {
        return new String[0];
    }

    @Override
    public Serializable[] getLastBind(int x) throws RemoteException {
        return new Serializable[0];
    }

}
