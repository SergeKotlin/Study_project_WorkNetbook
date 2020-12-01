package Home_Work_6.animals;

public class Main {

    public static void main(String[] args) {
        Cat cat = new Cat("Гарфилд", "Ночной охотник", 3);
        Dog dog = new Dog("Визи", "Пяточок", 9);

        cat.printInfo();
        dog.printInfo();
        System.out.println();

        cat.run(200);
        cat.swim(10);
        cat.jump(2);
        System.out.println();

        dog.run(200);
        dog.swim(10);
        dog.jump(0.5);
        System.out.println();

        cat.run(550);
        cat.swim(25);
        cat.jump(5);
        cat.costOnTheMarket();
        System.out.println();

        dog.run(1700);
        dog.swim(25);
        dog.jump(2);
        dog.costOnTheMarket();
        System.out.println();

        dog.printClassInfo();
        System.out.println();
        dog.setDogClass(2);
        System.out.println();
    }
}

/** ✓1. Создать классы Собака и Кот с наследованием от класса Животное.
 ✓2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие.
 ✓В качестве параметра каждому методу передается величина, означающая или длину препятствия (для бега и плавания),
 или высоту (для прыжков).
 ✓3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; прыжок: кот 2 м.,
 собака 0.5 м.; плавание: кот не умеет плавать, собака 10 м.).
 ✓4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат в консоль.
 (Например, dog1.run(150); -> результат: run: true)
 ✓5. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м.,
 у другой 600 м.
 **/
//Serega, sure