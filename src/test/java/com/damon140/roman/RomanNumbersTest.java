package com.damon140.roman;

import java.util.Arrays;
import java.util.List;

import com.damon140.roman.RomanNumbers.Numeral;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import java.util.Optional;

public class RomanNumbersTest {

    // TODO: switch whole class to Hamcrest https://openwritings.net/pg/java/using-hamcrest-testng
    
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
        assertEquals(RomanNumbers.intFromList(Arrays.asList(Numeral.I)), Optional.of(1));
        assertEquals(RomanNumbers.intFromList(Arrays.asList(Numeral.V)), Optional.of(5));
        assertEquals(RomanNumbers.intFromList(Arrays.asList(Numeral.X)), Optional.of(10));
        assertEquals(RomanNumbers.intFromList(Arrays.asList(Numeral.L)), Optional.of(50));
        assertEquals(RomanNumbers.intFromList(Arrays.asList(Numeral.C)), Optional.of(100));
        assertEquals(RomanNumbers.intFromList(Arrays.asList(Numeral.D)), Optional.of(500));
        assertEquals(RomanNumbers.intFromList(Arrays.asList(Numeral.M)), Optional.of(1000));
    }
    
    @Test
    public void simpleAdditive1() {
        List all = Arrays.asList(
            Numeral.M, Numeral.D, Numeral.C, Numeral.L, Numeral.X, Numeral.V, Numeral.I);
        assertEquals(RomanNumbers.intFromList(all), Optional.of(1666));
    }

    @Test
    public void simpleAdditive2() {
        assertEquals(RomanNumbers.intFromString("MDCLXVI"), Optional.of(1666));
    }

    @Test
    public void clockFaceNumbers() {
        assertEquals(RomanNumbers.intFromString("I"), Optional.of(1));
        assertEquals(RomanNumbers.intFromString("II"), Optional.of(2));
        assertEquals(RomanNumbers.intFromString("III"), Optional.of(3));
        assertEquals(RomanNumbers.intFromString("IV"), Optional.of(4));
        assertEquals(RomanNumbers.intFromString("V"), Optional.of(5));
        assertEquals(RomanNumbers.intFromString("VI"), Optional.of(6));
        assertEquals(RomanNumbers.intFromString("VII"), Optional.of(7));
        assertEquals(RomanNumbers.intFromString("VIII"), Optional.of(8));
        assertEquals(RomanNumbers.intFromString("IX"), Optional.of(9));
        assertEquals(RomanNumbers.intFromString("X"), Optional.of(10));
        assertEquals(RomanNumbers.intFromString("XI"), Optional.of(11));
        assertEquals(RomanNumbers.intFromString("XII"), Optional.of(12));
    }

    @Test
    public void simpleSubtractive() {
        assertEquals(RomanNumbers.intFromString("IM"), Optional.of(999));
        assertEquals(RomanNumbers.intFromString("CM"), Optional.of(900));
    }

    @Test
    public void commonYears() {
        // Simple examples from Wikipedia

        // 1912 is written MCMXII
        assertEquals(RomanNumbers.intFromString("MCMXII"), Optional.of(1912));

        // For this century, MM indicates 2000
        assertEquals(RomanNumbers.intFromString("MM"), Optional.of(2000));

        // Thus the current year is MMXIX (2019).
        assertEquals(RomanNumbers.intFromString("MMXIX"), Optional.of(2019));
    }

    @Test
    public void unabbreviated1to10() {
        assertEquals(RomanNumbers.intFromString("I"), Optional.of(1));
        assertEquals(RomanNumbers.intFromString("II"), Optional.of(2));
        assertEquals(RomanNumbers.intFromString("III"), Optional.of(3));
        assertEquals(RomanNumbers.intFromString("IIII"), Optional.of(4));
        assertEquals(RomanNumbers.intFromString("V"), Optional.of(5));
        assertEquals(RomanNumbers.intFromString("VI"), Optional.of(6));
        assertEquals(RomanNumbers.intFromString("VII"), Optional.of(7));
        assertEquals(RomanNumbers.intFromString("VIII"), Optional.of(8));
        assertEquals(RomanNumbers.intFromString("VIIII"), Optional.of(9));
        assertEquals(RomanNumbers.intFromString("X"), Optional.of(10));
    }

    @Test
    public void oldForms() {
        // old forms of C
        assertEquals(RomanNumbers.intFromString(">I<"), Optional.of(100));
        assertEquals(RomanNumbers.intFromString("ƆIC"), Optional.of(100));
        assertEquals(RomanNumbers.intFromString("Ɔ"), Optional.of(100));

        // old forms of D
        // TODO: this one fails, some sort of encoding problem??
        //assertEquals(RomanNumbers.intFromString("I?"), 500);
        
        // old forms of M
        assertEquals(RomanNumbers.intFromString("?"), Optional.of(1000));
        assertEquals(RomanNumbers.intFromString("?"), Optional.of(1000));
        assertEquals(RomanNumbers.intFromString("?"), Optional.of(1000));
        assertEquals(RomanNumbers.intFromString("?"), Optional.of(1000));
        assertEquals(RomanNumbers.intFromString("?"), Optional.of(1000));
        assertEquals(RomanNumbers.intFromString("?"), Optional.of(1000));
        assertEquals(RomanNumbers.intFromString("?"), Optional.of(1000));
        
        // lowercase varients of regular forms
        assertEquals(RomanNumbers.intFromString("mdclxvi"), Optional.of(1666));
    }

    @Test
    public void badCharacters() {
        assertEquals(RomanNumbers.intFromString("ih"), Optional.empty());
    }
    
    @Test
    public void stringFromInt_primitives() {
        assertEquals(RomanNumbers.stringFromInt(1000), Optional.of("M"));
        assertEquals(RomanNumbers.stringFromInt(500), Optional.of("D"));
        assertEquals(RomanNumbers.stringFromInt(100), Optional.of("C"));
        assertEquals(RomanNumbers.stringFromInt(50), Optional.of("L"));
        assertEquals(RomanNumbers.stringFromInt(10), Optional.of("X"));
        assertEquals(RomanNumbers.stringFromInt(5), Optional.of("V"));
        assertEquals(RomanNumbers.stringFromInt(1), Optional.of("I"));
    }

    @Test
    public void stringFromInt_simpleCompound() {
        assertEquals(RomanNumbers.stringFromInt(15), Optional.of("XV"));
        assertEquals(RomanNumbers.stringFromInt(1666), Optional.of("MDCLXVI"));
    }
    
    @Test
    public void littleBuddy() {
        assertEquals(Numeral.I.littleBuddy(), Optional.empty());
        assertEquals(Numeral.V.littleBuddy(), Optional.of(Numeral.I));
        assertEquals(Numeral.X.littleBuddy(), Optional.of(Numeral.I));
        assertEquals(Numeral.C.littleBuddy(), Optional.of(Numeral.X));
        assertEquals(Numeral.L.littleBuddy(), Optional.of(Numeral.X));
        assertEquals(Numeral.M.littleBuddy(), Optional.of(Numeral.C));
        assertEquals(Numeral.D.littleBuddy(), Optional.of(Numeral.C));
    }
    
    /**
     * Test the corner cases of 9s and 4s
     */
    @Test
    public void stringFromInt_9and4cornercases() {
        assertEquals(RomanNumbers.stringFromInt(4), Optional.of("IV"));
        assertEquals(RomanNumbers.stringFromInt(9), Optional.of("IX"));
        
        assertEquals(RomanNumbers.stringFromInt(3999), Optional.of("MMMCMXCIX"));
        assertEquals(RomanNumbers.stringFromInt(3994), Optional.of("MMMCMXCIV"));
        assertEquals(RomanNumbers.stringFromInt(3949), Optional.of("MMMCMXLIX"));
        assertEquals(RomanNumbers.stringFromInt(3944), Optional.of("MMMCMXLIV"));
        assertEquals(RomanNumbers.stringFromInt(3499), Optional.of("MMMCDXCIX"));
        assertEquals(RomanNumbers.stringFromInt(3494), Optional.of("MMMCDXCIV"));
        assertEquals(RomanNumbers.stringFromInt(3444), Optional.of("MMMCDXLIV"));
        assertEquals(RomanNumbers.stringFromInt(3449), Optional.of("MMMCDXLIX"));
    }
    
    @Test
    public void stringFromInt_commonyears() {
        assertEquals(RomanNumbers.stringFromInt(1776), Optional.of("MDCCLXXVI"));
        assertEquals(RomanNumbers.stringFromInt(1954), Optional.of("MCMLIV"));
        assertEquals(RomanNumbers.stringFromInt(2014), Optional.of("MMXIV"));
        assertEquals(RomanNumbers.stringFromInt(2019), Optional.of("MMXIX"));
    }
    
    // TODO: test for busted numbers that don't work
    // IM is busted
    // iiiii is busted
    
}
