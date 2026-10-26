package interpreter.model.exp;

import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VarExpTest {
    private final IDictionary<String, IValue> symTable = new MyDictionary<>();
    private IValue num(int n) { return new IntValue(n);}

    @Test
    void testEval() throws MyException {
        symTable.put("Integer",num(4));
        VarExp varExp1 = new VarExp("Integer");
        assertEquals(num(4), varExp1.eval(symTable));
    }

    @Test
    void undefinedVarTest() throws MyException{
        assertThrows(MyException.class,()->{
            VarExp varExp2 = new VarExp("boolean");
            varExp2.eval(symTable);
        });
    }

}
