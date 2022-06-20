package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //prva tri zadatka
        //int n = scanner.nextInt();
        //int radix = scanner.nextInt();
        //System.out.println(n + " = " + convertToBinary(n));
        //System.out.println(revertCalculatedBaseEight(convertToBaseEight(n)));
        //System.out.println(lastDigitOfBaseEight(n));
        //System.out.println(convertToAnyRadix(n, radix));

        //4. zadatak
        //int sourceRadix = scanner.nextInt();
        //int sourceNumber = scanner.nextInt();
        //int targetRadix = scanner.nextInt();
        //System.out.println(convertNumberFromAnyToAnyRadix(sourceRadix, sourceNumber, targetRadix));

        //5. zadatak
        int sourceRadix = 0;
        int targetRadix = 0;
        String sourceRadixString = "";
        String sourceNumberString = "";
        String targetRadixString = "";

        if (scanner.hasNext()) {
            sourceRadixString = scanner.nextLine();
            if (isRadixOk(sourceRadixString)) {
                sourceRadix = Integer.parseInt(sourceRadixString);
            } else {
                System.out.println("source radix error");
            }
        }
        if (scanner.hasNext()) {
            sourceNumberString = scanner.nextLine();
            if (!isNumberOk(sourceNumberString)) {
                System.out.println("number error");
            }
            //double sourceNumber = Double.parseDouble(sourceNumberString);
        }
        if (scanner.hasNext()) {
            targetRadixString = scanner.nextLine();
            if (isRadixOk(targetRadixString)) {
                targetRadix = Integer.parseInt(targetRadixString);
            } else {
                System.out.println("target radix error");
            }

        }
        if (isRadixOk(sourceRadixString) && isNumberOk(sourceNumberString) && isRadixOk(targetRadixString)) {
            System.out.println(convertFractionalFromaAnyRadixToAnyRadix(sourceRadix, sourceNumberString, targetRadix));
        } else {
            System.out.println("sve zajedno provera error");
        }
        //convertFractToAnotherBase(sourceRadix, sourceNumber, targetRadix);
        //System.out.println(convertStringToAnotherBase(sourceRadix, sourceNumberString, targetRadix));
    }

    private static boolean isInteger(String radix) {
        if (radix.length() == 0) {
            return false;
        }
        for (int i = 0; i < radix.length(); i++) {
            char ch = radix.charAt(i);
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNumberOk(String sourceNumberString) {
        int noOfDots = 0;
        if (sourceNumberString.length() == 0) {
            return false;
        } else {
            for (int i = 0; i < sourceNumberString.length(); i++) {
                char ch = sourceNumberString.charAt(i);
                if (!Character.isDigit(ch)) {
                    if (ch == '.') {
                        if (noOfDots > 1) {
                            return false;
                        } else {
                            noOfDots++;
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean isRadixOk(String radixString) {
        if (isInteger(radixString)) {
            int radix = Integer.parseInt(radixString);
            if (radix >= 1 && radix <= 36) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //metoda koja rastura broj na integer i frakcional i vraca int jednog ili drugog
    private static String returnIntegerOrFractionalPart(String sourceNumber, boolean integerPart) {
        String[] parts = sourceNumber.split("\\.");
        if (parts.length != 1) {
            if (integerPart == true) {
                return parts[0];
            } else {
                return parts[1];
            }
        } else {
            if (integerPart == true) {
                return parts[0];
            } else {
                return "0";
            }
        }
    }

    //metoda koji string celog broja bilo kog radixa konvertuje na decimalni 10 radix
    private static String convertToDecimalBase(String sourceNumber, int sourceRadix) {
        if (sourceRadix != 1) {
            int decimalNumber = Integer.parseInt(sourceNumber, sourceRadix);
            String convertedDecimal = Integer.toString(decimalNumber, 10);
            return convertedDecimal;
        } else {
            int convertedInt = 0;
            //System.out.println(stringRepOfNumber);
            for (int i = 0; i < sourceNumber.length(); i++) {
                if (sourceNumber.charAt(i) == '1') {
                    convertedInt += 1;
                }
            }
            //System.out.println(convertedInt);
            return Integer.toString(convertedInt, 10);
        }
    }

    private static String convertFractionalPartToDecimalBase(String fractionalPart, int sourceRadix) {
        double fractionalPartDecimalNumber = 0.0;
        long divider = sourceRadix;
        if (sourceRadix != 1) {
            for (int i = 0; i < fractionalPart.length(); i++) {
                System.out.println("Current digit is " + fractionalPart.charAt(i));
                int currentDigit = Integer.parseInt(String.valueOf(fractionalPart.charAt(i)), sourceRadix);
                fractionalPartDecimalNumber += 1.0 * currentDigit / divider;
                divider *= sourceRadix;
            }
        } else {
            int convertedInt = 0;
            String stringRepOfNumber = String.valueOf(fractionalPart);
            //System.out.println(stringRepOfNumber);
            for (int i = 0; i < stringRepOfNumber.length(); i++) {
                if (stringRepOfNumber.charAt(i) == '1') {
                    convertedInt += 1;
                }
            }
            //System.out.println(convertedInt);
            return Integer.toString(convertedInt, 10);
        }

        return String.valueOf(fractionalPartDecimalNumber);
    }

    //metoda koja konvertuje iz decimalnog u bilo koji drugi target radix
    private static String convertFromDecimalToAny(String sourceNumber, int targetRadix) {
        String convertedDecimalToTarget;
        if (targetRadix != 1) {
            return convertedDecimalToTarget = Integer.toString(Integer.parseInt(sourceNumber), targetRadix);
        } else {
            int decimalNumber = Integer.parseInt(sourceNumber, 10);
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < decimalNumber; i++) {
                sb2.append('1');
            }
            return sb2.toString();
        }
    }

    //metoda koja mnozi fractional part sa targetRadixom i vraca novi tako pomnozen broj
    private static String multiplyFractionalByRadix(String frationalNumber, int targetRadix) {
        String multipliedFractionaByRadix;
        System.out.println("Fractional number provided: " + frationalNumber);
        double number = Double.parseDouble(frationalNumber);
        System.out.println("number before multiplication: " + number);
        number *= targetRadix;
        multipliedFractionaByRadix = String.valueOf(number);
        return multipliedFractionaByRadix;
    }

    private static String convertFractionalFromaAnyRadixToAnyRadix
            (int sourceRadix, String sourceNumber, int targetRadix) {
        String result;
        String integerPartAnyRadix = returnIntegerOrFractionalPart(sourceNumber, true);
        System.out.println("Integer any radix: " + integerPartAnyRadix);
        String fractionalPartAnyRadix = returnIntegerOrFractionalPart(sourceNumber, false);
        System.out.println("Fractional part any radix: " + fractionalPartAnyRadix);
        String integerPartDecimalBase = convertToDecimalBase(integerPartAnyRadix, sourceRadix);
        System.out.println("Integer any decimal radix: " + integerPartDecimalBase);
        String fractionalPartDecimalBase = convertFractionalPartToDecimalBase(fractionalPartAnyRadix, sourceRadix);
        System.out.println("Fractional part decimal radix: " + fractionalPartDecimalBase);
        String currentNumber = fractionalPartDecimalBase;
        //String currentNumber = makeNewFractionalNumber(fractionalPartDecimalBase);
        StringBuilder sb = new StringBuilder();
        String integerPartTargetRadix = convertFromDecimalToAny(integerPartDecimalBase, targetRadix);
        sb.append(integerPartTargetRadix);
        sb.append(".");
        int counter = 0;
        while (counter < 5) {
            currentNumber = multiplyFractionalByRadix(currentNumber, targetRadix);
            System.out.println("Current number after multiplication is " + currentNumber);
            integerPartDecimalBase = returnIntegerOrFractionalPart(currentNumber, true);
            fractionalPartDecimalBase = returnIntegerOrFractionalPart(currentNumber, false);
            currentNumber = makeNewFractionalNumber(fractionalPartDecimalBase);
            integerPartTargetRadix = convertFromDecimalToAny(integerPartDecimalBase, targetRadix);
            System.out.println("Integer part after multiplication by Radix: " + integerPartTargetRadix);
            sb.append(integerPartTargetRadix);
            counter++;
        }
        return sb.toString();
    }

    private static String makeNewFractionalNumber(String fractionalNumberDecimalBase) {
        double decimalNumber = Double.parseDouble(fractionalNumberDecimalBase);
        for (int i = 0; i < fractionalNumberDecimalBase.length(); i++) {
            decimalNumber /= 10;
        }
        return String.valueOf(decimalNumber);
    }

    private static String convertStringToAnotherBase
            (int sourceRadix, String sourceNumberString, int targetRadix) {
        String result;
        String[] parts = sourceNumberString.split("\\.");
        int integerPartDecimalBase = Integer.parseInt(convertToDecimalBase(parts[0], sourceRadix));
        int fractionalPartDecimalBase = Integer.parseInt(convertToDecimalBase(parts[1], sourceRadix));
        String decimalNumberString = integerPartDecimalBase + "." + fractionalPartDecimalBase;
        System.out.println("Whole numebr converted to string " + decimalNumberString);
        double decimalDecimalNumber = Double.parseDouble(decimalNumberString);
        //testovi
        String integerPartToDecimatl = convertNumberFromAnyToAnyRadix(sourceRadix, integerPartDecimalBase, 10);
        System.out.println("Integral part to Decimal " + integerPartToDecimatl);
        String convertedIntegerPartToDecimal = convertNumberFromAnyToAnyRadix(10, integerPartDecimalBase, targetRadix);
        int integerIntegerPartToDecimatl = Integer.parseInt(convertedIntegerPartToDecimal);
        System.out.println("Integral part Converted to new radix " + integerIntegerPartToDecimatl);
        result = convertFractToAnotherBase(sourceRadix, decimalDecimalNumber, targetRadix);
        return result;
    }

    private static String convertFractToAnotherBase(int sourceRadix, double sourceNumber, int targetRadix) {
        int integerPart = splitDecimalToIntegerPart(sourceNumber);
        String integerConverted = convertNumberFromAnyToAnyRadix(sourceRadix, integerPart, targetRadix);
        StringBuilder sb = new StringBuilder(integerConverted);
        sb.append(".");
        int currInteger = integerPart;
        int counter = 0;
        while (counter < 5) {
            sourceNumber = splitDecimalTofractionalPart(sourceNumber);
            System.out.println("U Counteru fractional part: " + sourceNumber);
            sourceNumber *= targetRadix;
            System.out.println("U Counteru fractional part posle mnozenja sa target radixom: " + sourceNumber);
            integerPart = splitDecimalToIntegerPart(sourceNumber);
            System.out.println("U Counteru integer part novog izmnozenog broja: " + integerPart);
            sb.append(convertNumberFromAnyToAnyRadix(sourceRadix, integerPart, targetRadix));
            counter++;
        }
        return sb.toString();
    }

    private static double splitDecimalTofractionalPart(double sourceNumber) {
        String sourceNumString = String.valueOf(sourceNumber);
        String[] splittedNum = sourceNumString.split("\\.");
        System.out.println("SplitetdNum[1]: " + splittedNum[1]);
        int truncatedTo5FDigits;
        double fractionalPart;
        //int koji odgovoara duzini string frakcionalnog dela ili broju 5
        int fractionalLength;
        if (splittedNum[1].length() >= 5) {
            fractionalLength = 5;
        } else {
            fractionalLength = splittedNum[1].length();
        }
        //vracanje stringa decimalnog dela u integer
        truncatedTo5FDigits = Integer.parseInt(splittedNum[1].substring(0, fractionalLength));
        //prevodjenje integera ponovo u decimalni deo
        fractionalPart = truncatedTo5FDigits / 10.0;
        for (int i = 0; i < fractionalLength - 1; i++) {
            fractionalPart /= 10.0;
        }
        //vracanje decimalnog dela
        System.out.println("Fractiona part je: " + fractionalPart);
        return fractionalPart;
    }

    private static int splitDecimalToIntegerPart(double sourceNumber) {
        String sourceNumString = String.valueOf(sourceNumber);
        //System.out.println(sourceNumString);
        String[] splittedNum = sourceNumString.split("\\.");
        //System.out.println("Method splitDecimalToIntegerPart");
        //for(String splittedPart : splittedNum) {
        //    System.out.println(splittedPart);
        //}
        return Integer.parseInt(splittedNum[0]);
        //return 0;
    }


    private static String convertedNumberToTargetRadixExceptOne(int sourceRadix, int sourceNumber, int targetRadix) {
        String convertedNumber;
        int decimalNumber = Integer.parseInt(String.valueOf(sourceNumber), sourceRadix);
        convertedNumber = Integer.toString(decimalNumber, targetRadix);
        return convertedNumber;
    }

    private static String convertNumberFromAnyToAnyRadix(int sourceRadix, int sourceNumber, int targetRadix) {
        if (sourceRadix == 1) {
            return convertFromSourceRadixOne(sourceNumber, targetRadix);
        } else if (targetRadix == 1) {
            return convertToTargetRadixOne(sourceRadix, sourceNumber);
        } else {
            return convertedNumberToTargetRadixExceptOne(sourceRadix, sourceNumber, targetRadix);
        }
    }

    private static String convertFromSourceRadixOne(int number, int targetRadix) {
        int convertedInt = 0;
        String stringRepOfNumber = String.valueOf(number);
        //System.out.println(stringRepOfNumber);
        for (int i = 0; i < stringRepOfNumber.length(); i++) {
            if (stringRepOfNumber.charAt(i) == '1') {
                convertedInt += 1;
            }
        }
        //System.out.println(convertedInt);

        return Integer.toString(convertedInt, targetRadix);
    }

    private static String convertToTargetRadixOne(int sourceRadix, int number) {
        int decimalNumber = Integer.parseInt(String.valueOf(number), sourceRadix);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < decimalNumber; i++) {
            sb.append('1');
        }
        return sb.toString();
    }

    public static String convertToAnyRadix(int n, int radix) {
        String convertedByRadix = Long.toString(n, radix);
        String radixPrefix = findRadix(radix);
        return radixPrefix + convertedByRadix;

    }

    private static String findRadix(int radix) {
        if (radix == 2) {
            return "0b";
        } else if (radix == 8) {
            return "0";
        } else if (radix == 16) {
            return "0x";
        } else {
            return "";
        }
    }

    public static String convertToBinary(int n) {
        String binaryString;
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            //System.out.println(n);
            int remainder = n % 2;
            sb.append(remainder);
            //System.out.println(sb.toString());
            n = n / 2;
        }
        String firstOrder = sb.toString();
        binaryString = revertCalculatedBinary(firstOrder);
        return binaryString;
    }

    private static String revertCalculatedBinary(String firstOrder) {
        StringBuilder sb = new StringBuilder("0b");
        for (int i = firstOrder.length() - 1; i >= 0; i--) {
            sb.append(firstOrder.charAt(i));
        }
        String revertedOrder = sb.toString();
        return revertedOrder;
    }

    private static String revertCalculatedBaseEight(String firstOrder) {
        StringBuilder sb = new StringBuilder("0O");
        for (int i = firstOrder.length() - 1; i >= 0; i--) {
            sb.append(firstOrder.charAt(i));
        }
        String revertedOrder = sb.toString();
        return revertedOrder;
    }

    public static String convertToBaseEight(int n) {
        StringBuilder sb = new StringBuilder();
        int remainder = 0;
        while (true) {
            remainder = n % 8;
            sb.append(remainder);
            n = n / 8;
            if (n == 0) {
                break;
            }
        }
        String octaString = sb.toString();
        return octaString;
    }

    public static int lastDigitOfBaseEight(int n) {
        String revertStringOfBaseEight = revertCalculatedBaseEight(convertToBaseEight(n));
        String lastDigitOfBaseEightString = revertStringOfBaseEight.substring(revertStringOfBaseEight.length() - 1);
        int lastDigitOfBaseEight = Integer.parseInt(lastDigitOfBaseEightString);
        return lastDigitOfBaseEight;
    }
}
