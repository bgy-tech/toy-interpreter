package interpreter.model;

import interpreter.model.adt.*;
import interpreter.model.stmt.IStmt;
import interpreter.model.value.IValue;

public class PrgState {
    final private IStack<IStmt> exeStack;
    final private IDictionary<String, IValue> symTable;
    final private IList<IValue> out;
    final private IStmt originalProgram;

    public PrgState(IStack<IStmt> exeStack, IDictionary<String, IValue> symTable,
                    IList<IValue> out, IStmt program) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = program;
        exeStack.push(program);
    }
    // getters, setters, toString ...
    public IStack<IStmt> getStack() { return exeStack; }
    public IDictionary<String, IValue> getSymTable() { return symTable; }
    public IList<IValue> getOUT() { return out; }
    public IStmt getStatements() { return originalProgram; }

    @Override
    public String toString() {
        return "Execution Stack: \n" + exeStack.toString() + "\n" +
                "Symbol Table: \n" + symTable.toString() + "\n" +
                "Output List: \n" + out.toString() + "\n" +
                "------------------------------------------";
    }

}

