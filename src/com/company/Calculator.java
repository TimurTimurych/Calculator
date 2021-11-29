package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Calculator {
    private static String input;
    private static int a, b;

    public static void calculatorStart() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            romeOrArab();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void romeOrArab() throws Exception {
        String[] strings = input.split(" ");
        char operation;

        if (strings.length > 3)
            throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        try {
            operation = strings[1].charAt(0);
        } catch (Exception e) {
            throw new Exception("т.к. строка не является математической операцией");
        }

        try {
            a = Integer.parseInt(strings[0]);
            b = Integer.parseInt(strings[2]);
            System.out.println(calculatorArab(a, b, operation));
        } catch (NumberFormatException e) {
            if (a == 0 && b == 0) {
                a = convertToArab(strings[0]);
                b = convertToArab(strings[2]);
                System.out.println(convertToRome(calculatorArab(a, b, operation)));
            } else throw new Exception("т.к. используются одновременно разные системы счисления");
        }
    }

    private static int convertToArab(String a) throws Exception {

        String romanNumeral = a.toUpperCase();

        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getList();
        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new Exception("т.к. используются одновременно разные системы счисления");
        }
        return result;
    }

    private static String convertToRome(int romeNomber) throws Exception {
        StringBuilder result = new StringBuilder();
        if (romeNomber < 0) throw new Exception("т.к. в римской системе нет отрицательных чисел");
        while (romeNomber > 0) {
            if (romeNomber - 100 >= 0) {
                result.append("C");
                romeNomber -= 100;
            } else if (romeNomber - 90 >= 0) {
                result.append("XC");
                romeNomber -= 90;
            } else if (romeNomber - 50 >= 0) {
                result.append("L");
                romeNomber -= 50;
            } else if (romeNomber - 40 >= 0) {
                result.append("XL");
                romeNomber -= 40;
            } else if (romeNomber - 10 >= 0) {
                result.append("X");
                romeNomber -= 10;
            } else if (romeNomber - 9 >= 0) {
                result.append("IX");
                romeNomber -= 9;
            } else if (romeNomber - 5 >= 0) {
                result.append("V");
                romeNomber -= 5;
            } else if (romeNomber - 4 >= 0) {
                result.append("IV");
                romeNomber -= 4;
            } else {
                result.append("I");
                romeNomber -= 1;
            }
        }
        return result.toString();
    }

    private static int calculatorArab(int a, int b, Character operation) {
        if (operation == '+') return a + b;
        else if (operation == '-') return a - b;
        else if (operation == '*') return a * b;
        else if (operation == '/') return a / b;
        else return 0;
    }
}
