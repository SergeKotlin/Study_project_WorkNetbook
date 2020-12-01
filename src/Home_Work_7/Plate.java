package Home_Work_7;

import java.sql.SQLOutput;

public class Plate {

    protected int volume; //protected modifier for - getGarbage() and addFood()
    protected String plateName; //protected modifier for - throwAwayFood()
    protected int food; //protected modifier for - getGarbage()

    public Plate(int volume, String plateName) {
        this.volume = volume;
        this.plateName = plateName;
    }

    public Plate(int volume) {
        this(volume, "Some plate");
    }

    public int getLevelFood() {
        return food;
    }

    public void addFood(int food) {
        if (this.food < volume) {
            if ( (volume - this.food) > food) {
                this.food += food;
            } else {
                this.food = volume;
            }
            System.out.println("Добавляем еду в тарелку " + this.plateName);
        } else {
            System.out.println( ( this.plateName != null ? this.plateName : "Plate") + " наполнена до краёв" );
        }
    }

    public void addFood(String max) {
        if (max == "max") {
            addFood(this.volume);
        }
    }

    public void throwAwayFood(int food, Kitchen kitchen, Plate plate) {
        System.out.println("Выбрасываем еду из тарелки " + plate.plateName);
        if (food <= plate.food) {
            foodDecline(food);
            kitchen.addFood(food);
        } else {
            System.out.println("В тарелке " + plate.plateName + " нет столько еды!");
        }
    }

    public int foodDecline (int feedingOrUtilization) {
        if (feedingOrUtilization <= this.food) {
            this.food -= feedingOrUtilization;
            System.out.println("еда пропадает из тарелки");
            return 0;
        } else {
            System.out.println("еда пропадает из тарелки");
            this.food = 0;
            return (feedingOrUtilization - this.food);
        }
    }

    @Override
    public String toString() {
        return plateName + " {" + "volume = " + volume + ", food = " + food + '}';
    }

    public void printInfo() {
        System.out.println(this);
    }

    public void catsLegend() {
        System.out.println("Легенда про кухонную кошачью посуду.\n" +
                "Было замечено, что котик от природы голоден. Сытый кот - это чуть-чуть голодный кот (20 единиц)\n" +
                "Нельзя просто так взять, и выбросить еду.. Она по пути должна протохнуть и уменьшится в съедобном объёме!\n" +
                "Странники очень любят мусорные деликатесы... Коты с родословной никогда к ним не притрагиваются.\n" +
                "Если «мусорных деликатесов» слишком много, а странники не заходят, от них нужно избавляться!\n" +
                "(И что же с ними делать, если в туалетной комнате уже было несколько засоров...)\n");
    }

}
