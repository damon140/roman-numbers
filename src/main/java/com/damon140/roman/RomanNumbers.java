package com.damon140.roman;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RomanNumbers {
    
    public static enum Numeral {
        i("i", 1),
        I("I", 1),
        v("v", 5),
        V("V", 5),
        x("x", 10),
        X("X", 10),
        l("l", 50),
        L("L", 50),
        /** Old form of C */
        _IC(">I<", 100),
        /** Old form of C */
        _CIC("ƆIC", 100),
        /** Old form of C */
        _Cback("Ɔ", 100),
        c("c", 100),
        C("C", 100),
        _Dold("I?", 500),
        d("d", 500),
        D("D", 500),
        _M1("?", 1000),
        _M2("?", 1000),
        _M3("?", 1000),
        _M4("?", 1000),
        _M5("?", 1000),
        _M6("?", 1000),
        _M7("?", 1000),
        m("m", 1000),
        M("M", 1000),
        ;
        
        // TODO: this source show more numerals, maybe modern extension? https://www.rapidtables.com/convert/number/roman-numerals-converter.html?x1=&x2=
        
        // TODO: rename to proper const
        private static final Map<String, Numeral> stringToNumeral = new HashMap();

        /**
         * A map of numerals and the "little buddy" numerals that get used
         * when writing a number to make the 4's and 9's.
         */ 
        private static final Map<Numeral, Optional<Numeral>> LITTLE_BUDDIES = new HashMap();
        
        static {
            List<Numeral> reverseNumerals = Arrays.asList(Numeral.values());
            Collections.reverse(reverseNumerals);
            
            Arrays.stream(Numeral.values()).forEach(n1 -> {
                stringToNumeral.put(n1.characters, n1);
                LITTLE_BUDDIES.put(n1, 
                    reverseNumerals.stream()
                        .filter(n -> 5 * n.getValue() == n1.value || 10 * n.getValue() == n1.value)
                        .findFirst()
                );
            });
        }
        
        private final int value;
        private final String characters;

        // TODO: flip constructor to allow list of multiple string representations
        private Numeral(String characters, int value){
            this.value = value;
            this.characters = characters;
        }

        public int getValue() {
            return value;
        }
        
        public String getCharacters() {
            return characters;
        }

        public boolean smallerThan(Numeral other) {
            return this.value < other.value;
        }

        public boolean smallerThanOrEqual(int i) {
            return this.value <= i;
        }
                
        public boolean biggerThan(Numeral other) {
            return this.value > other.value;
        }

        public int minus(int i) {
            return i - this.getValue();
        }
        
        public boolean smallerThanButClose(int i) {
            Optional<Numeral> littleBuddy = littleBuddy();
            return littleBuddy.isPresent()
                    && this.value - littleBuddy.get().getValue() <= i && i < this.value;
        }

        public Optional<Numeral> predecessor() {
            for (int looper = this.ordinal(); looper >= 0; looper--) {
                final Numeral candidate = Numeral.values()[looper];
                if (candidate.value < this.value) {
                    return Optional.of(candidate);
                }
            }
            
            return Optional.empty();
        }
        
        public Optional<Numeral> littleBuddy() {
            return LITTLE_BUDDIES.get(this);
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

        public static List<Numeral> toRomanNumerals(String numeralsString) {
            List<Numeral> numerals = new ArrayList(); 

            for (int index = 0; index < numeralsString.length(); index++) {
                Optional<Numeral> numeral = toRomanNumeral(numeralsString.substring(index, index + 1));
                if (!numeral.isPresent()) {
                    // TODO: push this into block the numerals
                    // fall back to subscanning the tail
                    String tail = numeralsString.substring(index);
                    numeral = Arrays.stream(Numeral.values())
                        .filter(n -> tail.startsWith(n.getCharacters()))
                        .findAny();
                    
                    if (numeral.isPresent()) {
                        index += numeral.get().getCharacters().length() - 1;
                    }
                }

                if (!numeral.isPresent()) {
                    // TODO: switch to specific exception type
                    throw new IllegalArgumentException("Don't recognise numeral at head of substring [" + numeralsString + "].");
                }
                numerals.add(numeral.get());
            }
            
            return numerals;
        }

    }

    // TODO: roman number class??

    public static Optional<Integer> intFromString(String numerals) {
        try {
            return intFromList(Numeral.toRomanNumerals(numerals));
        } catch(IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public static Optional<Integer> intFromList(List<Numeral> numerals) {
        Optional<Numeral> prev = Optional.empty();
        int sum = 0;
        int index = 0;

        for (Numeral curr; index < numerals.size(); index++) {
            curr = numerals.get(index);
            sum += curr.getValue();

            if (prev.isPresent() && prev.get().smallerThan(curr)) {
                //  only prev character needed for basic case
                // do we need a backscan sequence detector for unusual varients?
                sum -= 2 * prev.get().value;
            }

            prev = Optional.of(curr);
        }

        return Optional.of(sum);
    }

    public static Optional<String> stringFromInt(int i) {
        // FIXME: add max upper bound check

        List<Numeral> values = Arrays.asList(Numeral.values());
        Collections.reverse(values);

        Optional<Numeral> special = values.stream()
                .filter(v -> v.smallerThanButClose(i))
                .findFirst();
        
        Optional<Numeral> numeral = values.stream()
                .filter(v -> v.smallerThanOrEqual(i))
                .findFirst();
        
        if (special.isEmpty() && numeral.isEmpty()) {
            return Optional.empty();
        } else {
            String s;
            final int remainder;
                    
            if (special.isPresent()) {
                Numeral littleBuddy = special.get().littleBuddy().get();
                s = littleBuddy.getCharacters() + special.get().getCharacters();
                remainder = i - (special.get().getValue() - littleBuddy.getValue());
            } else {
                s = numeral.get().getCharacters();
                remainder = numeral.get().minus(i);                
            }
            
            if (0 < remainder) {
                Optional<String> remainderString = stringFromInt(remainder);
                s += remainderString.get();
            }
            
            return Optional.of(s);
        }
    }

    // FIXME: impl
    // public static Optional<List<Numeral>> numeralsFromInt(int i) {

    // }

}