package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import java.util.Map;
import java.util.HashMap;

public class MyDictionary<K,V> implements IDictionary<K,V>{
    private final Map<K,V> sysTable = new HashMap<>();

    @Override
    public boolean isDefined(K name) {
      /*  V v = sysTable.get(name);
        return v != null;
        OR:*/
        return sysTable.containsKey(name);
    }

    @Override
    public V lookup(K name) throws ADTException {
        V v = sysTable.get(name);
        if (v == null)
            throw new ADTException("no name " + name + " is in the SysTable");
        return v;
    }

    @Override
    public void put(K name, V value) throws ADTException {
        if ( name == null || value == null) throw new ADTException("incorrect input values, try again");
        sysTable.put(name, value);
    }

    @Override
    public void update(K name, V value) throws ADTException {
        V v = sysTable.get(name);
        if (v == null)
            throw new ADTException("no name " + name + " is in the SysTable");
        sysTable.put(name, value);
    }
    @Override
    public String toString(){
        return sysTable.toString();
    }
}
