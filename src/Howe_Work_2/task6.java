package Howe_Work_2;

import java.util.Arrays;


public class task6 {

    public static void main(String[] args) {

        int[] arrayV1 = {5, 6, 7, 3, 4, 9};
        int[] arrayV2 = {2, 2, 2, 1, 2, 2, 10, 1};
        int[] arrayV3 = {2, 2, 2, 1, 2, 2, 12, 1, 22};
        System.out.println(summator(arrayV3));
        System.out.println(summator(arrayV1));
    }

    public static boolean summator(int[] arr) {
        int sumLeft = 0;
        for (int i = 0; i < arr.length; i++) {
            sumLeft += arr[i];

            int sumRight = 0;
            for (int j = i + 1; j < arr.length; j++) {
                sumRight += arr[j];
            }

            if (sumLeft == sumRight) {
                System.out.printf("%s%nМассив годится! «Баланс сил» имеет место в нём%n", Arrays.toString(arr));
                return true;

            }
        }
        System.out.println(Arrays.toString(arr));
        return false;
    }
}

/** Task 6. Написать метод, в который передается не пустой одномерный целочисленный массив,
 * метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
 * Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true,
 * checkBalance([1, 1, 1, || 2, 1]) → true, граница показана символами ||, эти символы в массив не входят**/
//Serega, sure
