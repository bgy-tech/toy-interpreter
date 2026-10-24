package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import interpreter.model.value.IValue;

public interface IStack<T> {
    void push(T v);
    T pop() throws ADTException;
    boolean isEmpty();
}
