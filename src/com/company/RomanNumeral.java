package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum RomanNumeral {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100);

    private final int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<RomanNumeral> getList() {
        List<RomanNumeral> romanNumeralList;
        romanNumeralList = Arrays.asList(values());
        Collections.reverse(romanNumeralList);
        return romanNumeralList;
    }
}