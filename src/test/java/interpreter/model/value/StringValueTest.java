package interpreter.model.value;

import interpreter.model.exp.IExp;
import interpreter.model.type.StringType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StringValueTest {
    @Test
    void sameStringsEqualTest(){
        StringValue s1 = new StringValue("haha");
        StringValue s2 = new StringValue("haha");

        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    void differentStringsEqualTest(){
        StringValue s1 = new StringValue("haha");
        StringValue s2 = new StringValue("hihi");

        assertNotEquals(s1, s2);
        assertNotEquals(s1.hashCode(), s2.hashCode());
    }
}
