package Home_Work_6;

import java.util.Arrays;

public class professorSnake_v2 {
    private int column;
    private int row;
    private static int[][] one;

    professorSnake_v2() {
        this.column = 0;
        this.row = 0;
    }

    professorSnake_v2(int col, int row) {
        setCol(col);
        setRow(row);
    }

    public void setCol (int col) {
        if (col >= 0) {
            this.column = col;
        }
    }

    public void setRow (int row) {
        if (row >= 0) {
            this.row = row;
        }
    }

    public int[] getSnakeField (int parametr) {
        if (parametr == 0) {
            System.out.println("Размер поля [столбцы, строки]:");
        } else {
            System.out.println("0 - вывод размера поля [столбцы, строки]");
        }
        return new int[]{this.column, this.row};
    }

    public int[] getSnakeField () {
        return getSnakeField (0);
    }


    public static void main(String[] args) {
        professorSnake_v2 oneField = new professorSnake_v2();
        one = new int[4][4];
        oneField.setCol(4);
        oneField.setRow(4);

        createEmptyField(4);

        System.out.println( Arrays.toString(oneField.getSnakeField()) );
        snakeTwist(oneField);
    }

    private static void createEmptyField(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                one[i][j] = 0;
            }
        }
    }


    private static void snakeTwist(professorSnake_v2 field) {
        int[][] workArray = new int[field.column][field.row];

        int[] starPosition = {0, 0};
        int[] position = new int[2];
        int numberOfElements = field.column * field.row;
        int step = 1;

        //Старт, вниз
        if (starPosition[0] < workArray.length) {
            for (int i = 0; i < workArray.length; i++) {
                one[i][0] = step++;
                position[0] = i;
                //default: position[1] = 0;
            }
        }

        //Вправо
        for (int i = step; i < numberOfElements; i++) {
            //if (starPosition[0] < workArray.length && workArray[starPosition[0]+1][starPosition[1]] == 0) {
            if (position[1] < workArray.length) {
                for (int j = position[1]+1; j < workArray.length; j++) {
                    one[position[0]][j] = step++;
                    //unchanged: position[0]
                    position[1] = j;
                }
            }
        }

        //Вверх
        for (int i = step; i < numberOfElements; i++) {
            if (position[0] > workArray.length-position[0]) {
                for (int j = position[0]-1; j >= 0; j--) {
                    one[j][position[1]] = step++;
                    //unchanged: position[1]
                    position[0] = j;
                }
            }
        }

        //Влево
        for (int i = step; i < numberOfElements; i++) {
            if (position[1] > workArray.length-position[1] && one[position[0]][position[1]-1] == 0) {
                for (int j = position[1]-1; j >= 0; j--) {
                    if (one[position[0]][position[1]-1] == 0) {
                        one[position[0]][j] = step++;
                        //unchanged: position[0]
                        position[1] = j;
                    }
                }
            }
        }

        //Вниз
        for (int i = step; i < numberOfElements; i++) {
            if (position[0] < workArray.length-position[0] && one[position[0]+1][position[1]] == 0) {
                for (int j = position[0]+1; j < workArray.length-1; j++) {
                    if (one[j][position[1]] == 0) {
                        one[j][position[1]] = step++;
                        //unchanged: position[1]
                        position[0] = j;
                    }
                }
            }
        }

        //Вправо
        for (int i = step; i < numberOfElements; i++) {
            //if (starPosition[0] < workArray.length && workArray[starPosition[0]+1][starPosition[1]] == 0) {
            if (position[1] < workArray.length) {
                for (int j = position[1]+1; j < workArray.length-1; j++) {
                    one[position[0]][j] = step++;
                    //unchanged: position[0]
                    position[1] = j;
                }
            }
        }

        //Вверх
        for (int i = step; i == numberOfElements; i++) {
            if (position[0] == workArray.length-position[0]) {
                for (int j = position[0]-1; j > 0; j--) {
                    one[j][position[1]] = step++;
                    //unchanged: position[1]
                    position[0] = j;
                }
            }
        }


        //Принт
        printResultField();


        /*if (если первое значение) {

            падаем первое значение в танец змеи
            fori ()
                if 1 не упирается в конец массива, 2 не упирается в числа
                    задаём
                    или ход вниз
                    получаем новые координаты
                if 1 не упирается в конец массива, 2 не упирается в числа
                    или ход вправо
                    получаем новые координаты
                if 1 не упирается в конец массива, 2 не упирается в числа
                    или ход вверх
                    получаем новые координаты
                if 1 не упирается в конец массива, 2 не упирается в числа
                    или ход влево
                    получаем новые координаты
        } else {
        }*/


        /*      // Инициализация шагов
                while (true) {
        //            stepDown();
        //            stepRight();
        //            stepUp();
        //            stepLeft();
                }*/
    }

    private static void printResultField() {
        for (int i = 0; i < one.length; i++) {
            for (int j = 0; j < one.length; j++) {
                System.out.print(one[i][j] + " ");
            }
            System.out.println();
        }
    }

}

/** Разработать метод, принимающий на вход размеры двумерного массива
 * и выводящий массив в виде инкременированной цепочки чисел,
 * идущих по спирали против часовой стрелки.
 * Примеры:
 * 2х3
 * 1 6
 * 2 5
 * 3 4
 *
 * 3х1
 * 1 2 3
 *
 * 4х4
 * 01 12 11 10
 * 02 13 16 09
 * 03 14 15 08
 * 04 05 06 07
 *
 * 0х7
 * Ошибка!
 **/
//Serega, sure