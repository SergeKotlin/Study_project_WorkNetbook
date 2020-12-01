package Second_2_Part.HW2_3;

import java.util.*;

public class Main {

    static String[] people = {"Katrin", "Lisi", "Ivan", "Serge", "Bionica", "Vika", "Piter", "Katrin", "Daria",
        "Katrin", "Filena", "Ivan", "Jhon", "Nadya", "Vika", "Piter", "Jhon", "Daria", "Valeo", "Filena"};

    public static void main(String[] args) {

        Set<String> set = findingPeople();
        System.out.println();
        LinkedList<String> list = toConsiderPersons();
        System.out.println();
        countThePeople(set, list);
        System.out.println();

        String[] numbers = {"+7934", "+7936", "+7978", "+7977", "+79100", "+7922", "+7933", "+79912", "later",
            "79393", "+79693", "+722", "+734", "+7345", "+7889", "+733345", "+72245", "+731145", "+723", "911"};

        TelephoneDirectory MyJob = new TelephoneDirectory();
        for (int i = 0; i < numbers.length; i++) {
            MyJob.addInTelephoneDirectory(people[i], numbers[i]);
        }

        MyJob.getInTelephoneDirectory("Katrin");
    }

    private static Set<String> findingPeople() {
        Set<String> set = new LinkedHashSet<>();
        for (String human : people) {
            set.add(human);
        }
        System.out.println(set);
        return set;
    }

    private static LinkedList<String> toConsiderPersons() {
        LinkedList<String> list = new LinkedList<>();
        for (String person : people) {
            list.add(person);
        }
        System.out.println(list);
        return list;
    }

    private static void countThePeople(Set<String> set, LinkedList<String> list) {
        for (String human : set) {
            System.out.println("Human: \u001B[32m" + human + "\u001B[30m, count: \u001B[32m" + Collections.frequency(list, human) + "\u001B[30m ");
        }
        System.out.println();
    }
}

/** ✓1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
 *      ✓1.2 Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
 *      ✓1.3 Посчитать, сколько раз встречается каждое слово.
 ✓2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
        ✓2.2 В этот телефонный справочник с помощью метода add() можно добавлять записи.
        2.3 С помощью метода get() искать номер телефона по фамилии.
        2.4 Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
        тогда при запросе такой фамилии должны выводиться все телефоны.
 **/