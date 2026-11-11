package interpreter.model;

import interpreter.model.adt.*;
import interpreter.model.stmt.IStmt;
import interpreter.model.value.IValue;
import interpreter.model.value.StringValue;

import java.io.BufferedReader;

public class PrgState {
    final private IStack<IStmt> exeStack;
    final private IDictionary<String, IValue> symTable;
    final private IList<IValue> out;
    private IDictionary<StringValue, BufferedReader> fileTable;
    final private IStmt originalProgram;

    public PrgState(IStack<IStmt> exeStack, IDictionary<String, IValue> symTable,
                    IList<IValue> out, IDictionary<StringValue, BufferedReader> fileTable, IStmt program) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.originalProgram = program;
        exeStack.push(program);
    }

    // Static factory to initialize fresh empty structures
    public static PrgState of(IStmt program) {
        return new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyDictionary<>(),
                program
        );
    }

    // getters, setters, toString ...
    //setters:
    public void setFileTable(IDictionary<StringValue, BufferedReader> fileTable) { this.fileTable = fileTable;}
    //getters:
    public IDictionary<StringValue, BufferedReader> getFileTable() { return fileTable; }
    public IStack<IStmt> getExeStack() { return exeStack; }
    public IDictionary<String, IValue> getSymTable() { return symTable; }
    public IList<IValue> getOut() { return out; }
    public IStmt getStatements() { return originalProgram; }

    @Override
    public String toString() {
        return "Execution Stack: \n" + exeStack.toString() + "\n" +
                "Symbol Table: \n" + symTable.toString() + "\n" +
                "Output List: \n" + out.toString() + "\n" +
                "------------------------------------------";
    }

}

