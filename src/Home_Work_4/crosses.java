package Home_Work_4;

import java.util.Random;
import java.util.Scanner;

public class crosses {

    //initialization CONSTANTS
//    static final int SIZE_GAME = 5;
//    static final int SIZE_OFTHE_VICTORY = 3;
    static final int SIZE_GAME = 7;
    static final int SIZE_OFTHE_VICTORY = 4;
//    static final int SIZE_GAME = 11;
//    static final int SIZE_OFTHE_VICTORY = 5;
    static final char EMPTY_CHARACTER = '♣';
    static final char HUMAN_SYMBOL = 'X';
    static final char AI_SYMBOL = 'O';

    static final String HEADER_FIRST_SYMBOL = "♥";
    static final String EMPTY = " ";

    static char[][] game_map = new char[SIZE_GAME][SIZE_GAME];
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();


    public static void main(String[] args) {

        turnGame();

    }

    private static void turnGame() {

        initMap();
        printMap();
        playGame();

    }

    private static void initMap() {
        for (int i = 0; i < SIZE_GAME; i++) {
            for (int j = 0; j < SIZE_GAME; j++) {
                game_map[i][j] = EMPTY_CHARACTER;
            }
        }
    }

    private static void printMap() {

        printMapHeader();
        printMapRow();

    }

    private static void printMapHeader() {
        System.out.print(HEADER_FIRST_SYMBOL + EMPTY);
        for (int i = 0; i < SIZE_GAME; i++) {
            printMapNumber(i);
        }
        System.out.println();
    }

    private static void printMapNumber(int i) {
        System.out.print(i + 1 + EMPTY);
    }

    private static void printMapRow() {
        for (int i = 0; i < SIZE_GAME; i++) {
            printMapNumber(i);
            for (int j = 0; j < SIZE_GAME; j++) {
                System.out.print(game_map[i][j] + EMPTY);
            }
            System.out.println( );
        }
    }


    private static void playGame() {
            //human
        while (true) {
            int[] target = humanTurn(); //make a move
            printMap(); //print a field
            checkEnd(HUMAN_SYMBOL, target); //cheсk

            //ai
            target = aiTurn(); //make a move
            printMap(); //print a field
            checkEnd(AI_SYMBOL, target); //cheсk
        }
    }

    private static int[] humanTurn() {
        //String rowColNumbers;
        int rowNumber;
        int colNumber;
        System.out.println("\nХод человека! Введите номер строки и столбца");

        do {
            //rowColNumbers = scanner.nextInt() + "";
            //rowNumber = rowColNumbers.charAt(0) - '0'; // the offset on the table ascii/unicode for type "int"
            //colNumber = rowColNumbers.charAt(1) - '0';
            System.out.print("Строка = ");
            rowNumber = scanner.nextInt();
            System.out.print("Столбец = ");
            colNumber = scanner.nextInt();
        } while (!isCellValid(rowNumber, colNumber));

        game_map[rowNumber - 1][colNumber - 1] = HUMAN_SYMBOL;

        return new int[]{rowNumber - 1, colNumber - 1};

    }

    private static boolean isCellValid(int rowNumber, int colNumber, boolean isAI) {

        if(!isAI && ( (rowNumber < 1) || (rowNumber > SIZE_GAME) || (colNumber < 1) || (colNumber > SIZE_GAME) ) ) {
            System.out.println("\nПроверьте - в ответе должны быть 2 значения: строки и столбца, каждое от 1 до " + SIZE_GAME);
            return false;
        }

        if (game_map[rowNumber - 1][colNumber - 1] != EMPTY_CHARACTER) {
            if (!isAI){
                System.out.println("\nВы выбрали занятую ячейку");
            }
            return false;
        }

        return true;
    }

    private static boolean isCellValid(int rowNumber, int colNumber) {
        return isCellValid(rowNumber, colNumber, false);
    }

    private static void checkEnd(char symbol, int[] targetCell) {

        boolean isEnd = false;

        //checkWin
        if(checkWin(symbol, targetCell)) {
            String winMessage;

            if(symbol == HUMAN_SYMBOL) {
                winMessage = "УРА! Вы победили!";
            } else {
                winMessage = "Восстание придёт! Победил AI";
            }

            isEnd = true;
            System.out.println(winMessage);
        }

        //isMapFull
        if(!isEnd && isMapFull() ) {
            System.out.println("Ничья!");
            isEnd = true;
        }

        //end?
        if(isEnd) {
            System.exit(0);
        }

        //
    }

