package com.company;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    // проверка на корректность введённых СС и числа для перевода
    public static String InputCheck(String number, int base, int newBase){
        String errorsList = "";
        if(base <= 1 || base > 36){                                    // проверка основания исходной СС
            errorsList += '1';
        }
        else if (newBase <= 1 || newBase > 36) {                       // проверка нового основания СС
            errorsList += '2';
        }
        else {                                                         // проверка числа
            char [] charNumberArr = number.toCharArray();
            for(int i = 0; i < charNumberArr.length; i++){
                if(AsciiFuncToNumbers(charNumberArr[i]) > base){
                    errorsList += '3';
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
        String subStr [] = number.split("\\.", 2);      // разделение числа на целую и дробную части

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

        if(fractionalPart == 0){
            fractionalPartOfNewNumber = "";
        }

        while(fractionalPart != 0){                 // преобразование дробной части числа
            fractionalPart *= newBase;
            fractionalPartOfNewNumber += AsciiFuncToSymbols((int)fractionalPart);
            fractionalPart = fractionalPart - (int)fractionalPart;
        }

        return wholePartOfNewNumber + fractionalPartOfNewNumber;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US); // стандартный сканер и функция для обработки "."

        while(true){
            System.out.print("Enter the number: ");
            String number = in.nextLine() + ".0";                 // решает проблему, если введено НЕ дробное число
            if(number.indexOf(",") != -1){                        // решает проблему если введена "," вместо "."
                char [] subNum = number.toCharArray();
                subNum[number.indexOf(",")] = '.';
                number = "";
                for(char i : subNum){
                    number += i;
                }
            }
            System.out.print("From base: ");
            int base = in.nextInt();
            System.out.print("To base: ");
            int newBase = in.nextInt();
            in.nextLine();

            InputCheck(number, base, newBase);                    // проверка ввода на корректность
            if(InputCheck(number, base, newBase).length() > 0){   // описание ошибки ввода
                for(int i = 0; i < InputCheck(number, base, newBase).length(); i++) {
                    switch (InputCheck(number, base, newBase).charAt(i)) {
                        case '1':
                            System.out.println("Неверно введена исходная система счисления");
                            break;
                        case '2':
                            System.out.println("Неверно введена новая система счисления");
                            break;
                        case '3':
                            System.out.println("Недопустимое значение исходного числа");
                            break;
                    }
                }

                System.out.print("Try again (1) or help (2): ");
                String act = in.nextLine();
                switch (act){
                    case "1":
                        System.out.println();
                        break;
                    case "2":                                     // вывод теории о СС
                        System.out.println();
                        System.out.println(" * Все возможные СС 2-36. Ограничения связаны с всего лишь 10-ю цифрами и английским алфавитом в 26 символов, он просто закончился");
                        System.out.println(" * В водимом числе не должно быть составляющих, которые бы были больше самой СС (например 'A' не может быть в 6-ти ричной системе т.к. A = 10, а максимальное значение 6-ти ричной СС - 5)");
                        System.out.println();
                }
            }
            else {
                System.out.println(FromDecToNewBase(ToDec(number, base), newBase) + " in " + newBase);  // вывод преобразованного числа
                System.out.println();
            }
        }
    }
}
