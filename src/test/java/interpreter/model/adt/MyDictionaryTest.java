package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import interpreter.exceptions.MyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyDictionaryTest {
    private IDictionary<String, Integer> symTable;

    @BeforeEach
    void setUp(){
        symTable = new MyDictionary<>();
    }

    @Test
    void putTest() throws MyException {
        symTable.put("david", 5);
        assertTrue(symTable.isDefined("david"));
        var exc = assertThrows(ADTException.class, () -> symTable.put(null, 5));
        assertEquals("incorrect input values, try again", exc.getMessage());
    }

    @Test
    void lookupTest() throws MyException {
        symTable.put("mum", 40);
        symTable.put("dad", 50);
        assertEquals(50, symTable.lookup("dad"));
        var exc = assertThrows(ADTException.class, ()->symTable.lookup("granddad"));
        assertEquals("no name granddad is in the SysTable", exc.getMessage());
    }

    @Test
    void updateTest() throws MyException{
        var exc = assertThrows(ADTException.class, () ->symTable.update("david", 20));
        assertEquals("no name " + "david" + " is in the SysTable", exc.getMessage());
        symTable.put("david", 19);
        symTable.update("david",20);
        assertEquals(20, symTable.lookup("david"));
    }

}
