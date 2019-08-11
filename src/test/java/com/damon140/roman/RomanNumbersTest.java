package com.damon140.roman;

import java.util.Arrays;
import java.util.List;

import com.damon140.roman.RomanNumbers.Numeral;

import org.testng.annotations.*;
import static org.testng.Assert.*;

public class RomanNumbersTest {

    @Test
    public void toRomanNumeral() {
        assertEquals(RomanNumbers.Numeral.toRomanNumeral("I").get(), Numeral.I);
        assertEquals(RomanNumbers.Numeral.toRomanNumeral("V").get(), Numeral.V);
        assertEquals(RomanNumbers.Numeral.toRomanNumeral("X").get(), Numeral.X);
        assertEquals(RomanNumbers.Numeral.toRomanNumeral("L").get(), Numeral.L);
        assertEquals(RomanNumbers.Numeral.toRomanNumeral("C").get(), Numeral.C);
        assertEquals(RomanNumbers.Numeral.toRomanNumeral("D").get(), Numeral.D);
        assertEquals(RomanNumbers.Numeral.toRomanNumeral("M").get(), Numeral.M);
    }

    @Test
    public void toRomanNumerals() {
        assertEquals(RomanNumbers.Numeral.toRomanNumerals("IVXLCDM"), Arrays.asList(
            Numeral.I, Numeral.V, Numeral.X, Numeral.L, Numeral.C, Numeral.D, Numeral.M
        ));
    }

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
    public void simpleAdditive1() {
        List all = Arrays.asList(
            Numeral.M, Numeral.D, Numeral.C, Numeral.L, Numeral.X, Numeral.V, Numeral.I);
        assertEquals(RomanNumbers.intFromList(all), 1666);
    }

    @Test
    public void simpleAdditive2() {
        assertEquals(RomanNumbers.intFromString("MDCLXVI"), 1666);
    }

    @Test
    public void clockFaceNumbers() {
        assertEquals(RomanNumbers.intFromString("I"), 1);
        assertEquals(RomanNumbers.intFromString("II"), 2);
        assertEquals(RomanNumbers.intFromString("III"), 3);
        assertEquals(RomanNumbers.intFromString("IV"), 4);
        assertEquals(RomanNumbers.intFromString("V"), 5);
        assertEquals(RomanNumbers.intFromString("VI"), 6);
        assertEquals(RomanNumbers.intFromString("VII"), 7);
        assertEquals(RomanNumbers.intFromString("VIII"), 8);
        assertEquals(RomanNumbers.intFromString("IX"), 9);
        assertEquals(RomanNumbers.intFromString("X"), 10);
        assertEquals(RomanNumbers.intFromString("XI"), 11);
        assertEquals(RomanNumbers.intFromString("XII"), 12);
    }

    @Test
    public void simpleSubtractive() {
        assertEquals(RomanNumbers.intFromString("IM"), 999);
        assertEquals(RomanNumbers.intFromString("CM"), 900);
    }

    @Test
    public void commonYears() {
        // Simple examples from Wikipedia

        // 1912 is written MCMXII
        assertEquals(RomanNumbers.intFromString("MCMXII"), 1912);

        // For this century, MM indicates 2000
        assertEquals(RomanNumbers.intFromString("MM"), 2000);

        // Thus the current year is MMXIX (2019).
        assertEquals(RomanNumbers.intFromString("MMXIX"), 2019);
    }

    @Test
    public void unabbreviated1to10() {
        assertEquals(RomanNumbers.intFromString("I"), 1);
        assertEquals(RomanNumbers.intFromString("II"), 2);
        assertEquals(RomanNumbers.intFromString("III"), 3);
        assertEquals(RomanNumbers.intFromString("IIII"), 4);
        assertEquals(RomanNumbers.intFromString("V"), 5);
        assertEquals(RomanNumbers.intFromString("VI"), 6);
        assertEquals(RomanNumbers.intFromString("VII"), 7);
        assertEquals(RomanNumbers.intFromString("VIII"), 8);
        assertEquals(RomanNumbers.intFromString("VIIII"), 9);
        assertEquals(RomanNumbers.intFromString("X"), 10);
    }

    // FIXME: test for busted numbers that don't work
    // IIM is busted??

}
