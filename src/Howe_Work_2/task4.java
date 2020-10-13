package Howe_Work_2;

import java.util.Arrays;

/* Что от меня хотят? Уменьшенная модель:
* 1 0 0 1
* 0 1 1 0
* 0 1 1 0
* 1 0 0 1
*
* 1 0 0 0 1
* 0 1 0 1 0
* 0 0 1 0 0
* 0 1 0 1 0
* 1 0 0 0 1*/

public class task4 {

    public static void main(String[] args) {
        corrector(12);
        System.out.println();
        corrector(7);
    }

    static int[][] corrector(int size) {
        int[][] newArray = new int[size][size];
        int step = 0;
        for (int i = 0; i < newArray.length; i++) {
            if (i == 0 | i == newArray.length - 1) {
                newArray[i][0] = 1;
                newArray[i][newArray.length - 1] = 1; //1-й и последний элементы
                step += 1;
            } else {
                newArray[i][step] = 1;
                newArray[i][newArray.length - 1 - step] = 1;
                step += 1;
            }
        }
        for (int[] printResult : newArray) {
            System.out.println(Arrays.toString(printResult));
        }
        return newArray;
    }
}

/** Task 4. Создать квадратный двумерный целочисленный массив
 * (количество строк и столбцов одинаковое).
 * B с помощью цикла(-ов) заполнить его диагональные элементы единицами;**/
//Serega, sure
