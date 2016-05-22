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
     * @param key the String to associate with the Serializable Object
     * @param obj a Serializable Object
     * @throws RemoteException if remote communication with the registry failed
     * @throws AlreadyBoundException
     */
    void bind(String key, Serializable obj) throws RemoteException, AlreadyBoundException;

    /**
     * Replaces the binding for the specified key in this registry with the supplied Serializable object reference.
     *
     * @param key the String to associate with the Serializable Object
     * @param obj a Serializable Object
     * @throws RemoteException if remote communication with the registry failed
     */
    void rebind(String key, Serializable obj) throws RemoteException;

    /**
     * Removes the binding for the specified key in this registry.
     *
     * @param key the String associate with the Serializable Object we want to delete
     * @throws RemoteException if remote communication with the registry failed
     * @throws NotBoundException
     */
    void unbind(String key) throws RemoteException, NotBoundException;

    /**
     * Returns the Serializable object bound to the specified key in this registry.
     *
     * @param key the String associate with the Serializable Object we want
     * @return Serializable Object
     * @throws RemoteException if remote communication with the registry failed
     * @throws NotBoundException
     */
    Serializable lookup(String key) throws RemoteException, NotBoundException;

    /**
     * Returns an array of the key bound in this registry.
     *
     * @return a list of all the key associate with Serializable object
     * @throws RemoteException if remote communication with the registry failed
     */
    String[] list() throws RemoteException;

    /**
     * Returns an array of last 'x' key bound in this registry.
     *
     * @param x a integer
     * @return a list of the x last key associate with Serializable object
     * @throws RemoteException if remote communication with the registry failed
     */
    String[] getLastList(int x) throws RemoteException;

    /**
     * Returns an array of last 'x' Serializable object bound in this registry.
     *
     * @param x a integer
     * @return a list of the x last Serializable object associated
     * @throws RemoteException if remote communication with the registry failed
     */
    Serializable[] getLastBind(int x) throws RemoteException;

    // TODO -> ajouter plus de spéc
}
