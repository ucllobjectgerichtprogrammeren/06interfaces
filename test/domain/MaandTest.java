package domain;

import org.junit.Test;
import utils.Maand;

import static org.junit.Assert.*;

public class MaandTest {
    Maand november = new Maand(11);
    Maand december = new Maand(12);
    Maand december2 = new Maand(12);

    @Test
    public void test_Maand_equals() {
        assertFalse(december.equals("abc"));
        assertFalse(december.equals(november));
        assertTrue(december.equals(december2));
    }

    @Test
    public void test_compareTo() {
        assertTrue(november.compareTo(december) < 0);
        assertTrue(december.compareTo(november) > 0);
        assertTrue(december.compareTo(december2) == 0);
    }

}