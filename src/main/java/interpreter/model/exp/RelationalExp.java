package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;

public record RelationalExp(RelOp op, IExp left, IExp right) implements IExp {

    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException {
        if (!(left.eval(symTable) instanceof IntValue(int n1))) {
            throw new ExpressionException("first operand is not a integer");
        }
        if (!(right.eval(symTable) instanceof IntValue(int n2))) {
            throw new ExpressionException("second operand is not an integer");
        }
        return switch (op) {
            case GREATER_THEN -> new BoolValue(n1 > n2);
            case LESS_THEN -> new BoolValue(n1 < n2);
            case GREATER_OR_EQUAL -> new BoolValue(n1 >= n2);
            case LESS_OR_EQUAL -> new BoolValue(n1 <= n2);
            case EQUAL -> new BoolValue(n1 == n2);
            case NOT_EQUAL -> new BoolValue(n1 != n2);
        };

    }
}
