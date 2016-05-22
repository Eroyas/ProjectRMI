package iRMI;

import java.io.Serializable;
import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Eroyas on 22/05/16.
 */
public interface IRMIRegistry extends Remote {
    /**
     * Binds a remote reference to the specified key in the registry
     *
     * @param key
     * @param obj
     * @throws RemoteException
     * @throws AlreadyBoundException
     */
    void bind(String key, Serializable obj) throws RemoteException, AlreadyBoundException;

    /**
     * TODO
     *
     * @param key
     * @param obj
     * @throws RemoteException
     */
    void rebind(String key, Serializable obj) throws RemoteException;

    void unbind(String key) throws RemoteException, NotBoundException;

    Serializable lookup(String key) throws RemoteException, NotBoundException;

    String[] list() throws RemoteException;

    String[] getLastList(int x) throws RemoteException;

    Serializable[] getLastBind(int x) throws RemoteException;

    // TODO -> ajouter plus de spéc
}
