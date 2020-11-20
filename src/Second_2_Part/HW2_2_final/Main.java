package Second_2_Part.HW2_2_final;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class Main {

    // rows & cols не менять, они же задают и условие 4х4/5х5 и т.д.
    private static final int rows = 4;
    private static final int cols = rows;

    public static void main(String[] args) {

//            корректный массив:
        String[][] myArray = new String[][] {{"Еёжки",  "9", "Кристина", "69"},
                {"Ой.. а тут числа?", "-1.2.3", "2", "9"},
                {"Нисго в голову не приходит", "+2", "v", "2v"},
                {"м", "11", "2", "2"}};

        try {
            boolean check = false;
            if (myArray.length == rows) {

                check = isCheck(myArray, check);

                if (check == true) {
                    System.out.println();
                    System.out.println("Вот, пожалуйста. Всё, что удалось просуммировать: " + someMethod(myArray));
                    System.out.println();
                }
            } else {
                throw new MyArraySizeException();
            }

        } catch (MyArraySizeException e) {
            System.out.println("Size.. ОМОНОМОМОМ0___0");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Wow! General Exception");
            e.printStackTrace();
        }

    }

    public static boolean isCheck(String[][] myArray, boolean check) throws MyArraySizeException {
        for (String[] strings : myArray) {
            if (strings.length != cols) {
                System.out.println("Да идите вы.. вводить другой массив! Работаем только с " + rows + "x" + cols + "!");
                throw new MyArraySizeException();
            }
        }
        return check = true;
    }

    public static int someMethod(String[][] someString) {

        int sum = 0;

        for (String[] strings : someString) {
            for (String items : strings) {
                int item;

                try {
                    item = Integer.parseInt(items.trim());
                } catch (NumberFormatException e) {
                    System.out.println("Data МОМ0___0");
                    item = 0;
                    e.printStackTrace();
                    System.out.println("\u001B[31m" + "java.lang.MyArrayDataException: For input string: " + items);
                }

                sum += item;
            }
        }

        return sum;
    }

}

/** ✓1. Напишите метод, на вход которого подается двумерный строковый массив размером 4х4,
 *    ✓1.2 при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
 *  ✓2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
 *    ✓2.2 Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст
 *          вместо числа), должно быть брошено исключение MyArrayDataException – с детализацией, в какой именно
 *          ячейке лежат неверные данные.
- *  ✓3. В методе main() вызвать полученный метод,
 *    ✓3.2 обработать возможные исключения MySizeArrayException и MyArrayDataException
 *      и вывести результат расчета. **/
//Serega, sure