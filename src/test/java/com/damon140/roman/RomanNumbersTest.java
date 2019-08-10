package com.damon140.roman;

import java.util.Arrays;

import com.damon140.roman.RomanNumbers.Numeral;

import org.testng.annotations.*;
import static org.testng.Assert.*;

public class RomanNumbersTest {

    @Test
    public void literals() {
        assertEquals(RomanNumbers.intFromList(Arrays.asList(Numeral.I)), 1);
    }

    // FIXME: test simple additive

    // FIXME: test simple subtractive

    // FIXME: test complex

    // TODO: test weird additive forms like IIII = 4

}
