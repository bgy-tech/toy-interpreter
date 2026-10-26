package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import interpreter.exceptions.MyException;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyStackTest {
    private IStack<IValue> stack;

    @BeforeEach
    void setUp(){
        stack = new MyStack<>();
    }

    @Test
    void lifoOrderTest() throws MyException {
        stack.push(new IntValue(2));
        stack.push(new IntValue(3));
        stack.push(new IntValue(4));
        stack.pop();
        stack.pop();
        IValue lastValue = stack.pop();
        assertEquals(new IntValue(2), lastValue);
        assertTrue(stack.isEmpty());

    }

    @Test
    void popEmptyStackTest() throws MyException{
        var exception = assertThrows(ADTException.class, () -> stack.pop());
        assertEquals("pop from an empty stack", exception.getMessage());
    }




}
