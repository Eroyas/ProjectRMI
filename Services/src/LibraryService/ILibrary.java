package LibraryService;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Eroyas on 22/05/16.
 */
public interface ILibrary extends Remote {

    String getBook(String book) throws RemoteException;

    void addBook(String book, String description) throws RemoteException;
}
