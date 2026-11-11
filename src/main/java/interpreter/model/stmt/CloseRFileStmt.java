package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public record CloseRFileStmt(IExp exp) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        // 1. Evaluate expression to a StringValue
        var val = exp.eval(state.getSymTable());
        if (!(val instanceof StringValue fileName)) {
            throw new StatementException("closeRFile: " + exp + " is not a string");
        }

        var fileTable = state.getFileTable();

        // 2. Check if file is open in FileTable
        if (!fileTable.isDefined(fileName)) {
            throw new StatementException("closeRFile: file " + fileName + " is not open");
        }

        // 3. Close the stream and remove it from the table
        BufferedReader reader = fileTable.lookup(fileName);
        try {
            reader.close();
            fileTable.remove(fileName);
        } catch (IOException e) {
            throw new StatementException("closeRFile: error closing " + fileName + ": " + e.getMessage());
        }

        return state;
    }

    @Override
    public String toString() {
        return "closeRFile(" + exp + ")";
    }
}