package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.IStack;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IfStmtTest {
    private PrgState state;
    private IStack<IStmt> exeStack;
    private IStmt thenStmt;
    private IStmt elseStmt;

    @BeforeEach
    void setUp() {
        exeStack = new MyStack<>();
        state = new PrgState(exeStack, new MyDictionary<>(), new MyList<>(), new NopStmt());
        thenStmt = new PrintStmt(new ValueExp(new IntValue(1)));
        elseStmt = new PrintStmt(new ValueExp(new IntValue(0)));
    }

    @Test
    void trueConditionPushesThenStatement() throws MyException {
        IStmt ifStmt = new IfStmt(new ValueExp(new BoolValue(true)), thenStmt, elseStmt);
        ifStmt.execute(state);
        assertEquals(thenStmt, exeStack.pop());
    }

    @Test
    void falseConditionPushesElseStatement() throws MyException {
        IStmt ifStmt = new IfStmt(new ValueExp(new BoolValue(false)), thenStmt, elseStmt);
        ifStmt.execute(state);
        assertEquals(elseStmt, exeStack.pop());
    }

    @Test
    void nonBooleanConditionThrowsException() {
        IStmt ifStmt = new IfStmt(new ValueExp(new IntValue(5)), thenStmt, elseStmt);
        assertThrows(StatementException.class, () -> ifStmt.execute(state));
    }
}