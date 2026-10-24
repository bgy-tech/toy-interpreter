package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;

public record CompStmt(IStmt first, IStmt second) implements IStmt { // S1
    @Override
    public PrgState execute(PrgState state) throws MyException {
        if(first == null || second == null) throw new MyException("The statements cant be null, try again!");
        state.getStack().push(second);
        state.getStack().push(first);
        return state;
    }
    @Override public String toString() { return "(" + first + "; " + second + ")"; }
}

