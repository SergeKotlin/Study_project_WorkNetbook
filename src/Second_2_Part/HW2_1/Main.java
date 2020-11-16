package Second_2_Part.HW2_1;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        // Одиночный тест
        Participants participants = new Participants(0);
        Human boris = new Human();
        boris.setId(1, participants);
        boris.setName("Борька");
        boris.setMaxRunDistance(9000);
        boris.setMaxJumpHeight(180);
        //
        boris.run(2000);
        boris.jump(170);
        boris.setSuccess();
        System.out.println();
        // Инициализации
        System.out.println("Инициализация участников..");
        Human kristi = new Human();
        kristi.setId(2, participants);
        kristi.setName("Кристина");
        kristi.setMaxRunDistance(4000);
        kristi.setMaxJumpHeight(170);
        //
        Robo briton = new Robo();
        briton.setId(3, participants);
        briton.setName("Брит");
        briton.setMaxRunDistance(20000);
        briton.setMaxJumpHeight(100);
        //
        Robo trinix = new Robo();
        trinix.setId(4, participants);
        trinix.setName("Триникс");
        trinix.setMaxRunDistance(5000);
        trinix.setMaxJumpHeight(150);
        //
        Cat tristan = new Cat();
        tristan.setId(5, participants);
        tristan.setName("Тристан");
        tristan.setMaxRunDistance(3500);
        tristan.setMaxJumpHeight(180);
        //
        Cat bayarsik = new Cat();
        bayarsik.setId(6, participants);
        bayarsik.setName("Баярсик");
        bayarsik.setMaxRunDistance(5000);
        bayarsik.setMaxJumpHeight(150);
        //
        // Отбор претендентов соревенований
        System.out.println("Отбор претендентов для соревенований..");
        Human[] applicantsPeople = {boris, kristi};
        Robo[] applicantsRobots = {briton, trinix};
        Cat[] applicantsCats = {tristan, bayarsik};
        // Ручной отбор претендентов
        Applicants applicants = new Applicants(new Integer[]{boris.getId(), kristi.getId(),
                briton.getId(), trinix.getId(), tristan.getId(), bayarsik.getId()});
        //
        // Формирование препятствий
        System.out.println("Формирование препятствий..");
        Treadmill treadmill = new Treadmill(4000);
        Wall wall = new Wall(150);
        //
        int[][] trials = {{1, treadmill.getPathLength()}, {2, wall.getHeightWall()}};

        // <отдельно испытывать каждый тип участника, id же общий для всех>

        // Начало испытаний
        System.out.println();
        System.out.println("Испытания для людей начались!");
        theTrialForPeople(applicantsPeople, applicants, trials);
        System.out.println();
        System.out.println("Испытания для роботов начались!");
        theTrialForRobots(applicantsRobots, applicants, trials);
        System.out.println();
        System.out.println("Испытания для кошек начались!");
        theTrialForCats(applicantsCats, applicants, trials);
        System.out.println();

    }

    public static void theTrialForPeople(Human[] applicantsPeople, Applicants applicants, int[][] trials) {
        for (Human participant : applicantsPeople) {
            //То, что ниже выполняется, в упрощённой форме : boolean check = Arrays.asList(new Integer[]{1, 2}).contains(1);
            boolean check = Arrays.asList(applicants.getIdOfApplicants()).contains(participant.getId());
            if (check == true) {
                for (int[] trial : trials) {
                    if (participant.getSuccess() == true) {
                        switch (trial[0]) {
                            case 1:
                                participant.run(trial[1]);
                                break;
                            case 2:
                                participant.jump(trial[1]);
                                break;
                        }
                    }
                }
                if (participant.getSuccess() == true) {
                    System.out.println("Участник " + participant.getId() + " "
                            + participant.getName() + " справился со всем!");
                }
            }
        }
    }

    public static void theTrialForRobots(Robo[] applicantsRobots, Applicants applicants, int[][] trials) {
        for (Robo participant : applicantsRobots) {
            boolean check = Arrays.asList(applicants.getIdOfApplicants()).contains(participant.getId());
            if (check == true) {
                for (int[] trial : trials) {
                    if (participant.getSuccess() == true) {
                        switch (trial[0]) {
                            case 1:
                                participant.run(trial[1]);
                                break;
                            case 2:
                                participant.jump(trial[1]);
                                break;
                        }
                    }
                }
                if (participant.getSuccess() == true) {
                    System.out.println("Участник " + participant.getId() + " "
                            + participant.getName() + " справился со всем!");
                }
            }
        }
    }

    public static void theTrialForCats(Cat[] applicantsCats, Applicants applicants, int[][] trials) {
        for (Cat participant : applicantsCats) {
            boolean check = Arrays.asList(applicants.getIdOfApplicants()).contains(participant.getId());
            if (check == true) {
                for (int[] trial : trials) {
                    if (participant.getSuccess() == true) {
                        switch (trial[0]) {
                            case 1:
                                participant.run(trial[1]);
                                break;
                            case 2:
                                participant.jump(trial[1]);
                                break;
                        }
                    }
                }
                if (participant.getSuccess() == true) {
                    System.out.println("Участник " + participant.getId() + " "
                            + participant.getName() + " справился со всем!");
                }
            }
        }
    }

}

/** ✓1. Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса.
 *      ✓1.2 Эти классы должны уметь бегать и прыгать
 *      (методы просто выводят информацию о действии в консоль).
 ✓2. Создайте два класса: беговая дорожка и стена, при прохождении через которые:
 *      ✓2.2 участники должны выполнять соответствующие действия (бежать или прыгать),
 *      ✓2.3 результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.);
 *      ✓2.4 у препятствий есть длина (для дорожки) или высота (для стены),
 *      ✓2.5 а у участников есть ограничения на бег и прыжки.
 ✓3. Создайте два массива: с участниками и препятствиями.
 *      ✓3.2 Заставьте всех участников пройти этот набор препятствий.
 *      ✓3.3 Если участник не смог пройти одно из препятствий,
 *      то дальше по списку препятствий он не идет.**/
//Serega, sure
