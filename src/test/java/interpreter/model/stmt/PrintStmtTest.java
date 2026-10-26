package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.IList;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintStmtTest {
    private PrgState state;
    private IList<IValue> outList;

    @BeforeEach
    void setUp() {
        outList = new MyList<>();
        state = new PrgState(new MyStack<>(), new MyDictionary<>(), outList, new NopStmt());
    }

    @Test
    void printAppendsToOutList() throws MyException {
        IStmt printStmt = new PrintStmt(new ValueExp(new IntValue(10)));
        printStmt.execute(state);

        assertEquals(1, outList.size());
        assertEquals(new IntValue(10), outList.get(0));
    }
}