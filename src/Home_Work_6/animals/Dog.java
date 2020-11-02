package Home_Work_6.animals;

import java.util.Arrays;

public class Dog extends Animal {

    private int dogClass;
    private boolean collar;

    public Dog(String name, String color, int age, int dogClass, boolean collar) {
        super(name, color, age);
        this.dogClass = dogClass;
        this.collar = collar;
    }

    public Dog(String name, String color, int age) {
        this(name, color, age, 1, false);
    }

    public void setDogClass(int dogClass) {
        int[] dogClasses = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        boolean failInput = true;

        for (int check : dogClasses) {
            if (check == dogClass) {
                this.dogClass = dogClass;
                System.out.printf("Класс породы %s изменён на %d", this.name, dogClass);
                failInput = false;
                break;
            }
        }

        if (failInput == true) {
            System.out.println("Не надо идти против Федерации. Классы собак подчиняются интервалу от 1 до 10");
        }
    }

    public void printClassInfo() {
        System.out.println("Справка. Классификация пород собак Международной кинологической федерации:\n" +
                "1 Пастушьи и скотогонные собаки, кроме швейцарских скотогонных собак\n" +
                "2 Пинчеры и шнауцеры - догообразные и швейцарские горные скотогонные собаки\n" +
                "3 Терьеры\n" +
                "4 Таксы\n" +
                "5 Шпицы и примитивные типы собак\n" +
                "6 Гончие, гончие по кровяному следу и родственные породы\n" +
                "7 Легавые собаки\n" +
                "8 Ретриверы, спаниели, водяные собаки\n" +
                "9 Собаки-компаньоны, декоративные собаки\n" +
                "10 Борзые");
    }

    @Override
    public void costOnTheMarket() {
        System.out.println("Справка. Из собаки можно сделать одежду, еду, чучело, сувениры, подрывного пса, сварить клей");
    }

    @Override
    public void run(int distance) {
        super.run(distance);
        int valueOfTheClassDisctance = this.runingDistanceForTheClass(this.dogClass);
        String answer = (distance <= valueOfTheClassDisctance) ? (this.name + ": \"Вуф, вуф! Стремительно!\"") : (this.name + ": \"Хозяин, сбегай ка сам..?\"");
        System.out.println(answer);
    }

    public int runingDistanceForTheClass(int dogClass) {
        switch (dogClass) {
            case 1:
                return 1600;
            case 2:
                return 300;
            case 3:
                return 700;
            case 4:
                return 300;
            case 5:
                return 300;
            case 6:
                return 3500;
            case 7:
                return 2000;
            case 8:
                return 1000;
            case 9:
                return 300;
            case 10:
                return 2800;
            default:
                return 500;
        }
    }

    @Override
    public void swim(int distance) {
        super.swim(distance);
        String answer = (distance <= 10) ? (this.name + ": \"Вуф! В воду!\"") : (this.name + ": \"Я не рыба. Я - собака! Вуф, вуф!\"");
        System.out.println(answer);
    }

    @Override
    public void jump(double height) {
        super.jump(height);
        String answer = (height <= 0.5) ? (this.name + ": \"Вуф! Береги зад!\"") : (this.name + ": \"Никак не допрыгну =(\"");
        System.out.println(answer);
    }
}
