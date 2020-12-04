package Second_2_Part.lesson_5.HW2_5;

public class Main implements Runnable {
    static final int SIZE = 100;
    static final int HALF = SIZE/2;
    static Main comparison = new Main();

    public static void main(String[] args) {

        /*Main pain = new Main();
        comparison.startPain();
        // 2nd time
        System.out.println("2nd time for pain (1st method)");
        comparison.startPain();

        System.out.println();

        System.out.println("Запускаем второй метод");
        System.out.println();*/

//        Main acceleration = new Main();
        comparison.startAcceleration();
        /*// 2nd time
        System.out.println("2nd time for acceleration (2d method)");
        comparison.startAcceleration();*/

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

        comparisonHalf1(arr, arr, " Вычисление, милисекунды");
        System.out.println( (System.currentTimeMillis() - all) + " Всё время работы метода pain, милисекунды");

    }

    static void startAcceleration() {

        long all = System.currentTimeMillis();

        float[] arr = new float[SIZE];
        for (float v : arr) {
            v = 1;
        }

        long division= System.currentTimeMillis();
        float[] arrPart1 = new float[HALF];
        float[] arrPart2 = new float[HALF];
        System.arraycopy(arr, 0, arrPart1, 0, HALF);
        System.arraycopy(arr, HALF, arrPart2, 0, HALF);
        System.out.println( (System.currentTimeMillis() - division) + " Разбиение массива, милисекунды");

        new Thread(() -> comparisonHalf1(arr, arrPart1, " Вычисление 1 части, милисекунды \n(таймер внутри потока)")).start();

        new Thread(() -> comparisonHalf2(arr, arrPart2, " Вычисление 2 части, милисекунды \n(таймер внутри потока)")).start();

//        new Thread(() -> theSpliceThread(arrPart1, arrPart2));
        theSpliceThread(arrPart1, arrPart2);

        printAllAccelerationWorkTime(all);
//        new Thread(() -> printAllAccelerationWorkTime(all));

    }


    private static void theSpliceThread(float[] arrPart1, float[] arrPart2) {
        synchronized (comparison) {
            long splice = System.currentTimeMillis();
            float[] resultArr = new float[SIZE];
            System.arraycopy(arrPart1, 0, resultArr, 0, HALF-1);
            System.arraycopy(arrPart2, 0, resultArr, HALF, HALF);
            System.out.println( (System.currentTimeMillis() - splice) + " Склейка массива, милисекунды");
//            for (float v : resultArr) {
//                System.out.print(v + " ");
//            }
            System.out.println();
            System.out.println(resultArr[resultArr.length-1] + " Последний элемент в результате");
        }
    }

    private static void printAllAccelerationWorkTime(long all) {
        synchronized (comparison) {
            System.out.println( (System.currentTimeMillis() - all) + " Всё время работы метода acceleration, милисекунды");
        }
    }

    private static void comparisonHalf2(float[] arr, float[] arrPart2, String s) {
        synchronized (comparison) {
            long calc2 = System.currentTimeMillis();
            for (int i = 0; i < arrPart2.length; i++) {
                arrPart2[i] = (float) (arr[i] * Math.sin(0.2f + i / 5)
                        * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//                arrPart2[i] = i+1; // Для теста склейки
                //засечь время выполнения 2
            }
            System.out.println((System.currentTimeMillis() - calc2) + s);
            System.out.println(arrPart2[arrPart2.length-1] + " Последний элемент во второй части расчёта " +
                    "\n(должен совпадать с последним элементом результата)");
        }
    }

    /*private static void comparisonHalf1(float[] arr, float[] arrPart1, String s) {
        comparisonHalf2(arr, arrPart1, s); // Упрощение - пока лишь усложнение понимания) Обошёлся дублированием кода
    }*/

    private static void comparisonHalf1(float[] arr, float[] arrPart1, String s) {
        synchronized (comparison) {
            long calc2 = System.currentTimeMillis();
            for (int i = 0; i < arrPart1.length; i++) {
                arrPart1[i] = (float) (arr[i] * Math.sin(0.2f + i / 5)
                        * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                //засечь время выполнения 2
            }
            System.out.println((System.currentTimeMillis() - calc2) + s);
        }
    }


    @Override
    public void run() {

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