package interpreter.model.adt;

import interpreter.exceptions.ADTException;

public interface IDictionary<K, V> {
    //k - name, v -value stored in SymTable
     boolean isDefined(K name);
     V lookup(K name) throws ADTException;
     void put(K name , V value) throws ADTException;
     void update(K name , V value) throws ADTException;


}
