package Howe_Work_2;

import java.util.Arrays;

public class task3 {

    public static void main(String[] args) {
        int[] newArray = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < newArray.length; i++) {
            if (newArray[i] < 6 ) {
                newArray[i] *= 6;
            }
        }
        System.out.println(Arrays.toString(newArray));
    }
}

/** Task 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ].
 * Пройти по нему циклом, числа меньшие 6 умножить на 2;**/
//Serega, sure
