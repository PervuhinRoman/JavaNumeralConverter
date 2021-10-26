package com.company;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static int WholePart(double number){
        return (int)number;
    }

    public static double FractionalPart(double number){
        String strNumber = String.valueOf(number);            // представление вещественного числа в виде строки
        String subStr[] = new String[2];                      // масиив для хранения целой и дробной частец
        subStr = strNumber.split("\\.");                // делим строковое представление вещественного числа на целую и дробную части по разделителю "точка"
        return Double.parseDouble("0." + subStr[1]);       // добавляем к дробной части числа в строковом представлении 0. и перобразуем обратно в вещественное
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Scanner inNumber = new Scanner(System.in).useLocale(Locale.US); // для работы с "."

        double number = inNumber.nextDouble();

        System.out.println(WholePart(number) + " - whole part " + '\n' + FractionalPart(number) + " - fractional part ");
    }
}
