import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static String number;
    static int var = 0;
    static String result;
    static String[] str;
    static int sign;
    static char[] letters;
    static Map<Character, Integer> map = new HashMap<>();
    static Map<String, String> map1 = new HashMap<>();
    static Map<String, String> map2 = new HashMap<>();
    static Map<String, String> map3 = new HashMap<>();
    static Map<String, String> map4 = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        number = reader.readLine().toUpperCase();
        letters = number.toCharArray();
        System.out.println(calc(number));


    }

    public static String calc(String input) {
        checkNumberOfOperators();
        findOfNumbers();
        checkNumbersLessThanTwo();
        checkOperatorIfLessThanOne();
        solution();
        return result;
    }


    static void solution() {
        int num1 = checkRomanNumbers(str[0]);
        int num2 = checkRomanNumbers(str[1]);

        if (num1 > 0 && num2 > 0) {
            str[0] = convertToArabic(str[0].trim());
            str[1] = convertToArabic(str[1].trim());
            checkAllNumbersMoreThanTenAndLessThanOne();
            resultOperation();
            if (var < 1) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
                    System.exit(0);
                }
            }

            result = convertFromArabicToRoman(var + "");

        } else if (num1 == 0 && num2 == 0) {
            checkAllNumbersMoreThanTenAndLessThanOne();
            resultOperation();
            result = var + "";
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
                System.exit(0);
            }
        }
    }

    static void checkOperatorIfLessThanOne() {
        if (sign == 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("throws Exception //т.к. строка не является математической операцией");
                System.exit(0);
            }
        }
    }

    static void checkNumbersLessThanTwo() {
        if (str.length < 2) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("throws Exception //т.к. в вырожении меньше двух чисел");
                System.exit(0);
            }
        }
    }

    static int findOfNumbers() {
        if (number.contains("+")) {
            str = number.trim().split("[+]");
            sign = 1;

        } else if (number.contains("-")) {
            str = number.trim().split("[-]");
            sign = 2;
        } else if (number.contains("*")) {
            str = number.trim().split("[*]");
            sign = 3;
        } else if (number.contains("/")) {
            str = number.trim().split("[/]");
            sign = 4;
        } else sign = 0;
        return sign;
    }


    static int checkNumberOfOperators() {
        int count = 0;

        for (char elem : letters) {
            if (elem == '+' || elem == '-' || elem == '*' || elem == '/') {
                count++;
            }
        }
        if (count > 1) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                System.exit(0);
            }
        }
        return count;
    }

    static int checkRomanNumbers(String str) {

        int checkResult = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char c = entry.getKey();
            if (str.contains(String.valueOf(c))) {
                checkResult++;
            }
        }
        return checkResult;
    }

    static String convertToArabic(String number) {

        int result = map.get(number.charAt(number.length() - 1));
        for (int i = number.length() - 1; i > 0; i--) {
            int a = map.get(number.charAt(i));
            int b = map.get(number.charAt(i - 1));
            if (a > b) {
                result -= b;
            } else {
                result += b;
            }
        }
        return result + "";
    }

    static void checkAllNumbersMoreThanTenAndLessThanOne() {
        if (Integer.parseInt(str[0].trim()) > 10 || Integer.parseInt(str[1].trim()) > 10) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("throws Exception //т.к. число больше десяти");
                System.exit(0);
            }
        }
        if (Integer.parseInt(str[0].trim()) < 1 || Integer.parseInt(str[1].trim()) < 1) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("throws Exception //т.к. число меньше одного");
                System.exit(0);
            }
        }
    }

    static int resultOperation() {
        switch (sign) {
            case 1:
                var = Integer.parseInt(str[0].trim()) + Integer.parseInt(str[1].trim());
                break;
            case 2:
                var = Integer.parseInt(str[0].trim()) - Integer.parseInt(str[1].trim());
                break;
            case 3:
                var = Integer.parseInt(str[0].trim()) * Integer.parseInt(str[1].trim());
                break;
            case 4:
                var = Integer.parseInt(str[0].trim()) / Integer.parseInt(str[1].trim());
                break;
        }
        return 0;
    }

    static String convertFromArabicToRoman(String number) {

        String result = "";
        map1.put("1", "I");
        map1.put("2", "II");
        map1.put("3", "III");
        map1.put("4", "IV");
        map1.put("5", "V");
        map1.put("6", "VI");
        map1.put("7", "VII");
        map1.put("8", "VIII");
        map1.put("9", "IX");

        map2.put("10", "X");
        map2.put("20", "XX");
        map2.put("30", "XXX");
        map2.put("40", "XL");
        map2.put("50", "L");
        map2.put("60", "LX");
        map2.put("70", "LXX");
        map2.put("80", "LXXX");
        map2.put("90", "XC");

        map3.put("100", "C");
        map3.put("200", "CC");
        map3.put("300", "CCC");
        map3.put("400", "CD");
        map3.put("500", "D");
        map3.put("600", "DC");
        map3.put("700", "DCC");
        map3.put("800", "DCCC");
        map3.put("900", "CM");

        map4.put("1000", "M");
        map4.put("2000", "MM");
        map4.put("3000", "MMM");

        for (
                Map.Entry<String, String> pair : map1.entrySet()) {
            String str = pair.getKey();
            if (number.substring(number.length() - 1).equals(str)) {
                result = pair.getValue();
                stringBuilder.insert(0, result);

            }
        }
        if (number.length() > 1) {
            for (Map.Entry<String, String> pair : map2.entrySet()) {
                String str = pair.getKey();
                if ((number.substring(number.length() - 2, number.length() - 1) + "0").equals(str)) {
                    result = pair.getValue();
                    stringBuilder.insert(0, result);
                }
            }
        }
        if (number.length() > 2) {
            for (Map.Entry<String, String> pair : map3.entrySet()) {
                String str = pair.getKey();
                if ((number.substring(number.length() - 3, number.length() - 2) + "00").equals(str)) {
                    result = pair.getValue();
                    stringBuilder.insert(0, result);
                }
            }
        }
        if (number.length() > 3) {
            for (Map.Entry<String, String> pair : map4.entrySet()) {
                String str = pair.getKey();
                if ((number.substring(number.length() - 4, number.length() - 3) + "000").equals(str)) {
                    result = pair.getValue();
                    stringBuilder.insert(0, result);
                }
            }
        }
        return stringBuilder + "";
    }
}

