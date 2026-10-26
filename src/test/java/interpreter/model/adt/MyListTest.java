package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import interpreter.exceptions.MyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyListTest {
    private IList<Integer> out;

    @BeforeEach
    void setUp(){
        out = new MyList<>();
    }

    @Test
    void addTest() throws MyException {
        var exc = assertThrows(ADTException.class, ()->out.add(null));
        assertEquals("Expression cant be null, try again!", exc.getMessage());

        out.add(5);
        assertEquals(1,out.size());
    }

    @Test
    void getTest() throws MyException{
        var exc = assertThrows(ADTException.class, () -> out.get(-1));
        assertEquals("Index does not exist, try again!",exc.getMessage());
        out.add(5);
        out.add(6);
        assertEquals(5, (int) out.get(0));
    }

    @Test
    void sizeTest() throws MyException{
        out.add(1);
        out.add(2);
        out.add(3);
        assertEquals(3, out.size());

    }
}
