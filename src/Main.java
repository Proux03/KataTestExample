import java.util.*;

//решение должно содержать данный класс
class Main {

    public static String[] calc(String inputString) {
        String[] calc_inputString = inputString.split(" ");
        if (calc_inputString.length != 3) {
            Scanner inputString_a_value_again = new Scanner(System.in);
            System.out.println("Неверный формат ввода данных. Введите выражение, разделяя каждый символ _пробелом_");
            inputString = inputString_a_value_again.nextLine();
            return calc(inputString);
        } else {
            return calc_inputString;
        }
    }

    private static boolean its_an_arabic_numbers = true;

    public static void main(String[] args) {
        Scanner inputString_a_value = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String inputString = inputString_a_value.nextLine();
        while (!inputString.isEmpty()) {
            String[] calc_inputString;
            calc_inputString = calc(inputString);
            String operation = calc_inputString[1];
            Number values;
            int value1 = 0;
            int value2 = 0;
            // Переводим в int. Если введены римские, выкинет исключение
            try {
                value1 = Integer.parseInt(calc_inputString[0]);
                value2 = Integer.parseInt(calc_inputString[2]);
                //values = new Arabic(value1, value2, 0);
            } catch (NumberFormatException e) {
                its_an_arabic_numbers = false;
                System.out.println("Введены римские цифры");
                //values = new Romes(parsed_input[0], parsed_input[2], 0);
            }

            if (its_an_arabic_numbers) {
                values = new Arabic(value1, value2);
            } else {
                values = new Romes(calc_inputString[0], calc_inputString[2]);
            }
            if (operation.equals("+")) {
                values.sum();
            } else if (operation.equals("-")) {
                values.sub();
            } else if (operation.equals("/") || operation.equals(":")) {
                values.div();
            } else if (operation.equals("*") || operation.equals("x")) {
                values.mul();
            }

            if (its_an_arabic_numbers) {
                System.out.println("Ответ: " + values.getResult());
            } else {
                System.out.println("Ответ: " + values.getStringResult());
            }
            System.out.println();
            System.out.print("Введите следующее выражение: ");
            inputString = inputString_a_value.nextLine();
        }
        System.out.println("Вы вышли из калькулятора");

    }
}

abstract class Number {

    public abstract void sum();

    public abstract void sub();

    public abstract void div();

    public abstract void mul();

    public abstract int getResult();
    public abstract String getStringResult();
}

class Romes extends Number {
    private String romes_value1;
    private String romes_value2;
    private int romes_value1_int;
    private int romes_value2_int;
    private int result_int;
    private String sign = "";
    private String result_string;
    private final String[] roman_letters_9 = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    Romes(String value1, String value2) {
        this.romes_value1 = value1;
        this.romes_value2 = value2;
        this.romes_value1_int = convert_to_int(romes_value1);
        this.romes_value2_int = convert_to_int(romes_value2);
    }
    private String convert_result_to_Romes(int n, int ostatok) {
        ostatok = n % 10;
        if (ostatok != 0) {
            try {
                return convert_result_to_Romes(n - ostatok, 0) + roman_letters_9[ostatok - 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                sign = "-";
                return convert_result_to_Romes(n - ostatok, 0) + roman_letters_9[(ostatok + 1) * -1];
            }
        }
        //Возможность вывести отрицательное римское число
        if (n > 0) {
            n = n - 10;
            return convert_result_to_Romes(n,0) + "X";
        } else if (n < 0) {
            n = n + 10;
            return convert_result_to_Romes(n,0) + "X";
        }   else {
            return sign;
        }
    }

    @Override
    public void sum() {
        result_int = romes_value1_int + romes_value2_int;
        result_string = convert_result_to_Romes(result_int, result_int);
    }

    @Override
    public void sub() {
        result_int = romes_value1_int - romes_value2_int;
        result_string = convert_result_to_Romes(result_int, result_int);
    }

    @Override
    public void div() {
        try {
            result_int = romes_value1_int / romes_value2_int;
            result_string = convert_result_to_Romes(result_int, result_int);
        } catch (ArithmeticException e) {
            System.out.print("Проверьте правильность ввода римских цифр. Вероятно введены и арабские и римские одновременно. ");
            return;
        }

    }

    @Override
    public void mul() {
        result_int = romes_value1_int * romes_value2_int;
        result_string = convert_result_to_Romes(result_int, result_int);
    }

    @Override
    public int getResult() {
        return result_int;
    }
    public String getStringResult() {
        return result_string;
    }

    private int convert_to_int(String romes_value){
        char[] value_char = romes_value.toCharArray();
        int[] values_int = new int[value_char.length];
        for (int i = 0; i < value_char.length; i++) {
            switch (value_char[i]) {
                case 'I':
                    values_int[i] = 1;
                    break;
                case 'V':
                    values_int[i] = 5;
                    break;
                case 'X':
                    values_int[i] = 10;
                    break;

                default:
                    System.out.println("Содержится неверный символ. Проверьте правильность ввода римских цифр:" + "\n" +
                            "I = 1" + "\n" +
                            "V = 5" + "\n" +
                            "X = 10");
                    break;
            }
        }
        int result = values_int[0];
        for (int i = 0; i < values_int.length && values_int.length > i + 1; i++) {
            if (values_int[i] >= values_int[i+1]) {
                result += values_int[i+1];
            } else if (values_int[i] < values_int[i+1]) {
                result = result + values_int[i+1] - 2;
            }
        }
        return result;
    }

    public String getRomes_value1() {
        return romes_value1;
    }

    public String getRomes_value2() {
        return romes_value2;
    }

    public void setRomes_value1(String romes_value1) {
        this.romes_value1 = romes_value1;
    }

    public void setRomes_value2(String romes_value2) {
        this.romes_value2 = romes_value2;
    }

    public int getRomes_value1_int() {
        return romes_value1_int;
    }

    public int getRomes_value2_int() {
        return romes_value2_int;
    }

    public void setRomes_value1_int(int romes_value1_int) {
        this.romes_value1_int = romes_value1_int;
    }

    public void setRomes_value2_int(int romes_value2_int) {
        this.romes_value2_int = romes_value2_int;
    }
}

class Arabic extends Number {

    private int value1;
    private int value2;
    private int result;

    Arabic(int value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public void sum() {
        this.result = value1 + value2;
    }

    public void sub() {
        this.result = value1 - value2;
    }

    public void div() {
        try{
            this.result = value1 / value2;
        } catch (ArithmeticException e) {
            System.out.print("Деление на 0! ");
            return;
        }

    }

    public void mul() {
        this.result = value1 * value2;
    }

    @Override
    public int getResult() {
        return result;
    }

    @Override
    public String getStringResult() {
        return "" + result;
    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }
}