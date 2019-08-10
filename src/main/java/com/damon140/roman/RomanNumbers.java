package com.damon140.roman;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class RomanNumbers {

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

        private final Map<String, Numeral> stringToNumeral = new HashMap();

        private final int value;

        private Numeral(String characters, int value){
            this.value = value;
            stringToNumeral.put(characters, this);
        }

        public int getValue() {
            return value;
        }

        public boolean smallerThanOrEqual(Numeral other) {
            return this.value <= other.value;
        }

        /**
         * Get a single enum value if the string exactly matches.
         * 
         * @param numeralString
         * @return
         */
        public Optional<Numeral> fromNumberString(String numeralString) {
            return Optional.ofNullable(stringToNumeral.get(numeralString));
        }

        // /**
        //  * Find the index of a descending run of numeral from the start
        //  * index. 
        //  */
        // public int descendingScan(int startIndex) {
        //     start
        //     for (Numeral curr : )
        // }

    }

    // FIXME: roman number class?

    // FIXME: impl here
    // public static int intFromString(String numerals) {
    //     // FIXME: impl parse function for use here
    //     return intFromList(numerals);
    // }

    public static int intFromList(List<Numeral> numerals) {
        Optional<Numeral> prev = Optional.empty();
        int sum = 0;
        int index = 0;

        for (Numeral curr; index < numerals.size(); index++) {
            
            curr = numerals.get(index);

            if (!prev.isPresent() || prev.get().smallerThanOrEqual(curr)) {
                sum += curr.getValue();
            } else {
                // scan && sub list

                // FIXME: Damon impl here
                //intFromNum -= 
            }
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