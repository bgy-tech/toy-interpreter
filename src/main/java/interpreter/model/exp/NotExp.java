package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;

public record NotExp(IExp exp) implements IExp {
    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException {
        if (!(exp.eval(symTable) instanceof BoolValue(boolean b)))
            throw new ExpressionException("Operand is not a boolean");
        return new BoolValue(!b);
    }

    @Override
    public String toString() { return "!(" + exp + ")"; }
}