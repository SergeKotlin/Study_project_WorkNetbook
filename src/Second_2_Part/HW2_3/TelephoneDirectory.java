package Second_2_Part.HW2_3;

import jdk.nashorn.internal.ir.CallNode;
import jdk.nashorn.internal.ir.LiteralNode;

import java.text.MessageFormat;
import java.util.*;

public class TelephoneDirectory {

    private Map<String, String> telephoneDirectory;

    TelephoneDirectory() {
        telephoneDirectory = new HashMap<>();
    }


    protected void addInTelephoneDirectory(String name, String phoneNumber) {
        telephoneDirectory.put(phoneNumber, name);
    }

    protected void getInTelephoneDirectory(String name) {

        getTelDictionaryList();
        System.out.println();

        ArrayList<String> personCount = new ArrayList<String>();
        personCount.trimToSize();

        if (telephoneDirectory.containsValue(name)) {
            personCount.add(name);
            for (Map.Entry<String, String> entry : telephoneDirectory.entrySet()) {
                if (entry.getValue() == name) {
                    personCount.add(entry.getKey());
                }
            }
        } else {
            personCount.add(name);
            personCount.add("0");
        }

        printAnswer(name, personCount);
    }

    protected void getTelDictionaryList() {
        telephoneDirectory.forEach((value, key) -> System.out.println(MessageFormat.format
                ("Имя: {1}, номер {0}", value, key)));
    }

    private void printAnswer(String name, ArrayList<String> personCount) {
        System.out.print("Имени " + name + " соответствуют следующие номера: ");
        for (int i = 1; i < personCount.size(); i++) {
            System.out.println(personCount.get(i) + " ");
        }
    }
}
