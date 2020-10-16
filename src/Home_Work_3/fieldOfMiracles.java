package Home_Work_3;

import java.util.Random;
import java.util.Scanner;

public class fieldOfMiracles {

    private static int colorCount;

    public static void main(String[] args) {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
                "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        colorCount = 0; // Исходиники для игры цветом
        String[] color = {"\u001B[0m", "\u001B[36m", "\u001B[34m", "\u001B[46m", "\u001B[44m"};

        Random rand = new Random();
        int theNumberOfDessert = rand.nextInt( words.length );
        String currentAnswer = words[theNumberOfDessert];

        while (true) {
            System.out.printf("%nGuess, what's for %sde%sss%se%srt%s? =)%n", "\u001B[32m", "\u001B[36m", "\u001B[33m", "\u001B[35m", "\u001B[0m");
            Scanner scan = new Scanner(System.in);
            String answer = scan.next();

            String check = guessTheWord(currentAnswer, answer);

            System.out.printf(check + "%n");

            if ( selectingAHint(check.length(), colorCount, color) ) {
                break;
            }
        }

        //✓ рандомим слово (тут
        //✓ просим угадать (тут
        //✓ логику - методам:
        //✓ guessTheWord() подаем ответ пользователя и загаданное слово, сравниваем, возвращаем ...
        //✓ selectingAHint() подаем состояние отгадывания...
        //  ✓ playWithColor() играем цветами...

    }

    public static String guessTheWord(String currentAnswer, String answer) {

        if (currentAnswer.equals(answer)) {
            return "CONGRATULATIONS! You guessed it: " + currentAnswer.toUpperCase() + " for desert!";
        } else {
            String hint = "";
            for (int i = 0; (i < currentAnswer.length() &&  i < answer.length()); i++) {
                char currentItem = currentAnswer.charAt(i);
                hint += (currentItem == answer.charAt(i)) ? currentItem : "#";
            }
            int sizeHint = hint.length();
            if (sizeHint <= 15 ) {
                for (int i = 0; i < (15 - sizeHint); i++) {
                    hint += "#";
                }
            }
            return "Откройте угаданные буквы! " + hint; // length = 15+26 = 41. Кодируем остановку игры в длине ответа.
        }
    }

    public static boolean selectingAHint(int check, int colorNumber, String[] color) {
        if (check > 41) { // magic number from guessTheWord()
            return true;
        } else {
            playWithColor(colorNumber, color);
            return false;
        }
    }

    public static void playWithColor(int colorNumber, String[] color) {
        String printHitToPlayer = "%n%s..playing until you're blue in the face }:>";
        if (colorNumber < color.length) {
            System.out.printf(printHitToPlayer, color[colorNumber]);
            colorCount++;
        } else {
            System.out.printf(printHitToPlayer, color[color.length-1]);
        }
    }

}

/** Option 2. *Создать массив из слов String[] words =
 * {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
 * "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
 * "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
 *
 * При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
 * сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь.
 *
 * Если слово не угадано, компьютер показывает буквы которые стоят на своих местах.
 * Пример..
 * apple – загаданное,
 * apricot - ответ игрока,
 * ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
 *
 * Для сравнения двух слов посимвольно, можно пользоваться:
 * String str = "apple";
 * str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
 *
 * Играем до тех пор, пока игрок не отгадает слово
 * Используем только маленькие буквы**/
//Serega, sure