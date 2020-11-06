package Home_Work_7;

import com.sun.org.apache.bcel.internal.generic.ARETURN;

import java.util.Random;

public class Cat {

    private final String name;
    private String breed;
    private int levelAppetite;
    private int minHealthyAppetite;


    public Cat(String name, String breed, int minHealthyAppetite) {
        this.name = name;
        this.breed = breed;
        this.minHealthyAppetite = minHealthyAppetite;
        Random random = new Random();
        this.levelAppetite = minHealthyAppetite + random.nextInt(151);

    }

    public Cat(String name, String breed) {
        this(name, breed,20);
    }

    public Cat(String name) {
        this(name, "Usual cat",20);
    }

    public Cat() {
        this("stray cat", "stray");
    }

    public boolean needToEat(Plate plate) {
        if (plate.getLevelFood() >= levelAppetite) {
            eating(plate);
            System.out.println("Кот доволен");
            return true;
        }
        else {
            System.out.println("Мне нужно больше еды, чем в " + plate.plateName + "!");
        }
        return false;
    }

    public void needToEat(Kitchen kitchen) {

        switch (breed) {
            case ("Blue-blooded"): // Кот голубых кровей ест только так
                System.out.println("\u001B[34m");
                if (!tryToFindTheRightBowl(kitchen.foodOfKitchen)) {
                    System.out.println("\u001B[34m" + this.name + ": Приду в следующий раз. Для меня тут нет еды..");
                }
                System.out.println("\u001B[30m");
                break;
            case ("stray"): // Уличный кот без названия не ест из тарелок. Он сразу навещает мусорку.
                // Не то, чтобы он брезговал чистой едой.. Просто он любит по-тухлее.
                // Для простоты задачи добавим такому коту угощение в помойке: ourKitchen.garbageOfKitchen.addFood(300);
                kitchen.garbageOfKitchen.addFood(100);
                if (!letsMoveToTheDelicious(kitchen.garbageOfKitchen)) {
                    letsEatSomething(kitchen.foodOfKitchen);
                }
                break;
            case ("Usual cat"):
                // Любой, даже обычный кот (кроме странника) любит повоображать из себя - "породистого" кота
                if (!tryToFindTheRightBowl(kitchen.foodOfKitchen)) {
                    if (!letsEatSomething(kitchen.foodOfKitchen)) {
                        letsMoveToTheDelicious(kitchen.garbageOfKitchen); //но чтобы он там не мурлыкал.. Голод - не тётка
                    }
                };
                break;
            default:
                break;
        }
    }

    private boolean tryToFindTheRightBowl(Plate[] plates) {
        for (Plate plate : plates) {
            if (needToEat(plate) == true) {
                return true;
            }
        }
        return false;
    }

    private boolean letsEatSomething(Plate[] plates) {
        for (Plate plate : plates) {
            if (plate.getLevelFood() != 0) {
                int resultFeeding = eating(plate);
                System.out.println("Кот почти доволен");
                if (resultFeeding == 0) {
                    return true;
                }
            }
            else {
                System.out.println("Мне нужно больше еды, чем в " + plate.plateName + "!");
            }
        }
        System.out.println("Капец.. А где еда то?! Я не полезу в мусорку!");
        return false;
    }

    private boolean letsMoveToTheDelicious (Plate garbage) {
        if (garbage.food != 0) {
                eating(garbage);
                System.out.println("Кот вроде как доволен (если едой с помойки можно быть довольным..)");
                return true;
            } else {
                System.out.println("Мне нужно больше еды, чем в " + garbage.plateName + "!");
            }
        return false;
    }


    private int eating(Plate plate) {
        System.out.println("..насыщается " + this.name + ". Из тарелки " + plate.plateName);
        int resultFeeding = plate.foodDecline(levelAppetite - minHealthyAppetite);
        plate.printInfo();
        if (resultFeeding == 0) {
            this.levelAppetite = minHealthyAppetite;
            return 0;
        } else {
            this.levelAppetite = resultFeeding;
            return resultFeeding;
        }
    }

    @Override
    public String toString() {
        return name + " {" + "Cat's appetite level = " + levelAppetite + "}";
    }

    public void printInfo() {
        System.out.println(this);
    }

    public void catsLegend() {
        System.out.println("Легенда про кухонных котов.\n" +
                "Коты Голубых кровей едят только из своей тарелочки, и только с необходимым уровнем еды..\n" +
                "Уличный кот, он же кот без названия (он же странник, он же номад) - не ест из тарелок. Он сразу навещает мусорку.\n" +
                "Не то, чтобы он брезговал чистой едой.. Просто он попробвал разные кухни, и теперь любит по-тухлее.\n" +
                "(Для простоты задачи, мы заботимся о странниках, и всегда добавляем пару тухляшек в кухонную мусорку перед их кормлением)\n" +
                "Любой, даже обычный кот (кроме странника) любит повоображать из себя - \"породистого\" кота...\n" +
                "..но что бы он не мурлыкал, как поступит - если еды в тарелках не окажется, от слова «Совсем»?) Голод - не тётка!");
    }

}
