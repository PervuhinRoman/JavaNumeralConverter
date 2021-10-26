package com.company;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    // проверка на корректность введённых СС и числа для перевода
    public static byte[] InputCheck(String number, int base, int newBase){
        byte[] errorsList = new byte[3];
        if(base <= 1 || base > 36){
            errorsList[0] = 1;
            // System.out.println("Incorrect input!");
        }
        else if (newBase <= 1 || newBase > 36) {
            // System.out.println("Incorrect input!");
            errorsList[1] = 2;
        }
        else {
            char [] charNumberArr = number.toCharArray();
            for(int i = 0; i < charNumberArr.length; i++){
                if(AsciiFuncToNumbers(charNumberArr[i]) > base){
                    // System.out.println("Incorrect input!");
                    errorsList[2] = 3;
                }
            }
        }

        return errorsList;
    }

    // преобразуем символ согласно таблице ascii в числовое значение, необходимое для расчётов
    public static int AsciiFuncToNumbers(char item){
        int newItem = 0;
        if ((int)item >= 48 && (int)item <= 57) {              // преобразование чисел
            newItem = item - 48;
        }
        else if ((int)item >= 65 && (int)item <= 90) {         // преобразование больших букв
            newItem = item - 48 - 7;
        }
        else if((int)item >= 97 && (int)item <= 122) {         // преобразование маленьких букв
            newItem = item - 87;
        }

        return newItem;
    }

    // преобразует число в символ для приведения к заданной СС
    public static char AsciiFuncToSymbols(int item){
        char newItem = 0;
        if(item >= 10){                             // преобразование в буквы
            newItem = (char)(item + 48 + 7);
        }
        else if(item < 10){
            newItem = (char)(item + 48);            // преобразование в цифры
        }

        return newItem;
    }

    public static double ToDec(String number, int base){
        double decNumber = 0.0;                                    // число преобразованное в десятичную СС
        String subStr [] = number.split("\\.");              // разделение числа на целую и дробную части
        char[] charNumberArr = subStr[0].toCharArray();            // превращаем целую часть числа в символьный массив

        for(int i = 0; i < charNumberArr.length; i++){             // преобразуем целую часть числа
            decNumber += AsciiFuncToNumbers(charNumberArr[i]) * Math.pow(base, charNumberArr.length - i - 1);
        }
        charNumberArr = subStr[1].toCharArray();                   // превращаем дробную часть числа в символьный массив
        for(int i = 0; i < charNumberArr.length; i++){             // преобразуем дробную часть числа
            decNumber += AsciiFuncToNumbers(charNumberArr[i]) * Math.pow(base, (-1 + i * (-1)));
        }

        return decNumber;
    }

    public static String FromDecToNewBase(double decNumber, int newBase){
        String wholePartOfNewNumber = "";
        String fractionalPartOfNewNumber = ".";
        int wholePart = (int)decNumber;
        double fractionalPart = decNumber - wholePart;

        while(wholePart > 0){                                       // преобразование целой части числа
            wholePartOfNewNumber += String.valueOf(AsciiFuncToSymbols(wholePart % newBase));
            wholePart /= newBase;
        }

        // реверс целой части числа т.к. из-за математического алгоритмы мы вынуждены заполнять строку с конца
        char[] subArr = wholePartOfNewNumber.toCharArray();
        int startIndex = 0;
        int endIndex = subArr.length - 1;
        char temp;
        for (; endIndex > startIndex; startIndex++, endIndex--) {
            temp = subArr[startIndex];
            subArr[startIndex] = subArr[endIndex];
            subArr[endIndex] = temp;
        }
        wholePartOfNewNumber = "";                  // записываем "перевёрнутую" строку
        for(int i = 0; i < subArr.length; i++){
            wholePartOfNewNumber += subArr[i];
        }

        while(fractionalPart != 0){                 // преобразование дробной части числа
            fractionalPart *= newBase;
            fractionalPartOfNewNumber += AsciiFuncToSymbols((int)fractionalPart);
            fractionalPart = fractionalPart - (int)fractionalPart;
        }

        return wholePartOfNewNumber + fractionalPartOfNewNumber;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);

        String number = in.nextLine() + ".0";
        int base = in.nextInt();
        int newBase = in.nextInt();

        System.out.println(ToDec(number, base));
        System.out.println(FromDecToNewBase(ToDec(number, base), newBase));
    }
}
