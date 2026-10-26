package interpreter.model.exp;

import static org.junit.jupiter.api.Assertions.*;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

class ArithExpTest {

    private final IDictionary<String, IValue> symTable = new MyDictionary<>();
    private static IExp num(int n) { return new ValueExp(new IntValue(n)); }
    private static IExp b(boolean n) { return new ValueExp(new BoolValue(n)); }

    @Test
    void respectsOperatorPrecedenceEncodedInTheTree() throws MyException {
        IExp e = new ArithExp(ArithOp.ADD, num(2),
                new ArithExp(ArithOp.MUL, num(3), num(5)));
        assertEquals(new IntValue(17), e.eval(symTable));
    }

    @Test
    void subtractionAndValidDivisionWork() throws MyException {
        IExp subExp = new ArithExp(ArithOp.SUB, num(10), num(4));
        assertEquals(new IntValue(6), subExp.eval(symTable));

        IExp divExp = new ArithExp(ArithOp.DIV, num(20), num(5));
        assertEquals(new IntValue(4), divExp.eval(symTable));
    }

    @Test
    void divisionByZeroThrows() {
        IExp e = new ArithExp(ArithOp.DIV, num(1), num(0));
        var ex = assertThrows(ExpressionException.class, () -> e.eval(symTable));
        assertEquals("division by zero", ex.getMessage());
    }

    @Test
    void nonIntegerOperandTest() {
        IExp e1 = new ArithExp(ArithOp.ADD, b(true), num(1));
        var exc1 = assertThrows(ExpressionException.class, () -> e1.eval(symTable));
        assertEquals("first operand is not an integer", exc1.getMessage());

        IExp e2 = new ArithExp(ArithOp.ADD, num(1), b(true));
        var exc2 = assertThrows(ExpressionException.class, () -> e2.eval(symTable));
        assertEquals("second operand is not an integer", exc2.getMessage());
    }
}