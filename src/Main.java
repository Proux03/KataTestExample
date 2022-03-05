import java.util.*;

class Main {
    static Scanner scanner = new Scanner(System.in);
    static int num1;
    static int num2;
    static char oper;
    static int result;

    public static void main(String[] args) {
        System.out.println("Введите выражение: ");
//      Считываем строку userInput которую ввёл пользователь
        String userInput = scanner.nextLine();
        userInput = userInput.replaceAll(" ", "");
//      Создаём пустой символьный массив длиной 10 символов:  under_char
        char[] under_char = new char[userInput.length()];
//      Заполняем символьный массив символами строки которую ввел пользователь и по ходу ловим знак операции
        for (int i = 0; i < userInput.length(); i++) {
            under_char[i] = userInput.charAt(i);
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
        //String under_charString = String.valueOf(under_char);
        String[] numbers = userInput.split("[+-/*]");
        //String stable00 = blacks[0];
        //String stable01 = blacks[1];
        //String string03 = stable01.trim();
        num1 = romanToInt(numbers[0]);
        num2 = romanToInt(numbers[1]);
        if (num1 < 0 || num2 < 0) {
            result = 0;
        } else {
            result = calc(num1, num2, oper);
            String resultRoman = IntToRoman(result);
            System.out.println(resultRoman);
            System.exit(1);
        }

        try {
            num1 = Integer.parseInt(numbers[0]);
            num2 = Integer.parseInt(numbers[1]);
            if (num1 < 11 && num2 < 11) {
                result = calc(num1, num2, oper);
                System.out.println(result);
            } else System.out.println("Некорректный ввод");

        } catch (NumberFormatException n) {
            System.out.println("Некорректный ввод");
            System.exit(1);
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
        String s = roman[numArab];
        return s;
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

    public static int calc(int num1, int num2, char op) {
        int result = 0;
        switch (op) {
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
                    //System.out.println("Исключение : " + e);
                    System.out.println("На ноль делить нельзя!");
                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;
    }
}