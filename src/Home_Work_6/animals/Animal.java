package Home_Work_6.animals;

public abstract class Animal {

    protected String name;
    protected String color;
    protected int age;

    public Animal (String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public void printInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Animal " + "name='" + name + '\'' + ", color='" + color + '\'' + ", age=" + age;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
     }

    public abstract void costOnTheMarket();

    public void run(int distance) {
        System.out.println("Забег на " + distance);
    }

    public void swim(int distance) {
        System.out.println("Заплыв на " + distance);
    }

    public void jump(double height) {
        if (height % 1 == 0.0) {
            System.out.println("Перепрыгнуть.. " + (int) height);
        } else {
            System.out.println("Перепрыгнуть.. " + height);
        }
        //Не понравилось IDEA моё исполнение тернаника.. Настоятельно переделалаи в if(). Пусть, подумал я - для понятности.
    }
}
