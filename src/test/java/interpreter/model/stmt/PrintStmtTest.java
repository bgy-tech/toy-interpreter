package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrintStmtTest {

    @Test
    void printAppendsToOutList() throws MyException {
        PrgState state = PrgState.of(new NopStmt());
        IList<IValue> out = state.getOut();

        IStmt print1 = new PrintStmt(new ValueExp(new IntValue(10)));
        IStmt print2 = new PrintStmt(new ValueExp(new IntValue(20)));

        print1.execute(state);
        print2.execute(state);

        assertEquals(2, out.size());
        assertEquals(new IntValue(10), out.get(0));
        assertEquals(new IntValue(20), out.get(1));
    }
}