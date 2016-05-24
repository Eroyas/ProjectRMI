package RMI;

import java.io.Serializable;
import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Eroyas on 22/05/16.
 *
 * Javadocs complete in file IRMIRegistry
 */
public class Registry {

    private static Map<String, Serializable> registry = new HashMap<>();
    private static List<String> orderRegistry = new ArrayList<>();

    public void bind(String key, Serializable obj) throws AlreadyBoundException {
        if (orderRegistry.contains(key))
            throw new AlreadyBoundException();
        registry.put(key, obj);
        orderRegistry.add(key);
    }

    public void rebind(String key, Serializable obj) {
        if (orderRegistry.contains(key)){
            registry.replace(key, obj);
            orderRegistry.remove(key);
        } else
            registry.put(key, obj);
        orderRegistry.add(key);
    }

    public void unbind(String key) throws NotBoundException {
        if (orderRegistry.contains(key)) {
            registry.remove(key);
            orderRegistry.remove(key);
        }
        throw new NotBoundException();
    }

    public Serializable lookup(String key) throws NotBoundException {
        if (orderRegistry.contains(key)) {
            return registry.get(key);
        }
        throw new NotBoundException();
    }

    public String[] list() {
        return orderRegistry.toArray(new String[orderRegistry.size()]);
    }

    public String[] getLastList(int x) {
        String[] res = new String[x];
        int j = 0, i = orderRegistry.size();
        while(j < x)
            res[j++] = orderRegistry.get(--i);
        return res;
    }

    public Serializable[] getLastBind(int x) {
        Serializable[] res = new Serializable[x];
        int j = 0, i = orderRegistry.size();
        while(j < x)
            res[j++] = registry.get(orderRegistry.get(--i));
        return res;
    }

}
