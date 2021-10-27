# JavaNumeralConverter

JavaNumeralConverter - конвертер систем счисления, способный переводить как целые, так и дробные числа в системы счисления от 2-ой до 36-ти ричной 💻

### Для понимания необходимо:
 * Знать что такое [система счисления](https://ru.wikipedia.org/wiki/%D0%A1%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D0%B0_%D1%81%D1%87%D0%B8%D1%81%D0%BB%D0%B5%D0%BD%D0%B8%D1%8F) и как строить системы счисления
 * Понимать "бумажный" [алгоритм](https://ege-study.ru/ege-informatika/sistemy-schisleniya-perevod-iz-odnoj-sistemy-v-druguyu/) перевода (конвертации) чисел между СС
 * Знать что такое таблица [ascii](https://ru.wikipedia.org/wiki/ASCII)
 * Понимать как связаны между собой типы данных `int` и `char`
 
 ### Работа алгоритма:
  * Алгоритм содержит методы:
    * `InputCheck(String number, int base, int newBase)` для проверки пользовательского ввода на корректность
    * `AsciiFuncToNumbers(char item)` для преобразования символа согласно таблице ascii в числовое значение, необходимое для расчётов
    * `AsciiFuncToSymbols(int item)` для преобразования числа в символ для приведения к заданной системе счисления
    * `ToDec(String number, int base)` для преобразования числа в десятичную систему счисления
    * `FromDecToNewBase(double decNumber, int newBase)` для преобразования числа в заданную систему счисления
    * `main` основной метод 
  * Методы, отвечающие взаимодействующие с таблицой ascii наиболее интересны
    ```Java
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
    ```
    
    ```Java
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
    ```
    
    
