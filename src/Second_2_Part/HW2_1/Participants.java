package Second_2_Part.HW2_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Participants {
    private static List<Integer> id_Participants = new ArrayList<Integer>();

    Participants (List<Integer> participants) {
        id_Participants = participants;
    }

    Participants (Integer participant) {
        id_Participants.add(participant);
    }

    public static void setId(Integer participant) {
        boolean check = Arrays.asList(id_Participants).contains(participant);
        if (check != true) {
            id_Participants.add(participant);
            System.out.println("Добавлен новый участник соревнований с id " + participant);
        }
    }

    public static List<Integer> getId() {
        System.out.println(id_Participants);
        return id_Participants;
    }
}