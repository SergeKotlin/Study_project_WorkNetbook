package Howe_Work_2;

import java.util.Arrays;

public class task5 {

    public static void main(String[] args) {
        int[] newArray = {0, 2, -3, 5, 9}; //С равными элементами не рассматриваем
        int nowMin = newArray[0];
        int nowMax = newArray[0];
        int i = 1;
        do {
            if (newArray[i] > nowMax) {
                nowMax = newArray[i];
            } else if (newArray[i] < nowMin) {
                nowMin = newArray[i];
            }
            i++;
        } while (i < newArray.length);
        System.out.printf("%s%nMinimum %d, Maximum %d%n", Arrays.toString(newArray), nowMin, nowMax);
    }

}

/** Task 5. Задать одномерный массив и найти в нем
 *  минимальный и максимальный элементы (без помощи интернета);**/
//Serega, sure
