package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.exp.VarExp;
import interpreter.model.type.IntType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompStmtTest {

    @Test
    void executePushesStatementsInCorrectOrder() throws MyException {
        PrgState state = PrgState.of(new NopStmt());
        IStack<IStmt> exeStack = state.getExeStack();
        exeStack.pop(); // clear initial NopStmt

        IStmt first = new VarDeclStmt("a", new IntType());
        IStmt second = new PrintStmt(new VarExp("a"));
        IStmt comp = new CompStmt(first, second);

        comp.execute(state);

        // First statement must be at the top of the stack (popped first)
        assertEquals(first, exeStack.pop());
        assertEquals(second, exeStack.pop());
        assertTrue(exeStack.isEmpty());
    }
}