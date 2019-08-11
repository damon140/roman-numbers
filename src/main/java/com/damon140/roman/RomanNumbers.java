package com.damon140.roman;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.ArrayList;

public class RomanNumbers {

    private static final Map<String, Numeral> stringToNumeral = new HashMap();

    public static enum Numeral {
        I("I", 1),
        V("V", 5),
        X("X", 10),
        L("L", 50),
        C("C", 100),
        D("D", 500),
        M("M", 1000),

        // TODO: add >I< & other unusual forms
        ;
        
        private final int value;

        private Numeral(String characters, int value){
            this.value = value;
            RomanNumbers.stringToNumeral.put(characters, this);
        }

        public int getValue() {
            return value;
        }

        public boolean smallerThan(Numeral other) {
            return this.value < other.value;
        }

        public boolean biggerThan(Numeral other) {
            return this.value > other.value;
        }

        /**
         * Get a single enum value if the string exactly matches.
         * 
         * @param numeralString
         * @return
         */
        public static Optional<Numeral> toRomanNumeral(String numeralString) {
            return Optional.ofNullable(stringToNumeral.get(numeralString));
        }

        // FIXME: unit test
        public static List<Numeral> toRomanNumerals(String numeralsString) {
            List<Numeral> numerals = new ArrayList(); 

            // TODO: update this naive impl when adding multichar numerals
            for (int index = 0; index < numeralsString.length(); index++) {
                String numeralString = numeralsString.substring(index, index + 1);
                Numeral numeral = stringToNumeral.get(numeralString);
                if (null == numeral) {
                    throw new IllegalArgumentException("Don't recognise numeral [" + numeralString + "].");
                }
                numerals.add(numeral);
            }
            
            return numerals;
        }
    }

    // FIXME: roman number class

    public static int intFromString(String numerals) {
        return intFromList(Numeral.toRomanNumerals(numerals));
    }


    //  I    V
    //  only prev character needed??
    public static int intFromList(List<Numeral> numerals) {
        Optional<Numeral> prev = Optional.empty();
        int sum = 0;
        int index = 0;

        for (Numeral curr; index < numerals.size(); index++) {
            curr = numerals.get(index);
            sum += curr.getValue();

            if (prev.isPresent() && prev.get().smallerThan(curr)) {
                // do we need a backscan sequence detector for unusual varients?
                sum -= 2 * prev.get().value;
            }

            prev = Optional.of(curr);
        }

        return sum;
    }

    // FIXME: impl
    // public static String stringFromInt(int i) {

    // }

    // FIXME: impl
    // public static List<Numeral> numeralsFromInt(int i) {

    // }

}