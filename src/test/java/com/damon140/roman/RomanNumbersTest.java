package com.damon140.roman;

import java.util.Arrays;
import java.util.List;

import com.damon140.roman.RomanNumbers.Numeral;

import org.testng.annotations.*;

import static org.testng.Assert.*;

public class RomanNumbersTest {

    @Test
    public void literals() {
        assertEquals(RomanNumbers.intFromList(Arrays.asList(Numeral.I)), 1);
        assertEquals(RomanNumbers.intFromList(Arrays.asList(Numeral.V)), 5);
        assertEquals(RomanNumbers.intFromList(Arrays.asList(Numeral.X)), 10);
        assertEquals(RomanNumbers.intFromList(Arrays.asList(Numeral.L)), 50);
        assertEquals(RomanNumbers.intFromList(Arrays.asList(Numeral.C)), 100);
        assertEquals(RomanNumbers.intFromList(Arrays.asList(Numeral.D)), 500);
        assertEquals(RomanNumbers.intFromList(Arrays.asList(Numeral.M)), 1000);
    }
    
    @Test
    public void simpleAdditive() {
        List all = Arrays.asList(Numeral.I, Numeral.V, Numeral.X, Numeral.L,
            Numeral.C, Numeral.D, Numeral.M);
        assertEquals(RomanNumbers.intFromList(all), 1666);
    }

    // FIXME: test simple subtractive

    // FIXME: test complex

    // TODO: test weird additive forms like IIII = 4

}
