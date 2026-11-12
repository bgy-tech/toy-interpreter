package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.IStack;
import interpreter.model.exp.IExp;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IfStmtTest {

    private PrgState state;
    private IStack<IStmt> exeStack;

    private static IExp b(boolean val) { return new ValueExp(new BoolValue(val)); }
    private static IExp num(int val) { return new ValueExp(new IntValue(val)); }

    @BeforeEach
    void setUp() {
        state = PrgState.of(new NopStmt());
        exeStack = state.getExeStack();
    }

    @Test
    void trueConditionPushesThenStatement() throws MyException {
        IStmt thenStmt = new PrintStmt(num(1));
        IStmt elseStmt = new PrintStmt(num(2));
        IStmt ifStmt = new IfStmt(b(true), thenStmt, elseStmt);

        ifStmt.execute(state);
        assertEquals(thenStmt, exeStack.pop());
    }

    @Test
    void falseConditionPushesElseStatement() throws MyException {
        IStmt thenStmt = new PrintStmt(num(1));
        IStmt elseStmt = new PrintStmt(num(2));
        IStmt ifStmt = new IfStmt(b(false), thenStmt, elseStmt);

        ifStmt.execute(state);
        assertEquals(elseStmt, exeStack.pop());
    }

    @Test
    void nonBooleanConditionThrowsException() {
        IStmt thenStmt = new NopStmt();
        IStmt elseStmt = new NopStmt();
        IStmt ifStmt = new IfStmt(num(10), thenStmt, elseStmt);

        var exc = assertThrows(StatementException.class, () -> ifStmt.execute(state));
        assertNotNull(exc.getMessage());
    }
}