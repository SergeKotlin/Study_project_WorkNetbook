package Home_Work_6.animals;

public class Cat extends Animal {

    public Cat(String name, String color, int age) {
        super(name,   color, age);
    }

    @Override
    public void costOnTheMarket() {
        System.out.println("Справка. Из кота можно сделать одежду, еду, чучело, сувениры..");
    }

    @Override
    public void run(int distance) {
        super.run(distance);
        String answer = (distance <= 200) ? (this.name + ": \"Мяу-у. Догони!\"") : (this.name + ": \"Не, не побегу!\"");
        System.out.println(answer);
    }

    @Override
    public void swim(int distance) {
        super.swim(distance);
        System.out.println(this.name + ": \"Приятель. Коты не мочат шерсть!\"");
    }

    @Override
    public void jump(double height) {
        super.jump(height);
        String answer = (height <= 2) ? (this.name + ": \"Мяу-у! Это я умею\"") : (this.name + ": \"Приятель, а ты меня кормил?)\"");
        System.out.println(answer);
    }
}
