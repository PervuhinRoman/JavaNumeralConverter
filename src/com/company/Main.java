package com.company;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void CorrectInput(){
        ;
    }

    // преобразуем символ согласно таблице ascii в числовое значение, необходимое для расчётов
    public static int AsciiFunc(char item){
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

    public static double ToDec(String number, int base){
        double decNumber = 0.0;                                    // число преобразованное в десятичную СС
        String subStr [] = number.split("\\.");              // разделение числа на целую и дробную части
        char[] charNumberArr = subStr[0].toCharArray();            // превращаем целую часть числа в символьный массив

        for(int i = 0; i < charNumberArr.length; i++){             // преобразуем целую часть числа
            decNumber += AsciiFunc(charNumberArr[i]) * Math.pow(base, charNumberArr.length - i - 1);
        }
        charNumberArr = subStr[1].toCharArray();                   // превращаем дробную часть числа в символьный массив
        for(int i = 0; i < charNumberArr.length; i++){             // преобразуем дробную часть числа
            decNumber += AsciiFunc(charNumberArr[i]) * Math.pow(base, (-1 + i * (-1)));
        }

        return decNumber;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);

        String number = in.nextLine();
        int base = in.nextInt();

        System.out.println(ToDec(number, base));
    }
}
