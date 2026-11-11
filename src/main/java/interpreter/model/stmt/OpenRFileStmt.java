package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;

import interpreter.exceptions.StatementException;
import interpreter.model.exp.IExp;
import interpreter.model.value.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public record OpenRFileStmt(IExp exp) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        // 1. Evaluate the expression
        var val = exp.eval(state.getSymTable());
        if (!(val instanceof StringValue fileName)) {
            //if it is an instance ==> filename = (StringValue) val
            throw new StatementException("openRFile: " + exp + " is not a string");
        }

        var fileTable = state.getFileTable();

        // 2. Check if already open
        if (fileTable.isDefined(fileName)) {
            throw new StatementException("openRFile: " + fileName + " is already open");
        }

        // 3. Open the reader (NO try-with-resources: must stay open)
        try {
            var reader = new BufferedReader(new FileReader(fileName.val()));
            fileTable.put(fileName, reader);
        } catch (IOException e) {
            throw new StatementException("openRFile: cannot open " + fileName + ": " + e.getMessage());
        }

        return state;
    }

    @Override
    public String toString() {
        return "openRFile(" + exp + ")";
    }
}