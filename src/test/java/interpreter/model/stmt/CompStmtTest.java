package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.type.IntType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompStmtTest {
    private PrgState state;
    private MyStack<IStmt> exeStack;

    @BeforeEach
    void setUp() throws MyException{
        exeStack = new MyStack<>();
        // Create a fresh state with empty ADTs for total isolation
        state = new PrgState(exeStack, new MyDictionary<>(), new MyList<>(), new NopStmt());
        exeStack.pop(); // Remove the dummy NopStmt pushed by the PrgState constructor!
    }

    @Test
    void executePushesStatementsInCorrectOrder() throws MyException {
        IStmt first = new VarDeclStmt("a", new IntType());
        IStmt second = new VarDeclStmt("b", new IntType());
        IStmt comp = new CompStmt(first, second);

        // Executing the compound statement should decompose it
        comp.execute(state);

        // According to Rule S1, the second statement is pushed first, then the first statement.
        // Therefore, popping them retrieves the first statement, then the second.
        assertEquals(first, exeStack.pop());
        assertEquals(second, exeStack.pop());
        assertTrue(exeStack.isEmpty());
    }
}