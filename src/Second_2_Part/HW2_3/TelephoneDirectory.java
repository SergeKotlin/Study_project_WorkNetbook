package Second_2_Part.HW2_3;

import jdk.nashorn.internal.ir.CallNode;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TelephoneDirectory {

    private Map<String, String> telephoneDirectory;

    TelephoneDirectory() {
        telephoneDirectory = new HashMap<>();
    }


    void addInTelephoneDirectory(String name, String phoneNumber) {
        telephoneDirectory.put(phoneNumber, name);
    }

    void getInTelephoneDirectory() {
        telephoneDirectory.forEach((value, key) -> System.out.println(MessageFormat.format
                ("Имя: {1}, номер {0}", value, key)));

//        for (String s : telephoneDirectory.keySet()) {
//            if (telephoneDirectory.containsValue(name)) {
//                telephoneDirectory.forEach((value, key) -> System.out.println(MessageFormat.format
//                        ("Имя: {1}, номер {0}", value, key)));
//            }
//        }
    }
}
