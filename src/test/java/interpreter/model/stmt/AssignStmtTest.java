package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AssignStmtTest {
    private PrgState state;
    private IDictionary<String, IValue> symTable;

    @BeforeEach
    void setUp() {
        symTable = new MyDictionary<>();
        state = new PrgState(new MyStack<>(), symTable, new MyList<>(), new NopStmt());
    }

    @Test
    void assignToDefinedTest() throws MyException {
        //simulates that v was already declared
        symTable.put("v", new IntValue(5));
        IStmt assign = new AssignStmt("v", new ValueExp(new IntValue(9)));
        assign.execute(state);

        assertEquals(new IntValue(9), symTable.lookup("v"));
    }

    @Test
    void assignToUndefined(){
        // v not in symTable
        IStmt assign = new AssignStmt("v", new ValueExp(new IntValue(9)));
        assertThrows(StatementException.class,()->assign.execute(state));
    }

    @Test
    void typeMismatchThrowsException() throws MyException {
        // Simulate that "b" is declared as a boolean
        symTable.put("b", new BoolValue(true));

        // Attempting to assign an integer to a boolean variable
        IStmt assign = new AssignStmt("b", new ValueExp(new IntValue(5)));

        assertThrows(StatementException.class, () -> assign.execute(state));
    }
}
