package Howe_Work_2;

import java.util.Arrays;

public class task2 {
    public static void main(String[] args) {
        int[] newArray = new int[8];
        int count = 0;
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] += count;
            count += 3;
        }
        System.out.println(Arrays.toString(newArray));
    }
}

/** Task 2. Задать пустой целочисленный массив размером 8.
 * С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;**/
//Serega, sure