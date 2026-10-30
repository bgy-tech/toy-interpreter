package interpreter.model.exp;

public enum RelOp {
    GREATER_THEN(">"), LESS_THEN("<"), GREATER_OR_EQUAL(">="), LESS_OR_EQUAL("<="), EQUAL("=="), NOT_EQUAL("!=");
    private final String symbol;
    RelOp(String symbol){ this.symbol = symbol; };
    @Override public String toString(){ return symbol; };
}
