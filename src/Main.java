import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Введите выражение: ");
        String userInput = scanner.nextLine();
        //System.out.println(calc(userInput));
    }

    public static String calc(String input) {
        int num1 = 0;
        int num2 = 0;
        char oper = 0;
        int result = 0;
        input = input.replaceAll(" ", "");

        char[] under_char = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            under_char[i] = input.charAt(i);
            if (under_char[i] == '+') {
                oper = '+';
            }
            if (under_char[i] == '-') {
                oper = '-';
            }
            if (under_char[i] == '*') {
                oper = '*';
            }
            if (under_char[i] == '/') {
                oper = '/';
            }
        }

        String[] roman = {"X", "IX", "VIII", "VII", "VI", "V", "IV", "III", "II", "I"};

        boolean flag = false;
        try {
            String[] numbers = input.split("[+-/*]");
            if (numbers.length > 2) throw new IllegalArgumentException("Неверный формат данных");

            for (int i = 0; i < roman.length; i++) {
                if (roman[i].equals(numbers[0]) || roman[i].equals(numbers[1])) {
                    flag = true;
                }
            }
            if (flag) {
                num1 = romanToInt(numbers[0]);
                num2 = romanToInt(numbers[1]);
            } else {
                num1 = Integer.parseInt(numbers[0]);
                num2 = Integer.parseInt(numbers[1]);
            }
            if ((num1 > 10 || num1 <= 0) || (num2 > 10 || num2 <= 0)) {
                throw new IllegalArgumentException("Неверный формат данных");
            }
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Неверный формат данных");
        }
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                try {
                    result = num1 / num2;
                } catch (ArithmeticException | InputMismatchException e) {
                    throw new ArithmeticException("Неверный формат данных");
                }
        }
        if (flag) {
            return IntToRoman(result);
        }
        else {
            return Integer.toString(result);
        }
    }

    private static String IntToRoman(int numArab) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        try {
            String s = roman[numArab];
            return s;
        } catch (ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException("Неверный формат данных");
        }
    }

    private static int romanToInt(String roman) {
        try {
            if (roman.equals("I")) {
                return 1;
            } else if (roman.equals("II")) {
                return 2;
            } else if (roman.equals("III")) {
                return 3;
            } else if (roman.equals("IV")) {
                return 4;
            } else if (roman.equals("V")) {
                return 5;
            } else if (roman.equals("VI")) {
                return 6;
            } else if (roman.equals("VII")) {
                return 7;
            } else if (roman.equals("VIII")) {
                return 8;
            } else if (roman.equals("IX")) {
                return 9;
            } else if (roman.equals("X")) {
                return 10;
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Неверный формат данных");
        }
        return -1;
    }
}