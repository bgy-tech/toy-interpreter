package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RelationalExpTest {
    private final IDictionary<String, IValue> symTable = new MyDictionary<>();
    private static IExp num(int n) { return new ValueExp(new IntValue(n)); }
    private static IExp b(boolean n) { return new ValueExp(new BoolValue(n)); }

    //all relation ops tests:
    @Test
    void greaterOrEqualTest() throws MyException {

        IExp greaterOrEqualExp1 = new RelationalExp(RelOp.GREATER_OR_EQUAL, num(5), num(5));
        assertEquals(new BoolValue(true), greaterOrEqualExp1.eval(symTable));
        IExp greaterOrEqualExp2 = new RelationalExp(RelOp.GREATER_OR_EQUAL, num(5), num(4));
        assertEquals(new BoolValue(true), greaterOrEqualExp2.eval(symTable));

        IExp falseExp = new RelationalExp(RelOp.GREATER_OR_EQUAL, num(4), num(5));
        assertEquals(new BoolValue(false), falseExp.eval(symTable));
    }
    @Test
    void lessOrEqualTest() throws MyException
    {
        IExp lessOrEqualExp1 = new RelationalExp(RelOp.LESS_OR_EQUAL, num(5), num(5));
        assertEquals(new BoolValue(true), lessOrEqualExp1.eval(symTable));
        IExp lessOrEqualExp2 = new RelationalExp(RelOp.LESS_OR_EQUAL, num(4), num(5));
        assertEquals(new BoolValue(true), lessOrEqualExp2.eval(symTable));

        IExp falseExp = new RelationalExp(RelOp.LESS_OR_EQUAL, num(5), num(4));
        assertEquals(new BoolValue(false), falseExp.eval(symTable));
    }

    @Test
    void greaterThenTest() throws MyException {
       IExp greaterThenExp = new RelationalExp(RelOp.GREATER_THEN, num(5), num(4));
       assertEquals(new BoolValue(true), greaterThenExp.eval(symTable));

       IExp falseExp = new RelationalExp(RelOp.GREATER_THEN, num(4), num(5));
       assertEquals(new BoolValue(false), falseExp.eval(symTable));
    }

    @Test
    void lessThenTest() throws MyException
    {
        IExp lessThenExp = new RelationalExp(RelOp.LESS_THEN, num(4), num(5));
        assertEquals(new BoolValue(true), lessThenExp.eval(symTable));

        IExp falseExp = new RelationalExp(RelOp.LESS_THEN, num(5), num(4));
        assertEquals(new BoolValue(false), falseExp.eval(symTable));

    }
    @Test
    void equalTest() throws MyException {

        IExp equalExp = new RelationalExp(RelOp.EQUAL, num(5), num(5));
        assertEquals(new BoolValue(true), equalExp.eval(symTable));

        IExp falseExp = new RelationalExp(RelOp.EQUAL, num(5), num(0));
        assertEquals(new BoolValue(false), falseExp.eval(symTable));
    }
    @Test
    void notEqualTest() throws MyException {
        IExp notEqualExp = new RelationalExp(RelOp.NOT_EQUAL, num(5), num(0));
        assertEquals(new BoolValue(true), notEqualExp.eval(symTable));

        IExp falseExp = new RelationalExp(RelOp.NOT_EQUAL, num(5), num(5));
        assertEquals(new BoolValue(false), falseExp.eval(symTable));
    }


    @Test
    void nonIntegerOperandThrowsException() {
        IExp invalidFirst = new RelationalExp(RelOp.NOT_EQUAL, b(true), num(5));
        var exc1 = assertThrows(ExpressionException.class, () -> invalidFirst.eval(symTable));
        assertEquals("first operand is not a integer", exc1.getMessage());

        IExp invalidSecond = new RelationalExp(RelOp.EQUAL, num(5), b(true));
        var exc2 = assertThrows(ExpressionException.class, () -> invalidSecond.eval(symTable));
        assertEquals("second operand is not an integer", exc2.getMessage());
    }

}