    private static boolean checkWin(char symbol, int[]  targetCell) {
        //заглушка, ущербнейшая и не масштабируемая реализация проверки условий победы
        /*if (game_map[0][0] == symbol && game_map[0][1] == symbol && game_map[0][2] == symbol) return true;
        if (game_map[1][0] == symbol && game_map[1][1] == symbol && game_map[1][2] == symbol) return true;
        if (game_map[2][0] == symbol && game_map[2][1] == symbol && game_map[2][2] == symbol) return true;

        if (game_map[0][0] == symbol && game_map[1][0] == symbol && game_map[2][0] == symbol) return true;
        if (game_map[0][1] == symbol && game_map[1][1] == symbol && game_map[2][1] == symbol) return true;
        if (game_map[0][2] == symbol && game_map[1][2] == symbol && game_map[2][2] == symbol) return true;

        if (game_map[0][0] == symbol && game_map[1][1] == symbol && game_map[2][2] == symbol) return true;
        if (game_map[0][2] == symbol && game_map[1][1] == symbol && game_map[2][0] == symbol) return true;*/
        //

        if ( checkNorth_And_South(symbol, targetCell) ) {
            return true;
        }

        if (checkNorthwest_And_Southeast(symbol, targetCell)) {
            return true;
        }

        if (checkWest_And_East(symbol, targetCell)) {
            return true;
        }

        if (checkSouthwest_And_Northeast(symbol, targetCell)) {
            return true;
        }

        return false;
    }

    private static boolean checkNorth_And_South(char symbol, int[] targetCell) {
        int validCount = 0;
        for (int i = 0; i < game_map.length; i++) {
            if (game_map[i][targetCell[1]] == symbol) {
                validCount++;
                if (validCount == SIZE_OFTHE_VICTORY) {
                    return true;
                }
                //Прикол в том - не нужно специально проверять вхождение хода в выигрыш
                //Потому что до него в системе не было выигрышной комбинации символов
                //(различать не с чем! Если найдётся хоть одна - это новая)
            } else {
                validCount = 0;
            }
        }
        return false;
    }

    private static boolean checkNorthwest_And_Southeast(char symbol, int[] targetCell) {

        // найти координаты первого элемента диагонали - на пересечении первой строки и столбца
        int[] saved = new int[]{targetCell[0], targetCell[1]};
        int[] check;
        while (true) {
            if (saved[0] < 1 || saved[1] < 1) {
                check = new int[]{saved[0], saved[1]};
                break;
            } else {
                saved[0]--;
                saved[1]--;
            }
        }

        // запустить пробор диагонали с первого элемента и вниз - к пересечению последней строки и столбца
        int validCount = 0;
        for (int j = check[0], k = check[1]; (j < game_map.length) && (k < game_map.length); j++, k++) {
            if (game_map[j][k] == symbol) {
                validCount++;
                if (validCount == SIZE_OFTHE_VICTORY) {
                    return true;
                }
            } else {
                validCount = 0;
            }
        }

        return false;
    }

    private static boolean checkWest_And_East(char symbol, int[] targetCell) {
        int validCount = 0;
        for (int i = 0; i < game_map.length; i++) {
            if (game_map[targetCell[0]][i] == symbol) {
                validCount++;
                if (validCount == SIZE_OFTHE_VICTORY) {
                    return true;
                }
            } else {
                validCount = 0;
            }
        }
        return false;
    }

    private static boolean checkSouthwest_And_Northeast(char symbol, int[] targetCell) {

        // найти координаты первого элемента диагонали - на пересечении первого столбца и последней строки
        int[] check;
        while (true) {
            if (targetCell[0] == (game_map.length - 1) || targetCell[1] < 1) {
                check = new int[]{targetCell[0], targetCell[1]};
                break;
            } else {
                targetCell[0]++;
                targetCell[1]--;
            }

        }

        // запустить пробор диагонали с первого элемента и вверх - к пересечению ппервой строки и последнего столбца
        int validCount = 0;
        for (int j = check[0], k = check[1]; j >= 0 && k < game_map.length; j--, k++) {
            if (game_map[j][k] == symbol) {
                validCount++;
                if (validCount == SIZE_OFTHE_VICTORY) {
                    return true;
                }
            } else {
                validCount = 0;
            }
        }
        return false;
    }

    private static boolean isMapFull() {
        for (char[] chars : game_map) {
            for (char aChar : chars) {
                if(aChar == EMPTY_CHARACTER) {
                    return false;
                }
            }
        }
        return true;
    }


    private static int[] aiTurn() {
        int rowNumber;
        int colNumber;
        System.out.print("\nХод компьютера!\n");
        do {
            try {
                Thread.sleep(600); // Пауза для эффакта мышления AI :))
            } catch(InterruptedException ex) {}
            rowNumber = random.nextInt(SIZE_GAME) + 1;
            colNumber = random.nextInt(SIZE_GAME) + 1;
        } while (!isCellValid(rowNumber, colNumber, true));

        game_map[rowNumber - 1][colNumber - 1] = AI_SYMBOL ;

        return new int[]{rowNumber - 1, colNumber - 1};
    }

}
