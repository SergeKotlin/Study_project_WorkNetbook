package Home_Work_7;

public class Main {
    public static void main(String[] args) {
        Cat bayarsik = new Cat("Баярсик", "Blue-blooded");
        Cat bayarsikFräulein = new Cat("Фролен Баярсика", "Blue-blooded");
        Cat shmurik = new Cat("Шмурик");
        Cat nomad = new Cat();
        Plate middlePlate = new Plate(350);
        Plate largePlate = new Plate(750, "Large red bowl");
        Plate smallPlate = new Plate(200, "Small white saucer");
        Kitchen ourKitchen = new Kitchen(new Plate[]{smallPlate, middlePlate, largePlate});
        //
        //Let's go!
        //
        //проверка миски
        System.out.println("проверка миски");
        largePlate.printInfo();
        largePlate.addFood(1000);
        largePlate.printInfo();
        System.out.println();
        //смотрим на питание из 1 миски
        System.out.println("смотрим на питание из 1 миски");
        bayarsik.printInfo();
        bayarsik.needToEat(largePlate);
        largePlate.printInfo();
        bayarsik.printInfo();
        System.out.println();
        //выбрасываем корм
        System.out.println("выбрасываем корм");
        ourKitchen.garbageOfKitchen.printInfo();
        largePlate.throwAwayFood(largePlate.food, ourKitchen, largePlate);
        largePlate.printInfo();
        System.out.println();
        //выбрасываем корм 2
        System.out.println("выбрасываем корм 2");
        ourKitchen.garbageOfKitchen.printInfo();
        largePlate.throwAwayFood(3000, ourKitchen, largePlate);
        largePlate.printInfo();
        ourKitchen.garbageOfKitchen.printInfo();
        System.out.println();
        //выбрасываем корм 3
        System.out.println("выбрасываем корм 3");
        largePlate.addFood(1000);
        largePlate.throwAwayFood(largePlate.food, ourKitchen, largePlate);
        ourKitchen.garbageOfKitchen.printInfo();
        System.out.println();
        //
        //смотрим на питание на кухне (массив тарелок) кота "Голубых кровей"
        System.out.println("~~~ Смотрим на питание на кухне (массив тарелок) кота \"Голубых кровей\" ~~~");
        largePlate.throwAwayFood(largePlate.food, ourKitchen, largePlate);
        bayarsikFräulein.printInfo();
        smallPlate.addFood(80);
        ourKitchen.printInfo();
        bayarsikFräulein.needToEat(ourKitchen);
        bayarsikFräulein.printInfo();
        ourKitchen.printInfo();
        System.out.println();
        //смотрим на питание в условиях кухни (массив тарелок) обычного котэ
        System.out.println("~~~ Смотрим на питание в условиях кухни (массив тарелок) обычного котэ ~~~");
        shmurik.printInfo();
        middlePlate.throwAwayFood(middlePlate.food, ourKitchen, middlePlate);
        shmurik.needToEat(ourKitchen);
        shmurik.printInfo();
        ourKitchen.printInfo();
        System.out.println();
        //смотрим, как поведёт себя на кухне (массив тарелок) странник
        System.out.println("~~~ Смотрим, как поведёт себя на кухне (массив тарелок) странник ~~~");
        nomad.printInfo();
        ourKitchen.garbageOfKitchen.addFood(300);
        ourKitchen.printInfo();
        nomad.needToEat(ourKitchen);
        nomad.printInfo();
        ourKitchen.printInfo();
        System.out.println();
        shmurik.catsLegend();
        System.out.println();
        middlePlate.catsLegend();
    }
}

/** ✓1. Сделать массив из тарелок. Коты не могут вызывать тарелку из массива
 * и опустошать её в < 0.
 * ✓2. Сделать поле сытости котам. Изначально коты несколько голодны.
 * Если кот поел - кот стал сытым котом, его аппетит мал.
 * 3. Кот тыкается по очереди в массив тарелок. Выбирает ту, где ему хватает.
 * Или возвращается и подъедеает, начиная с первой.
 * ✓4. В тарелке сделать метод пополнения тарелки.
 *
 **/
//Serega, sure