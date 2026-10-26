package interpreter.view;

import interpreter.controller.Controller;
import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.stmt.IStmt;
import interpreter.model.value.IValue;
import interpreter.repository.IRepository;
import interpreter.repository.Repository;

import java.util.Scanner;

public class View {
    public void menu(){
        Scanner scanner  = new Scanner(System.in);
        while(true){
            System.out.println("\n--- Toy Language Interpreter ---");
            System.out.println("1. Run Example 1: " + Examples.ex1);
            System.out.println("2. Run Example 2: " + Examples.ex2);
            System.out.println("3. Run Example 3: " + Examples.ex3);
            System.out.println("0. Exit");
            System.out.print("Input option: ");

            String option = scanner.nextLine();
            if (option.equals("0")){
                System.out.println("Bye bye");
                break;
            }
            IStmt selectedProgram = switch(option){
                case "1" -> Examples.ex1;
                case "2" -> Examples.ex1;
                case "3" -> Examples.ex1;
                default -> null;
            };

            if (selectedProgram == null) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }
            runProgram(selectedProgram);
        }
    }

    private void runProgram(IStmt selectedProgram){
        try{
            //fresh new program state
            PrgState state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
                    selectedProgram);
            IRepository repo = new Repository(state);
            Controller ctr = new Controller(repo, true);
            ctr.allStep();
        }catch(MyException e){
            System.out.println("Interpreter error: " + e.getMessage());
        }catch(Exception e){
            System.out.println("System error: " + e.getMessage());
        }
    }
}
