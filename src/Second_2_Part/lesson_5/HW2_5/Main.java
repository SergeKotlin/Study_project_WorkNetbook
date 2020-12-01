package Second_2_Part.lesson_5.HW2_5;

public class Main {
    static final int SIZE = 10000000;
    static final int HALF = SIZE/2;

    public static void main(String[] args) {

        /*Main pain = new Main();
        pain.startPain();
        // 2nd time
        System.out.println("2nd time for pain (1st method)");
        pain.startPain();

        System.out.println();*/

        Main acceleration = new Main();
        acceleration.startAcceleration();


    }

    static void startPain() {

        long all = System.currentTimeMillis();

        long ini = System.currentTimeMillis();
        float[] arr = new float[SIZE];
        for (float v : arr) {
            v = 1;
            //засечь время выполнения 1
        }
        System.out.println( (System.currentTimeMillis() - ini) + " Создание массива и 'Инициализация' единицами, милисекунды");

        long calc = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5)
                    * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            //засечь время выполнения 2
        }
        System.out.println( (System.currentTimeMillis() - calc) + " Вычисление, милисекунды");
        System.out.println( (System.currentTimeMillis() - all) + " Всё время работы метода pain, милисекунды");

    }

    static void startAcceleration() {

        long all = System.currentTimeMillis();

        float[] arr = new float[SIZE];
        for (float v : arr) {
            v = 1;
        }

        float[] arrPart1 = new float[0];
        float[] arrPart2 = new float[0];
        System.arraycopy(arr, 0, arrPart1, 0, HALF);
        System.arraycopy(arr, HALF, arrPart2, 0, HALF);


        long calc1 = System.currentTimeMillis();
        for (int i = 0; i < arrPart1.length; i++) {
            arrPart1[i] = (float)(arr[i] * Math.sin(0.2f + i / 5)
                    * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            //засечь время выполнения 2
        }
        System.out.println( (System.currentTimeMillis() - calc1) + " Вычисление 1 части, милисекунды");

        long calc2 = System.currentTimeMillis();
        for (int i = arrPart1.length+1; i < arrPart2.length + arrPart1.length; i++) {
            arrPart2[i] = (float)(arr[i] * Math.sin(0.2f + i / 5)
                    * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            //засечь время выполнения 2
        }
        System.out.println( (System.currentTimeMillis() - calc2) + " Вычисление 2 части, милисекунды");


        float[] resultArr = new float[0];
        System.arraycopy(arrPart1, 0, resultArr, 0, HALF);
        System.arraycopy(arrPart2, 0, resultArr, HALF, HALF);

        System.out.println( (System.currentTimeMillis() - all) + " Всё время работы метода acceleration, милисекунды");

    }

}

/** ✓1. Необходимо написать два метода, которые делают следующее:
 ✓1) Создают одномерный длинный массив, например:
 static final int size = 10000000;
 static final int h = size / 2;
 float[] arr = new float[size];
 ✓2) Заполняют этот массив единицами;
 ✓3) Засекают время выполнения: long a = System.currentTimeMillis();
 ✓4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
 ✓arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 ✓5) Проверяется время окончания метода System.currentTimeMillis();
 ✓6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
 Отличие первого метода от второго:
 Первый просто бежит по массиву и вычисляет значения.
 Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и
 потом склеивает эти массивы обратно в один.

 Пример деления одного массива на два:
 System.arraycopy(arr, 0, a1, 0, h);
 System.arraycopy(arr, h, a2, 0, h);

 Пример обратной склейки:
 System.arraycopy(a1, 0, arr, 0, h);
 System.arraycopy(a2, 0, arr, h, h);

 Примечание:
 System.arraycopy() копирует данные из одного массива в другой:
 System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение,
 откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
 По замерам времени:
 Для первого метода надо считать время только на цикл расчета:
 for (int i = 0; i < size; i++) {
 arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 }
 Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
 * **/
//Serega, sure