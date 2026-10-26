package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NopStmtTest {
    @Test
    void nopLeavesStateUnchanged() throws MyException {
        IStmt initialProgram = new NopStmt();
        PrgState state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new NopStmt());
        IStmt nop = new NopStmt();

        nop.execute(state);

        // Verify the stack was not modified (it should NOT be empty)
        assertFalse(state.getExeStack().isEmpty());

        // Verify the stack still contains exactly what it started with
        assertEquals(initialProgram, state.getExeStack().pop());
    }
}