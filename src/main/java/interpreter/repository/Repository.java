package interpreter.repository;

import interpreter.model.PrgState;
import java.util.ArrayList;

public class Repository implements IRepository{

    private final ArrayList<PrgState> programStates = new ArrayList<>();

    public Repository(PrgState state){
        addPrg(state);
    }
    @Override
    public PrgState getCrtPrg() {
        return programStates.getFirst();
    }

    @Override
    public void addPrg(PrgState prg){
        programStates.add(prg);
    }

}
