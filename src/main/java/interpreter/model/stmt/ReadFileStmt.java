package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.type.IntType;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public record ReadFileStmt(IExp exp, String varName) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        var symTable = state.getSymTable();

        // 1. Check if varName is declared in SymTable
        if (!symTable.isDefined(varName)) {
            throw new StatementException("readFile: variable '" + varName + "' is not declared");
        }

        // 2. Check if varName has IntType
        if (!symTable.lookup(varName).getType().equals(new IntType())) {
            throw new StatementException("readFile: variable '" + varName + "' is not of type int");
        }

        // 3. Evaluate expression to a StringValue
        var val = exp.eval(symTable);
        if (!(val instanceof StringValue fileName)) {
            throw new StatementException("readFile: " + exp + " does not evaluate to a string");
        }

        var fileTable = state.getFileTable();

        // 4. Check if file is registered in FileTable
        if (!fileTable.isDefined(fileName)) {
            throw new StatementException("readFile: file " + fileName + " is not open");
        }

        // 5. Read line from the BufferedReader
        BufferedReader reader = fileTable.lookup(fileName);
        try {
            String line = reader.readLine();
            int intValue;
            if (line == null) {
                // End of file reached -> default value 0
                intValue = 0;
            } else {
                intValue = Integer.parseInt(line.strip());
            }
            symTable.update(varName, new IntValue(intValue));
        } catch (IOException e) {
            throw new StatementException("readFile: error reading from " + fileName + ": " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new StatementException("readFile: file line is not a valid integer in " + fileName);
        }

        return state;
    }

    @Override
    public String toString() {
        return "readFile(" + exp + ", " + varName + ")";
    }
}