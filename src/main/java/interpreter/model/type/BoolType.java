package interpreter.model.type;

import interpreter.model.value.IValue;
import interpreter.model.value.BoolValue;
public record BoolType() implements IType{
    @Override
    public IValue defaultValue(){ return new BoolValue(false); }

    @Override
    public String toString(){ return "boolean";}

    @Override
    public boolean equals(Object another){
        return another instanceof BoolType;
    }
}
