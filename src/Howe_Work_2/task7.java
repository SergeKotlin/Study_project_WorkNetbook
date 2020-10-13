package Howe_Work_2;

import java.util.Arrays;
import java.util.Scanner;

public class task7 {

    public static void main(String[] args) {
        int[] array = {5, 6, 7, 3, 4, 9, 1};
        int n = 5;

        shiftingElements(n, array);

    }

    public static void shiftingElements(int shift, int[]array) {
        int[] arrayResult = new int[array.length];
        int n = shift; // n будет утверждённым шагом смещения
        // имеет смысл смещение < array.length
        System.out.printf("Массив %s имеет размер %d.%nВозможно смещение элементов от -%d до %d, включительно.%nВы задали смещение %d.%nХотите продолжить или перезадите смещение? (1 - оставить, 0 - перезадать)%n",  Arrays.toString(array), array.length, array.length - 1, array.length - 1, shift);
        Scanner scan = new Scanner(System.in);
        int answer = scan.nextInt();
        if (answer == 0) {
            System.out.printf("Массив %s имеет размер %d.%nЗадайте смещение элементов (от -%d до %d, включительно)%nn = ",  Arrays.toString(array), array.length, array.length - 1, array.length - 1);
            scan = new Scanner(System.in);
            n = scan.nextInt();
        }

        if (n < 0) {
            // 5 = -1, 4 = -2, ... 1 = -5
            n += array.length; // приводим отриц. смещение к "5"
        }
        if (n < array.length) {
            int step = 0;
            for (int i = 0; i < array.length; i++) {
                // "-1", вычитание : "5", прибавление (в зависимости от - выходим ли за массив)
                step = -(array.length - n);
                if (i + step >= 0) {
                    arrayResult[i + step] = array[i];
                } else {
                    step = n;
                    arrayResult[i + step] = array[i];
                }
            }
        } else {
            System.out.println("Ой, нет. Данные введены некорректно");
        }

        System.out.printf("Начальный массив %s%nСмещённый массив %s%n", Arrays.toString(array), Arrays.toString(arrayResult));
    }

}

/** Task 7. Написать метод, которому на вход подается одномерный массив и
 * число n (может быть положительным, или отрицательным),
 * при этом метод должен сместить все элементы массива на n позиций.
 * Для усложнения задачи нельзя пользоваться вспомогательными массивами**/
//Serega, sure
