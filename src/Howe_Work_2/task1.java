package Howe_Work_2;

import java.util.Arrays;

import static java.lang.Math.abs;

/* Как задать массив:
* int[] myArray = new int[10];
* int[] myArray = {value1, value2};
* Копировать значение: .clone()
* Вывод на экрана - для массива: Arrays.toString(myName)*/

public class task1 {

    public static void main(String[] args) {
        int[] Array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        int[] ArrayResult = Array.clone();
        for (int i = 0; i < Array.length; i++) {
            //ArrayResult[i] = abs(Array[i] - 1);
            if (Array[i] == 0) {
                ArrayResult[i] = 1;
            } else {
                ArrayResult[i] = 0;
            }
        }
        System.out.println(Arrays.toString(Array));
        System.out.println(Arrays.toString(ArrayResult));
    }
}

/** Task 1. Задать целочисленный массив, состоящий из элементов 0 и 1.
 * Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
 * С помощью цикла и условия заменить 0 на 1, 1 на 0;**/
//Serega, sure
