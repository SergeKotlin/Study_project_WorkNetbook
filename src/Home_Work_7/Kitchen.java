package Home_Work_7;

public class Kitchen {

    protected Plate[] foodOfKitchen; //protected modifier for - needToEat
    protected Plate garbageOfKitchen; //protected modifier for - Main, printInfo()

    Kitchen(Plate[] foodOfKitchen) {
        this.foodOfKitchen = foodOfKitchen;
        this.garbageOfKitchen = new Plate(1000, "garbageOfKitchen");
    }

    Kitchen() {
        this(new Plate[]{});
    }

    public int getGarbage() {
        int transferOfGarbage = garbageOfKitchen.food;
        garbageOfKitchen.food = 0;
        return transferOfGarbage;
    }

    public void addFood(int food) {
        double depravityIndex = 0.65;
        int determination = (int)(food * depravityIndex);
        if (determination <= garbageOfKitchen.volume - garbageOfKitchen.food - determination) {
            this.garbageOfKitchen.food += determination;
        } else {
            this.garbageOfKitchen.food = garbageOfKitchen.volume;
            System.out.println("На кухне уже свалка. Часть еды будет выброшена в окно");
        }

    }

    @Override
    public String toString() {
        String answer = "Kitchen info. Plates: ";
        for (Plate plates: foodOfKitchen) {
            answer += plates + "; ";
        }
        //просто полезная функция answer.substring(0, answer.length() - 2);
        answer += "(special) " + garbageOfKitchen;
        return answer;
    }

    public void printInfo() {
        System.out.println(this);
    }
}


