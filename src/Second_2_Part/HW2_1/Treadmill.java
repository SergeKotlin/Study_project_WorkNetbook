package Second_2_Part.HW2_1;

public class Treadmill {
    private String name = "Treadmill";
    private int pathLength;

    public Treadmill(int pathLength) {
        this.pathLength = pathLength;
    }

    public void setPathLength(int pathLength) {
        if (pathLength >= 0) {
            this.pathLength = pathLength;
        } else {
            System.out.println("Вы ввели что-то непонятное ");
        }
    }

    public void setName(String name) {
        if (name.length() <= 20) {
            this.name = name;
        } else {
            System.out.println("Вы ввели что-то непонятное ");
        }
    }

    public int getPathLength() {
        return pathLength;
    }

    public String getName() {
        return this.name;
    }
}
