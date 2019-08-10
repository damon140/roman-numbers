package com.damon140.roman;

import java.util.List;

public class RomanNumbers {

    public static enum Numeral {
        I(1),
        V(5),
        X(10),
        L(50),
        C(100),
        D(500),
        M(1000),
        ;

        private static final Map<char, Numeral> charToNumeral = new HashMap();

        private final int value;

        private Numeral(int value){
            this.value = value;
            charToNumeral.put(this.name.toChar(), this);
        }

        public int getValue() {
            return value;
        }

        public Numeral fromChar(char numeralChar) {
            return Optional.fromNull(charToNumeral.get(numeralChar));
        }

        public List<Numeral> fromString(String numerals) {
            numerals.stream().map(c -> fromChar(c)).collect(toList());
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

    public static int intFromString(String numerals) {
        return intFromList(numerals);
    }

    public static int intFromList(List<Numeral> numerals) {
        Optional<Numeral> prev;
        int value = 0;
        int index = 0;

        for (Numeral curr; index < numerals.size(); index++) {
            
            curr = numerals.get(index);

            if (!prev.isPresent() || prev.smallerThan(curr)) {
                value += curr.getValue();
            } else {
                // scan && sub list

                // FIXME: Damon impl here
                //intFromNum -= 
            }
        }
    }

    public static String stringFromInt(int i) {

    }

    public static List<Numeral> numeralsFromInt(int i) {

    }

}