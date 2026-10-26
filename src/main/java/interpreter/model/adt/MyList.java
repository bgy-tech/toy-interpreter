package interpreter.model.adt;

import interpreter.exceptions.ADTException;

import java.util.ArrayList;

public class MyList<T> implements IList<T> {
    ArrayList<T> out = new ArrayList<>();

    @Override
    public void add(T v) throws ADTException {
        if (v == null) throw new ADTException("Expression cant be null, try again!");
        out.add(v);
    }

    @Override
    public T get(int index) throws ADTException {
        if (out.size() <= index || index < 0){
            throw new ADTException("Index does not exist, try again!");
        }
        return out.get(index);

    }

    @Override
    public int size() {
        return out.size();
    }

    @Override
    public String toString(){
        return out.toString();
    }
}
