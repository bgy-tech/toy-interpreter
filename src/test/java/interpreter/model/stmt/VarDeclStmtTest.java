package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.IDictionary;
import interpreter.model.type.BoolType;
import interpreter.model.type.IntType;
import interpreter.model.type.StringType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VarDeclStmtTest {

    private PrgState state;
    private IDictionary<String, IValue> symTable;

    @BeforeEach
    void setUp() {
        state = PrgState.of(new NopStmt());
        symTable = state.getSymTable();
    }

    @Test
    void varFirstDeclaredTest() throws MyException {
        IStmt varStmt = new VarDeclStmt("x", new IntType());
        varStmt.execute(state);

        assertTrue(symTable.isDefined("x"));
        assertEquals(new IntValue(0), symTable.lookup("x"));
    }

    @Test
    void booleanDefaultValueTest() throws MyException {
        IStmt varStmt = new VarDeclStmt("flag", new BoolType());
        varStmt.execute(state);

        assertEquals(new BoolValue(false), symTable.lookup("flag"));
    }

    @Test
    void stringDefaultValueTest() throws MyException {
        IStmt varStmt = new VarDeclStmt("s", new StringType());
        varStmt.execute(state);

        assertEquals(new StringValue(""), symTable.lookup("s"));
    }

    @Test
    void redeclarationThrowsException() throws MyException {
        IStmt varStmt1 = new VarDeclStmt("x", new IntType());
        IStmt varStmt2 = new VarDeclStmt("x", new BoolType());

        varStmt1.execute(state);
        var exc = assertThrows(StatementException.class, () -> varStmt2.execute(state));
        assertNotNull(exc.getMessage());
    }
}