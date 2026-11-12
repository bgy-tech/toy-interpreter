package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.IDictionary;
import interpreter.model.exp.IExp;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssignStmtTest {

    private PrgState state;
    private IDictionary<String, interpreter.model.value.IValue> symTable;

    private static IExp num(int n) { return new ValueExp(new IntValue(n)); }
    private static IExp b(boolean n) { return new ValueExp(new BoolValue(n)); }

    @BeforeEach
    void setUp() {
        state = PrgState.of(new NopStmt());
        symTable = state.getSymTable();
    }

    @Test
    void assignToDefinedTest() throws Exception {
        symTable.put("x", new IntValue(2));
        IStmt assignStmt = new AssignStmt("x", num(3));
        assignStmt.execute(state);
        assertEquals(new IntValue(3), symTable.lookup("x"));
    }

    @Test
    void assignWithWrongTypeThrowsException() throws Exception {
        symTable.put("x", new IntValue(2));
        IStmt assignStmt = new AssignStmt("x", b(false));
        var exc = assertThrows(StatementException.class, () -> assignStmt.execute(state));
        assertNotNull(exc.getMessage());
    }

    @Test
    void assignUndefinedVariableThrowsException() {
        IStmt assignStmt = new AssignStmt("x", num(1));
        var exc = assertThrows(StatementException.class, () -> assignStmt.execute(state));
        assertNotNull(exc.getMessage());
    }
}