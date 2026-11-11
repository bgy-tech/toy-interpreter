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

public class RelationalExpTest {
    private final IDictionary<String, IValue> symTable = new MyDictionary<>();
    private static IExp num(int n) { return new ValueExp(new IntValue(n)); }
    private static IExp b(boolean n) { return new ValueExp(new BoolValue(n)); }

    @Test
    void greaterOrEqualTest() throws MyException {
        IExp ge1 = new RelationalExp(RelOp.GE, num(5), num(5));
        assertEquals(new BoolValue(true), ge1.eval(symTable));

        IExp ge2 = new RelationalExp(RelOp.GE, num(5), num(4));
        assertEquals(new BoolValue(true), ge2.eval(symTable));

        IExp falseExp = new RelationalExp(RelOp.GE, num(4), num(5));
        assertEquals(new BoolValue(false), falseExp.eval(symTable));
    }

    @Test
    void lessOrEqualTest() throws MyException {
        IExp le1 = new RelationalExp(RelOp.LE, num(5), num(5));
        assertEquals(new BoolValue(true), le1.eval(symTable));

        IExp le2 = new RelationalExp(RelOp.LE, num(4), num(5));
        assertEquals(new BoolValue(true), le2.eval(symTable));

        IExp falseExp = new RelationalExp(RelOp.LE, num(5), num(4));
        assertEquals(new BoolValue(false), falseExp.eval(symTable));
    }

    @Test
    void greaterThanTest() throws MyException {
        IExp gt = new RelationalExp(RelOp.GT, num(5), num(4));
        assertEquals(new BoolValue(true), gt.eval(symTable));

        IExp falseExp = new RelationalExp(RelOp.GT, num(4), num(5));
        assertEquals(new BoolValue(false), falseExp.eval(symTable));
    }

    @Test
    void lessThanTest() throws MyException {
        IExp lt = new RelationalExp(RelOp.LT, num(4), num(5));
        assertEquals(new BoolValue(true), lt.eval(symTable));

        IExp falseExp = new RelationalExp(RelOp.LT, num(5), num(4));
        assertEquals(new BoolValue(false), falseExp.eval(symTable));
    }

    @Test
    void equalTest() throws MyException {
        IExp eq = new RelationalExp(RelOp.EQ, num(5), num(5));
        assertEquals(new BoolValue(true), eq.eval(symTable));

        IExp falseExp = new RelationalExp(RelOp.EQ, num(5), num(0));
        assertEquals(new BoolValue(false), falseExp.eval(symTable));
    }

    @Test
    void notEqualTest() throws MyException {
        IExp ne = new RelationalExp(RelOp.NE, num(5), num(0));
        assertEquals(new BoolValue(true), ne.eval(symTable));

        IExp falseExp = new RelationalExp(RelOp.NE, num(5), num(5));
        assertEquals(new BoolValue(false), falseExp.eval(symTable));
    }

    @Test
    void nonIntegerOperandThrowsException() {
        IExp invalidFirst = new RelationalExp(RelOp.NE, b(true), num(5));
        var exc1 = assertThrows(ExpressionException.class, () -> invalidFirst.eval(symTable));
        assertEquals("first operand is not an integer", exc1.getMessage());

        IExp invalidSecond = new RelationalExp(RelOp.EQ, num(5), b(true));
        var exc2 = assertThrows(ExpressionException.class, () -> invalidSecond.eval(symTable));
        assertEquals("second operand is not an integer", exc2.getMessage());
    }

    @Test
    void toStringFormatsInfixCorrectly() {
        IExp exp = new RelationalExp(RelOp.LT, num(2), num(3));
        assertEquals("(2 < 3)", exp.toString());
    }
}