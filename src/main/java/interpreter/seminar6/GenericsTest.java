package interpreter.seminar6;

import java.util.ArrayList;
public class GenericsTest {

    @Test
    void testGenerics(){
        var list = new ArrayList<Integer>();
        list.add(3);

    }

   //1
    class A { };
    class B extends A {};
//
//    var listB = new ArrayList<B>();
//    ArrayList<A> ListA = listB;
    // - it wont work

   //2
//ArrayList<? extends A> //- any class that is subclass of A
//    ArrayList.add(new A())
    // error: for 'write' op we have to define what type can be added
    //'read' - works: ex : list.getFirst()

   //3
// super B  - superclasses of B(parents of B)

   //4
// ? super B   - any class which is super of B

   //5
// <T extends A> - any class that extends A

   //6
//  <T extends A & B> extends A and B too


}
