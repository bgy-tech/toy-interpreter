package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.type.BoolType;
import interpreter.model.type.IType;
import interpreter.model.type.StringType;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VarDeclStmtTest {
    private PrgState state;
    private IDictionary<String, IValue> symTable;

    @BeforeEach
    void setUp() {
        symTable = new MyDictionary<>();
        state = new PrgState(new MyStack<>(), symTable, new MyList<>(), new NopStmt());
    }

    @Test
    void varFirstDeclaredTest() throws MyException {
        IType type = new BoolType();
        IStmt newStmt = new VarDeclStmt("v", type);
        newStmt.execute(state);

        assertTrue(symTable.isDefined("v"));
    }
    //Default values -test cases:
    @Test
    void booleanDefaultValueTest() throws MyException {
        BoolType type = new BoolType();
        IStmt newStmt = new VarDeclStmt("v", type);
        newStmt.execute(state);

        assertEquals(type.defaultValue(), symTable.lookup("v"));
    }

    @Test
    void stringDefaultValueTest() throws MyException {
        StringType type = new StringType();
        IStmt newStmt = new VarDeclStmt("v", type);
        newStmt.execute(state);

        assertEquals(type.defaultValue(), symTable.lookup("v"));
    }

    @Test
    void redeclarationThrowsException() throws MyException {
        symTable.put("v", new IntValue(0));
        IStmt redeclare = new VarDeclStmt("v", new BoolType());
        assertThrows(StatementException.class, () -> redeclare.execute(state));
    }
}