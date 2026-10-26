package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicExpTest {
    private final IDictionary<String, IValue> symTable = new MyDictionary<>();
    private IExp b(boolean val) { return new ValueExp(new BoolValue(val)); }
    private IExp num(int val) { return new ValueExp(new IntValue(val)); }

    @Test
    void validLogicalEvaluation() throws MyException {
        IExp andExp = new LogicExp(LogicOp.AND, b(true), b(false));
        assertEquals(new BoolValue(false), andExp.eval(symTable));

        IExp orExp = new LogicExp(LogicOp.OR, b(true), b(false));
        assertEquals(new BoolValue(true), orExp.eval(symTable));
    }

    @Test
    void nonBooleanOperandThrowsException() {
        IExp invalidFirst = new LogicExp(LogicOp.AND, num(5), b(true));
        var exc1 = assertThrows(ExpressionException.class, () -> invalidFirst.eval(symTable));
        assertEquals("first operand is not a boolean", exc1.getMessage());

        IExp invalidSecond = new LogicExp(LogicOp.OR, b(true), num(5));
        var exc2 = assertThrows(ExpressionException.class, () -> invalidSecond.eval(symTable));
        assertEquals("second operand is not a boolean", exc2.getMessage());
    }
}