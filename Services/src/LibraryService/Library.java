package LibraryService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Created by Eroyas on 22/05/16.
 */
public class Library extends UnicastRemoteObject implements ILibrary, Serializable {

    Map<String, String> lib;

    protected Library() throws RemoteException {
        lib = new HashMap<>();
    }

    @Override
    public String getBook(String book) throws RemoteException {
        return lib.get(book);
    }

    @Override
    public void addBook(String book, String description) throws RemoteException {
        lib.put(book, description);
    }
}
